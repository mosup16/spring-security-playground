package com.mo16.customauthtoken.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();

    User save(User s);

    Optional<User> findById(Long id);

    void deleteById(Long id);

    void delete(User user);

    Optional<User> findUserByName(String name);
}
