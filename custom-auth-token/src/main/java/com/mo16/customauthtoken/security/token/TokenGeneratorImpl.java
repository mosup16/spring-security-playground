package com.mo16.customauthtoken.security.token;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TokenGeneratorImpl implements TokenGenerator {
    @Override
    public String generate() {
        return UUID.randomUUID().toString();
    }
}
