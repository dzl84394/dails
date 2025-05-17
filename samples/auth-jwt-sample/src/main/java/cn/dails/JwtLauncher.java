package cn.dails;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@MapperScan(basePackages =  "cn.dails.dao")
@EnableWebSecurity
@Slf4j
public class JwtLauncher {
    public static void main(String[] args) {
        SpringApplication.run(JwtLauncher.class, args);
    }
}
