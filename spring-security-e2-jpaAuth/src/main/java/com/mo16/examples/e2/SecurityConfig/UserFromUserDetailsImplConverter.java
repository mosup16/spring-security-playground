package com.mo16.examples.e2.SecurityConfig;

import com.mo16.examples.e2.User.User;
import org.springframework.security.core.userdetails.UserDetails;

public class UserFromUserDetailsImplConverter implements UserFromUserDetailsConverter {

    public User convert(UserDetails userDetails) {
        var userDetailsImpl = (UserDetailsImpl) userDetails;
        return userDetailsImpl.getUser();
    }
}
