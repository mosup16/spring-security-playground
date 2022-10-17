package com.mo16.customauthtoken.security.otp;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OtpRepository extends JpaRepository<Otp, Long> {
    Optional<Otp> findByUsernameAndActiveIsTrue(String name);
    void deleteAllByUsername(String username);
}
