package com.mo16.examples.e2;

import com.mo16.examples.e2.User.User;
import com.mo16.examples.e2.User.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import static com.mo16.examples.e2.applicationRoles.ApplicationRole.*;


@AllArgsConstructor
@Component
@Slf4j
public class DevDataLoader implements CommandLineRunner {

    private final UserService userService;

    @Override
    public void run(String... args) throws Exception {
        loadUsers();
    }

    private void loadUsers() {
        log.info("Loading Users");

        User mosup = User.builder().username("mosup").password("myPass")
                .role(ADMIN)
                .enabled(true)
                .build();

        User mike = User.builder().username("mike").password("myPass")
                .role(USER)
                .enabled(true)
                .build();

        User john = User.builder().username("john").password("myPass")
                .role(USER)
                .enabled(true)
                .build();

        userService.save(mosup);
        userService.save(mike);
        userService.save(john);
    }
}
