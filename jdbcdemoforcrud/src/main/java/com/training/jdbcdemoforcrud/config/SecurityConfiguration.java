package com.training.jdbcdemoforcrud.config;

import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

public class SecurityConfiguration {
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        //Ask to Authenticate all http request
        httpSecurity.authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> authorizationManagerRequestMatcherRegistry.anyRequest().authenticated());
        //provide basic login page for authentication is not done
        httpSecurity.httpBasic(Customizer.withDefaults());
        //Disable csrf
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        return httpSecurity.build();
    }
}
