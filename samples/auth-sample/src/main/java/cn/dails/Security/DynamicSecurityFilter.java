package cn.dails.Security;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;

public class DynamicSecurityFilter extends OncePerRequestFilter {

    private final FilterInvocationSecurityMetadataSource securityMetadataSource;
    private final AccessDecisionManager accessDecisionManager;

    public DynamicSecurityFilter(FilterInvocationSecurityMetadataSource securityMetadataSource,
                                 AccessDecisionManager accessDecisionManager) {
        this.securityMetadataSource = securityMetadataSource;
        this.accessDecisionManager = accessDecisionManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        // 1. 放行公开路径
        if (isPublicPath(request)) {
            filterChain.doFilter(request, response);
            return;
        }

        // 2. 动态权限检查
        try {
            // 2.1 获取当前请求所需的权限
            Collection<ConfigAttribute> attributes = securityMetadataSource.getAttributes(request);

            // 2.2 获取当前认证信息
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            // 2.3 权限决策
            accessDecisionManager.decide(authentication, request, attributes);

            // 2.4 放行通过权限检查的请求
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            // 3. 处理权限异常
            handleAccessDenied(response, e);
        }
    }

    private boolean isPublicPath(HttpServletRequest request) {
        String path = request.getRequestURI();
        return path.startsWith("/login") ||
                path.startsWith("/public/") ||
                path.startsWith("/assets/");
    }

    private void handleAccessDenied(HttpServletResponse response, Exception e) throws IOException {
        if (e instanceof AccessDeniedException) {
            response.sendRedirect("/login?denied");
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Security check failed");
        }
    }
}