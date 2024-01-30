package com.training.jdbcdemoforcrud.controller;

import com.training.jdbcdemoforcrud.config.JwtHelper;
import com.training.jdbcdemoforcrud.model.request.JwtRequest;
import com.training.jdbcdemoforcrud.model.response.JwtResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    JwtHelper jwtHelper;
    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public JwtResponse login(@RequestBody JwtRequest jwtRequest) {
        JwtResponse jwtResponse = new JwtResponse();
        try {
            doAuthenticate(jwtRequest.getUserName(), jwtRequest.getPassword());
            UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getUserName());
            String token = jwtHelper.generateToken(userDetails);
            jwtResponse.setUsername(userDetails.getUsername());
            jwtResponse.setToken(token);
        } catch (BadCredentialsException e) {
            System.err.println("Invalid Username or Password!");
        }
        return jwtResponse;

    }

    private void doAuthenticate(String email, String password) throws BadCredentialsException {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        authenticationManager.authenticate(authentication);
    }
}
