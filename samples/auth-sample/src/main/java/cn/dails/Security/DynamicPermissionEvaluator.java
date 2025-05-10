package cn.dails.Security;

import cn.dails.dao.SysPermissionDao;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;
@Component
@RequiredArgsConstructor
public class DynamicPermissionEvaluator implements PermissionEvaluator {

    private final SysPermissionDao permissionDao;

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        // 不需要实现这个方法，除非你有特殊需求
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        // 1. 获取当前用户
        if (authentication == null || !authentication.isAuthenticated()) {
            return false;
        }


        // 2. 如果是管理员，直接放行
        if (authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            return true;
        }

        // 3. 获取用户ID
        SecurityUserDetails userDetails = (SecurityUserDetails) authentication.getPrincipal();
        Long userId = userDetails.getUserId();

        // 4. 检查权限
        return permissionDao.checkUserPermission(userId, permission.toString());
    }
}