package com.mo16.examples.e2.applicationRoles;

import com.mo16.examples.e2.applicationRoles.ApplicationAuthority;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.LinkedList;
import java.util.List;

import static com.mo16.examples.e2.applicationRoles.ApplicationAuthority.READ;
import static com.mo16.examples.e2.applicationRoles.ApplicationAuthority.WRITE;

public enum ApplicationRole {
    ADMIN(READ, WRITE), USER(READ);

    private ApplicationAuthority[] authorities;

    private ApplicationRole(ApplicationAuthority... authorities) {
        this.authorities = authorities;
    }

    public List<GrantedAuthority> getGrantedAuthorities() {
        List<GrantedAuthority> grantedAuthorities = new LinkedList<>();
        for (ApplicationAuthority authority : authorities) {
            grantedAuthorities.add(new SimpleGrantedAuthority(authority.name()));
        }
        return grantedAuthorities;
    }
}
