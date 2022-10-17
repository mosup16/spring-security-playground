package com.mo16.customauthtoken.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class UsernamePasswordAuthentication extends UsernamePasswordAuthenticationToken {

    public UsernamePasswordAuthentication(Object username, Object password) {
        super(username, password);
    }

    public UsernamePasswordAuthentication(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }
}
