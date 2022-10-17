package com.mo16.customauthtoken.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private final UserRepository repo;
    private final PasswordEncoder encoder;

    public UserServiceImpl(UserRepository repo, PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    @Override
    public List<User> findAll() {
        return repo.findAll();
    }

    @Override
    public User save(User u) {
        u.setPassword(encoder.encode(u.getPassword()));
        return repo.save(u);
    }

    @Override
    public Optional<User> findById(Long id) {
        return repo.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        repo.deleteById(id);
    }

    @Override
    public void delete(User user) {
        repo.delete(user);
    }

    @Override
    public Optional<User> findUserByName(String name) {
        return repo.findUserByName(name);
    }
}
