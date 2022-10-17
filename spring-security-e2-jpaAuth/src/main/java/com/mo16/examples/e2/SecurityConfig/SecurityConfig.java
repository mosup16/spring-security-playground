package com.mo16.examples.e2.SecurityConfig;

import com.mo16.examples.e2.User.User;
import com.mo16.examples.e2.User.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService jpaUserDetailsService(UserService userService) {
        return username -> {
            User user = userService.
                    findByUsername(username).
                    orElseThrow(() -> new UsernameNotFoundException(
                            String.format("user name : %s could not be authenticated", username)));
            return new UserDetailsImpl(user);
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserFromUserDetailsConverter converter() {
        return new UserFromUserDetailsImplConverter();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();
        http.csrf().disable();
        http.authorizeRequests().anyRequest().authenticated();
    }
}

