package com.mo16.examples.e2.SecurityConfig;

import com.mo16.examples.e2.User.User;
import com.mo16.examples.e2.User.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;

import java.util.Optional;

@AllArgsConstructor
public class UserDetailsManagerImpl implements UserDetailsManager {

    private final UserService userService;
    private final UserFromUserDetailsConverter converter;

    @Override
    public void createUser(UserDetails userDetails) {
        userService.save(converter.convert(userDetails));
    }

    @Override
    public void updateUser(UserDetails userDetails) {
        userService.save(converter.convert(userDetails));
    }

    @Override
    public void deleteUser(String username) {
        userService.deleteByUsername(username);
    }

    @Override
    public void changePassword(String s, String s1) {
//        userService.findByUsername()
    }

    @Override
    public boolean userExists(String username) {
        return userService.existsByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.
                findByUsername(username).
                orElseThrow(() -> new UsernameNotFoundException(
                        String.format("user name : %s could not be authenticated", username)));
        return new UserDetailsImpl(user);
    }
}
