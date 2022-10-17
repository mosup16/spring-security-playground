package com.mo16.examples.e2.SecurityConfig;

import com.mo16.examples.e2.User.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserFromUserDetailsConverter {

    User convert(UserDetails userDetails);
}
