package com.mo16.customauthtoken.security.authenticationProviders;

import com.mo16.customauthtoken.security.UsernamePasswordAuthentication;
import com.mo16.customauthtoken.user.User;
import com.mo16.customauthtoken.user.UserService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider {

    private final UserService userService;
    private final PasswordEncoder encoder;

    public UsernamePasswordAuthenticationProvider(UserService userService, PasswordEncoder encoder) {
        this.userService = userService;
        this.encoder = encoder;
    }

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        String pass = (String) auth.getCredentials();
        String username = (String) auth.getPrincipal();
        var user = userService.findUserByName(username);
        User u = user.orElseThrow(() -> new BadCredentialsException("bad username"));
        if (encoder.matches(pass, u.getPassword())) {
            return new UsernamePasswordAuthentication(username,pass,
                    List.of(new SimpleGrantedAuthority("admin")));
        }
        throw new BadCredentialsException("bad username password authentication");

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthentication.class.equals(authentication);
    }
}
