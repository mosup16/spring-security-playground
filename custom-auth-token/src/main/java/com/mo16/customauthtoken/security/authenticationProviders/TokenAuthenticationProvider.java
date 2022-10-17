package com.mo16.customauthtoken.security.authenticationProviders;

import com.mo16.customauthtoken.security.TokenAuthentication;
import com.mo16.customauthtoken.security.token.Token;
import com.mo16.customauthtoken.security.token.TokenService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TokenAuthenticationProvider implements AuthenticationProvider {

    private final TokenService tokenService;

    public TokenAuthenticationProvider(TokenService tokenService) {
        this.tokenService = tokenService;
    }


    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        String username = (String) auth.getPrincipal();
        Token expectedToken = tokenService.findByUsernameAndActiveIsTrue(username)
                .orElseThrow(() -> new BadCredentialsException("invalid user name can't get a token"));

        // validate token
        String token = (String) auth.getCredentials();
        if (expectedToken.getToken().equals(token)) {
            return new TokenAuthentication(username,token,
                    List.of(new SimpleGrantedAuthority("admin")));
        }
        throw new BadCredentialsException("token is invalid");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return TokenAuthentication.class.equals(authentication);
    }
}
