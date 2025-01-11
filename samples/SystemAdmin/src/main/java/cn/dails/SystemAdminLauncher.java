package cn.dails;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages =  "cn.dails.dao")
@Slf4j
public class SystemAdminLauncher {
    public static void main(String[] args) {
        SpringApplication.run(SystemAdminLauncher.class, args);
    }
}
