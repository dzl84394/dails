package cn.dails.Security;

import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class DynamicAccessDecisionManager implements AccessDecisionVoter<FilterInvocation> {

    @Override
    public int vote(Authentication authentication, FilterInvocation fi,
                    Collection<ConfigAttribute> attributes) {

        // 处理PERMIT_ALL
        if (attributes.stream().anyMatch(attr -> "PERMIT_ALL".equals(attr.getAttribute()))) {
            return ACCESS_GRANTED;
        }

        // 处理认证要求
        if (authentication == null || !authentication.isAuthenticated()) {
            if (attributes.stream().anyMatch(attr -> "AUTH_REQUIRED".equals(attr.getAttribute()))) {
                return ACCESS_DENIED;
            }
            return ACCESS_ABSTAIN;
        }

        // 处理角色验证
        return attributes.stream()
                .filter(attr -> attr.getAttribute().startsWith("ROLE_"))
                .anyMatch(attr -> authentication.getAuthorities().contains(attr))
                ? ACCESS_GRANTED : ACCESS_DENIED;
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}