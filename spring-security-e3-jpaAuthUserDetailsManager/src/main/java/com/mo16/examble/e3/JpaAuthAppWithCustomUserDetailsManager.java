package com.mo16.examble.e3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Function;

@SpringBootApplication
public class JpaAuthAppWithCustomUserDetailsManager {
    public static void main(String[] args) {
        new SpringApplication(JpaAuthAppWithCustomUserDetailsManager.class).run(args);
    }

    @Bean
    public Function<String, String> hello() {
        return name -> "hello " + name;
    }
}
