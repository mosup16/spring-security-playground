package com.mo16.customauthtoken.security.filters;

import com.mo16.customauthtoken.security.TokenAuthentication;
import com.mo16.customauthtoken.security.token.Token;
import com.mo16.customauthtoken.security.token.TokenService;
import com.mo16.customauthtoken.user.AuthenticatedUser;
import com.mo16.customauthtoken.user.User;
import com.mo16.customauthtoken.user.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class TokenAuthFilter extends OncePerRequestFilter {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    public TokenAuthFilter(UserService userService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        String username = req.getHeader("username");
        String token = req.getHeader("authorization");
        if (username == null || token == null) {
            filterChain.doFilter(req, res);
            return;
        }
        // authenticate user
        Authentication authentication =
                authenticationManager.authenticate(new TokenAuthentication(username, token));

        // add to security context
        User user = userService.findUserByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("user couldn't be found"));
        var authenticatedUser = new AuthenticatedUser(user, authentication.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticatedUser);

        // continue the chain
        filterChain.doFilter(req, res);
    }
}
