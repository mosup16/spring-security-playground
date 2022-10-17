package com.mo16.customauthtoken.security.token;

import java.util.List;
import java.util.Optional;

public interface TokenService{
    List<Token> findAll();

    void deleteById(Long aLong);

    Token save(Token entity);

    Optional<Token> findByUsernameAndActiveIsTrue(String name);

    Token generateAndSave(String username);
}
