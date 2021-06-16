package com.bjsxt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
@MapperScan("com.bjsxt.mapper")

public class FrontendPassportApplication {
    public static void main(String[] args) {
        SpringApplication.run(FrontendPassportApplication.class,args);
    }
}
