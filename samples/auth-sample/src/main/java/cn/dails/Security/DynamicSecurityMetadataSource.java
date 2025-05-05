package cn.dails.Security;

import cn.dails.dao.SysPermissionDao;
import cn.dails.dao.SysRolePermissionRelationDao;
import cn.dails.dao.entity.SysPermissionEntity;
import cn.dails.dao.entity.SysRoleEntity;
import cn.dails.service.ISysPermissionService;
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

    // 缓存权限数据
    private static Map<String, Collection<ConfigAttribute>> permissionMap = null;

    /**
     * 加载所有权限数据
     */
    private void loadPermissionData() {
        permissionMap = new ConcurrentHashMap<>();

        // 查询所有权限
        List<SysPermissionEntity> permissions = permissionMapper.selectList(null);

        // 查询每个权限对应的角色
        for (SysPermissionEntity permission : permissions) {
            String key = permission.getPath() + ":" + permission.getHttpMethod();
            List<SysRoleEntity> roles = permissionMapper.selectRolesByPermissionId(permission.getId());

            Collection<ConfigAttribute> configAttributes = new ArrayList<>();
            for (SysRoleEntity role : roles) {
                ConfigAttribute configAttribute = new SecurityConfig(role.getRoleCode());
                configAttributes.add(configAttribute);
            }

            permissionMap.put(key, configAttributes);
        }
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        if (permissionMap == null) {
            loadPermissionData();
        }

        FilterInvocation fi = (FilterInvocation) object;
        HttpServletRequest request = fi.getRequest();
        String url = request.getRequestURI();
        String method = request.getMethod();

        String key = url + ":" + method;

        // 查找匹配的权限
        for (Map.Entry<String, Collection<ConfigAttribute>> entry : permissionMap.entrySet()) {
            String pattern = entry.getKey();
            if (pathMatches(pattern, key)) {
                return entry.getValue();
            }
        }

        // 如果没有配置权限，默认允许访问
        return SecurityConfig.createList("ROLE_PUBLIC");
    }

    private boolean pathMatches(String pattern, String path) {
        // 简单的路径匹配，可以根据需要实现AntPathMatcher等更复杂的匹配
        return pattern.equals(path);
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