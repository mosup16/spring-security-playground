package com.mo16.customauthtoken.security.otp;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OtpServiceImpl implements OtpService {

    private final OtpRepository repo;
    private final OtpGenerator generator;
    private final OtpPublisher otpPublisher;

    public OtpServiceImpl(OtpRepository repo, OtpGenerator generator, OtpPublisher otpPublisher) {
        this.repo = repo;
        this.generator = generator;
        this.otpPublisher = otpPublisher;
    }

    @Override
    public List<Otp> findAll() {
        return repo.findAll();
    }

    @Override
    public void deleteById(Long aLong) {
        repo.deleteById(aLong);
    }

    @Override
    public Otp save(Otp entity) {
        return repo.save(entity);
    }

    @Override
    public Optional<Otp> findByUsernameAndActiveIsTrue(String name) {
        return repo.findByUsernameAndActiveIsTrue(name);
    }

    @Override
    @Transactional
    public void generateAndSend(String username) {
        repo.deleteAllByUsername(username);
        var otp = Otp.builder().username(username).build();
        otp.setOtp(generator.generate());
        otp.setCreated(LocalDateTime.now());
        otp.setActive(true);
        otp.setSent(true);
        save(otp);
        otpPublisher.publish(otp);
    }

    @Override
    public void deleteAllByUsername(String username) {
        repo.deleteAllByUsername(username);
    }
}
