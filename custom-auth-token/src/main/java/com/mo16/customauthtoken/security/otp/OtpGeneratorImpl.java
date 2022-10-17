package com.mo16.customauthtoken.security.otp;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class OtpGeneratorImpl implements OtpGenerator {
    @Override
    public int generate() {
        return new Random().nextInt(99999);
    }
}
