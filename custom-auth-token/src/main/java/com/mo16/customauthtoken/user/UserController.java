package com.mo16.customauthtoken.user;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @GetMapping("/me")
    public AuthenticatedUser getAuthenticatedUser(){
        var user = (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication();
        System.out.println("user = " + user);
        return user;
    }
}
