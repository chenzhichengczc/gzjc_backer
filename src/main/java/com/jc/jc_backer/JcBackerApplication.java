package com.jc.jc_backer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
<<<<<<< HEAD
=======
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
>>>>>>> 1089cb4b7854570e9d0504c4dd9b9c460bb2c648

@SpringBootApplication
@MapperScan("com.jc.jc_backer.modules.*.mapper")
@SpringBootConfiguration
@EnableScheduling
public class JcBackerApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(JcBackerApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return super.configure(builder);
    }

}
