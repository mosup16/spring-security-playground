package com.mo16.customauthtoken.security.filters;

import com.mo16.customauthtoken.security.OtpAuthentication;
import com.mo16.customauthtoken.security.UsernamePasswordAuthentication;
import com.mo16.customauthtoken.security.otp.Otp;
import com.mo16.customauthtoken.security.otp.OtpService;
import com.mo16.customauthtoken.security.token.Token;
import com.mo16.customauthtoken.security.token.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UsernamePasswordFilter extends OncePerRequestFilter {

    private final AuthenticationManager authenticationManager;
    private final OtpService otpService;
    private final TokenService tokenService;

    public UsernamePasswordFilter(AuthenticationManager authenticationManager, OtpService otpService, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.otpService = otpService;
        this.tokenService = tokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String username = request.getHeader("username");
        String password = request.getHeader("password");
        String otp = request.getHeader("otp");

        if (otp == null) {
            // validate user & pass
            authenticationManager.authenticate(new UsernamePasswordAuthentication(username, password));
            otpService.generateAndSend(username);

        } else {
            // validate otp
            authenticationManager.authenticate(new OtpAuthentication(username, otp));
            //generate token
            Token token = tokenService.generateAndSave(username);
            response.setHeader("authorization", token.getToken());
        }

    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !"/otp".equals(request.getRequestURI());
    }
}
