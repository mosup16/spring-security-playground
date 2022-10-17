package com.mo16.customauthtoken.user;

import com.mo16.customauthtoken.security.UsernamePasswordAuthentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class AuthenticatedUser extends UsernamePasswordAuthentication {
    private final User user;
    private final Collection<GrantedAuthority> authorities;

    public AuthenticatedUser(User user, Collection<? extends GrantedAuthority> authorities) {
        super(user.getName(), null, authorities);
        this.user = user;
        this.user.setPassword(null);
        this.authorities = (Collection<GrantedAuthority>) authorities;
    }

    public User getUser() {
        return user;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return authorities;
    }
}
