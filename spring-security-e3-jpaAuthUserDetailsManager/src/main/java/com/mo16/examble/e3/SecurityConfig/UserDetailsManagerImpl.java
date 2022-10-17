package com.mo16.examble.e3.SecurityConfig;


import com.mo16.examble.e3.User.User;
import com.mo16.examble.e3.User.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

@AllArgsConstructor
//@Component
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
    public void changePassword(String oldPassword, String newPassword) {
        var authentication = Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .orElseThrow(() -> new AccessDeniedException(
                        "Can't change password as no Authentication object found in context for current user."));
        User currentUser = userService.findByUsername(authentication.getName())
                .orElseThrow(() -> new IllegalStateException("the current authenticated user wasn't found"));
        currentUser.setPassword(newPassword);
        User savedUser = userService.save(currentUser);
        var token = new UsernamePasswordAuthenticationToken(new UserDetailsImpl(savedUser), newPassword);
        token.setDetails(authentication.getDetails());
        SecurityContextHolder.getContext().setAuthentication(token);

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
