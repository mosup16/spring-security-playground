package com.mo16.sso;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository(
            @Value("${github.client.id}") String clientId,
            @Value("${github.client.secret}") String clientSecret) {
        return new InMemoryClientRegistrationRepository(githubClientRegistration(clientId, clientSecret));
    }

    private ClientRegistration githubClientRegistration(String clientId, String clientSecret) {
        return CommonOAuth2Provider
                .GITHUB.getBuilder("github")
                .clientId(clientId)
                .clientSecret(clientSecret)
                .build();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.oauth2Login();
        http.authorizeRequests()
                .anyRequest()
                .authenticated();
    }
}
