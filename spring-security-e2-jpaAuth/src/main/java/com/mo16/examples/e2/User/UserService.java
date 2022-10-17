package com.mo16.examples.e2.User;

import java.util.Optional;

public interface UserService {

    Optional<User> findByUsername(String username);

    User save(User entity);

    Optional<User> findById(Long aLong);

    boolean existsById(Long aLong);

    boolean existsByUsername(String username);

    void deleteById(Long aLong);

    void delete(User entity);

    void deleteByUsername(String username);
}
