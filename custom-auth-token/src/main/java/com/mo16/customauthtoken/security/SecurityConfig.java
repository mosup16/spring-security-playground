package com.mo16.customauthtoken.security;

import com.mo16.customauthtoken.security.authenticationProviders.OtpAuthenticationProvider;
import com.mo16.customauthtoken.security.authenticationProviders.TokenAuthenticationProvider;
import com.mo16.customauthtoken.security.authenticationProviders.UsernamePasswordAuthenticationProvider;
import com.mo16.customauthtoken.security.filters.TokenAuthFilter;
import com.mo16.customauthtoken.security.filters.UsernamePasswordFilter;
import com.mo16.customauthtoken.security.otp.OtpService;
import com.mo16.customauthtoken.security.token.TokenService;
import com.mo16.customauthtoken.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final TokenService tokenService;
    private final UserService userService;
    private final OtpService otpService;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private OtpAuthenticationProvider otpAuthenticationProvider;
    @Autowired
    private UsernamePasswordAuthenticationProvider usernamePasswordAuthenticationProvider;
    @Autowired
    private TokenAuthenticationProvider tokenAuthenticationProvider;

    public SecurityConfig(TokenService tokenService, UserService userService,
                          OtpService otpService, PasswordEncoder passwordEncoder) {
        this.tokenService = tokenService;
        this.userService = userService;
        this.otpService = otpService;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        var tokenAuthFilter = new TokenAuthFilter(userService, authenticationManagerBean());
        var usernamePasswordFilter =
                new UsernamePasswordFilter(authenticationManagerBean(), otpService, tokenService);

        http.addFilterBefore(tokenAuthFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterAfter(usernamePasswordFilter, TokenAuthFilter.class);
        http.authorizeRequests()
                .antMatchers("/otp")
                .permitAll()
                .and()
                .authorizeRequests()
                .anyRequest()
                .hasAuthority("admin")
                .and()
                .csrf()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(tokenAuthenticationProvider);
        auth.authenticationProvider(otpAuthenticationProvider);
        auth.authenticationProvider(usernamePasswordAuthenticationProvider);
    }

}
