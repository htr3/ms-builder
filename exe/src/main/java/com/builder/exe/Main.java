package com.builder.exe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.log.LogMessage;

//@PropertySource("classpath:module-deployment.properties")
@SpringBootApplication(scanBasePackages = {"org.builder.api", "com.builder.api.resources", "com.builder.resource", "com.builder"})
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        System.out.println("Spring Boot application started. Scanning packages: org.msbuilder.api, com.builder.api.resources");
    }
}