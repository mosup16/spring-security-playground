package com.mo16.examble.e3.SecurityConfig;

import com.mo16.examble.e3.User.User;
import org.springframework.security.core.userdetails.UserDetails;

public class UserFromUserDetailsImplConverter implements UserFromUserDetailsConverter {

    public User convert(UserDetails userDetails) {
        var userDetailsImpl = (UserDetailsImpl) userDetails;
        return userDetailsImpl.getUser();
    }
}
