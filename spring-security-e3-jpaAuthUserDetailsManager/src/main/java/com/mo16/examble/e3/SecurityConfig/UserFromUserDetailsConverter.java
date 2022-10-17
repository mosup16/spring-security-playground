package com.mo16.examble.e3.SecurityConfig;

import com.mo16.examble.e3.User.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserFromUserDetailsConverter {

    User convert(UserDetails userDetails);
}
