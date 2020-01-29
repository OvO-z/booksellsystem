package com.bss.order.restapi;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@ComponentScan(basePackages = "com.bss")
public class OrderRestApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderRestApiApplication.class, args);
    }
}
