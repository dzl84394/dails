package cn.dails.Security;

import cn.dails.dao.SysPermissionDao;
import cn.dails.dao.SysRoleDao;
import cn.dails.dao.SysRolePermissionRelationDao;
import cn.dails.dao.entity.SysPermissionEntity;
import cn.dails.dao.entity.SysRoleEntity;
import cn.dails.service.ISysPermissionService;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.util.AntPathMatcher;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DynamicSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    private final SysPermissionDao permissionMapper;
    private final SysRolePermissionRelationDao rolePermissionMapper;
    private final SysRoleDao roleDao;
    // 缓存权限数据
    private static Map<String, Collection<ConfigAttribute>> permissionMap = null;
    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

    @PostConstruct  // 增加此注解
    public void init() {
        loadPermissionData(); // 确保启动时加载
    }
    /**
     * 加载所有权限数据
     */
    private void loadPermissionData() {
        permissionMap = new ConcurrentHashMap<>();

        // 查询所有权限
        List<SysPermissionEntity> permissions = permissionMapper.selectList(null);

        // 查询每个权限对应的角色
        for (SysPermissionEntity permission : permissions) {
            String key = permission.getResource() + ":" + permission.getHttpMethod();
            List<SysRoleEntity> roles = roleDao.selectRolesByPermissionId(permission.getId());

            Collection<ConfigAttribute> configAttributes = new ArrayList<>();
            for (SysRoleEntity role : roles) {
                ConfigAttribute configAttribute = new SecurityConfig(role.getRoleCode());
                configAttributes.add(configAttribute);
            }

            permissionMap.put(key, configAttributes);
        }
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) {
//        HttpServletRequest request = ((FilterInvocation) object).getRequest();
        HttpServletRequest request = null;
        if (object instanceof FilterInvocation) {
            request = ((FilterInvocation) object).getRequest();
        } else if (object instanceof HttpServletRequest) {
            request = (HttpServletRequest) object;
        } else {
            // 其他情况返回默认权限（或拒绝）
            return SecurityConfig.createList("PERMIT_ALL");
        }


        String url = request.getRequestURI();
        String method = request.getMethod();

        // 放行公开路径
        if (url.startsWith("/login") || url.startsWith("/public/") || url.startsWith("/actuator")) {
            return SecurityConfig.createList("PERMIT_ALL");
        }

        // 查找匹配的权限规则
        for (Map.Entry<String, Collection<ConfigAttribute>> entry : permissionMap.entrySet()) {
            if (antPathMatcher.match(entry.getKey(), url)) {
                return entry.getValue();
            }
        }

        // 默认放行PERMIT_ALL，拒绝是DENY
        return SecurityConfig.createList("PERMIT_ALL");
    }

    private boolean isPublicPath(String url) {
        return url.equals("/login")
                || url.startsWith("/public/")
                || url.startsWith("/assets/");
    }

    private boolean pathMatches(String pattern, String url, String method) {
        String[] parts = pattern.split(":");
        String pathPattern = parts[0];
        String methodPattern = parts.length > 1 ? parts[1] : "*";

        return antPathMatcher.match(pathPattern, url)
                && ("*".equals(methodPattern) || methodPattern.equalsIgnoreCase(method));
    }



    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        if (permissionMap == null) {
            loadPermissionData();
        }
        return permissionMap.values().stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }

    /**
     * 清除权限缓存
     */
    public void clearPermissionCache() {
        permissionMap = null;
    }
}