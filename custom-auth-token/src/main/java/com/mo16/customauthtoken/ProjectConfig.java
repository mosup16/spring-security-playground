package com.mo16.customauthtoken;

import com.mo16.customauthtoken.user.UserRepository;
import com.mo16.customauthtoken.user.UserService;
import com.mo16.customauthtoken.user.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ProjectConfig {
    @Bean
    public UserService userService(UserRepository userRepo, PasswordEncoder passwordEncoder){
        return new UserServiceImpl(userRepo, passwordEncoder);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
