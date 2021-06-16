package com.bjsxt;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
@EnableDubbo
public class FrontendCartApplication {
    public static void main(String[] args) {
        SpringApplication.run(FrontendCartApplication.class,args);
    }
}
