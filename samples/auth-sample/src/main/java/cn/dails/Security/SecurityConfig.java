package cn.dails.Security;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

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

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/",
                                "/mylogin",
                                "/assets/**",

                                "/images/**"
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
////                        .loginPage("/mylogin")         // 自定义登录页
////                        .loginProcessingUrl("/login") // 登录表单提交地址（默认即 /login）
                        .defaultSuccessUrl("/home", true) // 强制跳转到 /home
//                        .permitAll()
                )
                .logout(logout -> logout
//                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                )
                .formLogin(Customizer.withDefaults())
//                .userDetailsService(userDetailsService)
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
}