package cn.dails.Security;

import cn.dails.dao.entity.SysRoleEntity;
import com.alibaba.fastjson2.JSONObject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        SecurityUserDetails userDetails = (SecurityUserDetails) authentication.getPrincipal();

        Map<String, Object> result = Map.of(
                "code", 200,
                "message", "登录成功",
                "data", Map.of(
                        "username", userDetails.getUsername(),
                        "roles", userDetails.getRoles().stream()
                                .map(SysRoleEntity::getRoleCode)
                                .collect(Collectors.toList())
                )
        );

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(JSONObject.toJSONString(request));
    }
}
