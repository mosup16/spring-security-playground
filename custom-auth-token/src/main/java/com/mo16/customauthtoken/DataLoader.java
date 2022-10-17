package com.mo16.customauthtoken;

import com.mo16.customauthtoken.user.User;
import com.mo16.customauthtoken.user.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private UserService userService;

    public DataLoader(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        User user = User.builder().name("mosup emad")
                .password("my-pass")
                .build();
        userService.save(user);
    }
}
