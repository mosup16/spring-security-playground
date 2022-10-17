package com.mo16.sso;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class SSOApp {
    public static void main(String[] args) {
        new SpringApplication(SSOApp.class).run(args);
    }
}
