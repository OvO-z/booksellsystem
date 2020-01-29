package com.bss.user.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by QAQ on 2019/5/9
 */
@SpringBootApplication
@EnableEurekaClient
@ComponentScan(basePackages = "com.bss")
public class UserRestApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserRestApiApplication.class, args);
    }
}