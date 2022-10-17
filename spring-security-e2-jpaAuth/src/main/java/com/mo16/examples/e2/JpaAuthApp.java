package com.mo16.examples.e2;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Function;
import java.util.function.Supplier;

@SpringBootApplication
public class JpaAuthApp {
    public static void main(String[] args) {
        new SpringApplication(JpaAuthApp.class).run(args);
    }

    @Bean
    public Function<String, String> hello() {
        return name -> "hello " + name;
    }
}
