package com.mo16.customauthtoken.security.otp;

import org.springframework.stereotype.Component;

@Component
public class ConsoleOtpPublisher implements OtpPublisher {
    @Override
    public void publish(Otp otp) {
        System.out.printf("Hi %s, here is your otp %s%n", otp.getUsername(), otp.getOtp());
    }
}
