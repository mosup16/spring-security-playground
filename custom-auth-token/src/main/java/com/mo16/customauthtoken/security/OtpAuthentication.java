package com.mo16.customauthtoken.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class OtpAuthentication extends UsernamePasswordAuthenticationToken {

    public OtpAuthentication(String username, String otp) {
        super(username, otp);
    }

    public OtpAuthentication(String username, String otp, Collection<? extends GrantedAuthority> authorities) {
        super(username, otp, authorities);
    }
}
