package com.mo16.customauthtoken.security.authenticationProviders;

import com.mo16.customauthtoken.security.OtpAuthentication;
import com.mo16.customauthtoken.security.UsernamePasswordAuthentication;
import com.mo16.customauthtoken.security.otp.Otp;
import com.mo16.customauthtoken.security.otp.OtpService;
import com.mo16.customauthtoken.user.User;
import com.mo16.customauthtoken.user.UserService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class OtpAuthenticationProvider implements AuthenticationProvider {

    private final UserService userService;
    private final OtpService otpService;

    public OtpAuthenticationProvider(UserService userService, OtpService otpService) {
        this.userService = userService;
        this.otpService = otpService;
    }

    @Override
    @Transactional
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        String otp = (String) auth.getCredentials();
        String username = (String) auth.getPrincipal();
        Otp expectedOtp = otpService.findByUsernameAndActiveIsTrue(username)
                .orElseThrow(() -> new BadCredentialsException("bad username"));
        if (String.valueOf(expectedOtp.getOtp()).equals(otp)) {
            otpService.deleteAllByUsername(username);
            return new OtpAuthentication(username,otp, List.of(new SimpleGrantedAuthority("admin")));
        }
        throw  new BadCredentialsException("bad username password authentication");

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return OtpAuthentication.class.equals(authentication);
    }
}
