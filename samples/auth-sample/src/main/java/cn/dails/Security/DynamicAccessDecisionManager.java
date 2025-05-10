package cn.dails.Security;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class DynamicAccessDecisionManager implements AccessDecisionManager {

    @Override
    public void decide(Authentication authentication, Object object,
                       Collection<ConfigAttribute> configAttributes) throws AccessDeniedException {

        // 如果是公共访问权限，直接放行
        if (configAttributes.stream()
                .anyMatch(attr -> "PUBLIC_ACCESS".equals(attr.getAttribute()))) {
            return;
        }

        // 检查用户是否有任一要求的权限
        for (ConfigAttribute configAttribute : configAttributes) {
            String needPermission = configAttribute.getAttribute();

            for (GrantedAuthority authority : authentication.getAuthorities()) {
                if (authority.getAuthority().equals(needPermission)) {
                    return; // 有权限则放行
                }
            }
        }

        throw new AccessDeniedException("权限不足");
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true; // 支持所有ConfigAttribute
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz); // 明确支持FilterInvocation
    }

    // 其他必要方法实现...
}