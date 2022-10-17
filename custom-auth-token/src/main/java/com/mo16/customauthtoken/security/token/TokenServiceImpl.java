package com.mo16.customauthtoken.security.token;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TokenServiceImpl implements TokenService {

    private final TokenRepository repo;
    private final TokenGenerator generator;

    public TokenServiceImpl(TokenRepository repo, TokenGenerator generator) {
        this.repo = repo;
        this.generator = generator;
    }

    @Override
    public List<Token> findAll() {
        return repo.findAll();
    }

    @Override
    public void deleteById(Long aLong) {
        repo.deleteById(aLong);
    }

    @Override
    public Token save(Token token) {
        return repo.save(token);
    }

    @Override
    public Optional<Token> findByUsernameAndActiveIsTrue(String name) {
        return repo.findByUsernameAndActiveIsTrue(name);
    }

    @Override
    @Transactional
    public Token generateAndSave(String username) {
        repo.deleteAllByUsername(username);

        Token token = Token.builder()
                .username(username)
                .token(generator.generate())
                .active(true)
                .created(LocalDateTime.now())
                .build();
        return save(token);
    }
}
