package com.mo16.customauthtoken.security.otp;

import java.util.List;
import java.util.Optional;

public interface OtpService {
    List<Otp> findAll();

    void deleteById(Long aLong);

    Otp save(Otp entity);

    Optional<Otp> findByUsernameAndActiveIsTrue(String name);

    void generateAndSend(String username);

    void deleteAllByUsername(String username);
}
