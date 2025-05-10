package cn.dails.Security;


import cn.dails.dao.entity.SysRoleEntity;
import com.alibaba.fastjson.JSONObject;
import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author dzl
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    @Autowired
    private  UserDetailsService userDetailsService;

    private final PasswordEncoder passwordEncoder;
    private final DynamicSecurityMetadataSource securityMetadataSource;
    private final DynamicAccessDecisionManager accessDecisionManager;
//PERMIT_ALL AUTH_REQUIRED，默认放行PERMIT_ALL，拒绝是DENY
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/","/login", "/logout",
                                "/home",
                                "/mylogin",
                                "/assets/**",
                                "/public/**",
                                "/images/**"
                        ).permitAll()
                        .anyRequest().authenticated()
                )

                .formLogin(form -> form
                        .loginPage("/login")         // 自定义登录页
                        .loginProcessingUrl("/login") // 登录表单提交地址（默认即 /login）
//                                .successHandler(authenticationSuccessHandler())//可以返回json方便前后端分离
//                                .failureHandler(authenticationFailureHandler())
                                .defaultSuccessUrl("/home", true) // 强制跳转到 /home
                                .permitAll()
                )
                .addFilterBefore(
                        dynamicSecurityFilter(),
                       AuthorizationFilter.class
                )
                .userDetailsService(userDetailsService)
//                .formLogin(Customizer.withDefaults())
                .logout(logout -> logout
//                        .logoutUrl("/logout")
                            .logoutSuccessUrl("/login?logout")
//                            .logoutSuccessHandler(logoutSuccessHandler())
                )
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(authenticationEntryPoint())
                        .accessDeniedHandler(accessDeniedHandler())
                )


                .csrf(csrf -> csrf.disable());
        return http.build();
    }



//    @Bean
//    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
//        // 打印加密后的密码（确保一致性）
//        String userPassword = passwordEncoder.encode("123");
//        String adminPassword =passwordEncoder.encode("123");
//        System.out.println("====User Password: " + userPassword);
//        System.out.println("=====Admin Password: " + adminPassword);
//
//        UserDetails user = User.builder()
//                .username("user")
//                .password(userPassword)
//                .roles("USER")
//                .build();
//
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password(adminPassword)
//                .roles("ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(user, admin);
//    }

    // 关键：定义 DaoAuthenticationProvider
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService); // 关联 UserDetailsService
        provider.setPasswordEncoder(passwordEncoder); // 关联 PasswordEncoder
        return provider;
    }

    // 关键：暴露 AuthenticationManager
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

//    @Bean
//    public AuthenticationSuccessHandler authenticationSuccessHandler() {
//        return (request, response, authentication) -> {
//            SecurityUserDetails userDetails = (SecurityUserDetails) authentication.getPrincipal();
//
//            Map<String, Object> result = Map.of(
//                    "code", 200,
//                    "message", "登录成功",
//                    "data", Map.of(
//                            "username", userDetails.getUsername(),
//                            "roles", userDetails.getRoles().stream()
//                                    .map(SysRoleEntity::getRoleCode)
//                                    .collect(Collectors.toList())
//                    )
//            );
//
//            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//            response.getWriter().write(JSONObject.toJSONString(result));
//        };
//    }

//    @Bean
//    public AuthenticationFailureHandler authenticationFailureHandler() {
//        return (request, response, exception) -> {
//            Map<String, Object> result = Map.of(
//                    "code", 401,
//                    "message", "登录失败: " + exception.getMessage()
//            );
//
//            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//            response.setStatus(HttpStatus.UNAUTHORIZED.value());
//            response.getWriter().write(JSONObject.toJSONString(result));
//        };
//    }

//    @Bean
//    public LogoutSuccessHandler logoutSuccessHandler() {
//        return (request, response, authentication) -> {
//            Map<String, Object> result = Map.of(
//                    "code", 200,
//                    "message", "退出成功"
//            );
//
//            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//            response.getWriter().write(JSONObject.toJSONString(result));
//        };
//    }

//    @Bean
//    public AuthenticationEntryPoint authenticationEntryPoint() {
//        return (request, response, authException) -> {
//            Map<String, Object> result = Map.of(
//                    "code", 401,
//                    "message", "未经授权: " + authException.getMessage()
//            );
//
////            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
////            response.setStatus(HttpStatus.UNAUTHORIZED.value());
////            response.getWriter().write(JSONObject.toJSONString(result));
//            response.sendRedirect("/login"); // 确保这里正确重定向
//        };
//    }
    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        // 指定登录页面的URL
        return new LoginUrlAuthenticationEntryPoint("/login");
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return (request, response, accessDeniedException) -> {
            Map<String, Object> result = Map.of(
                    "code", 403,
                    "message", "权限不足: " + accessDeniedException.getMessage()
            );

            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.getWriter().write(JSONObject.toJSONString(result));
        };
    }

    @Bean
    public Filter dynamicSecurityFilter() {
        return new DynamicSecurityFilter(securityMetadataSource, accessDecisionManager);
    }
}