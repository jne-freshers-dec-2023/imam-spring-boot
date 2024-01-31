package com.training.jdbcdemoforcrud.controller;

import com.training.jdbcdemoforcrud.config.JwtHelper;
import com.training.jdbcdemoforcrud.exception.GlobalException;
import com.training.jdbcdemoforcrud.model.request.JwtRequest;
import com.training.jdbcdemoforcrud.model.request.UserRequest;
import com.training.jdbcdemoforcrud.model.response.JwtResponse;
import com.training.jdbcdemoforcrud.model.response.UserResponse;
import com.training.jdbcdemoforcrud.service.UserService;
import com.training.jdbcdemoforcrud.service.impl.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestController
@RequestMapping(path = "/auth")
public class AuthController {

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Autowired
    JwtHelper jwtHelper;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @PostMapping(path = "/login")
    public JwtResponse login(WebRequest webRequest,@RequestBody JwtRequest jwtRequest) {
        JwtResponse jwtResponse = new JwtResponse();
        try {
            doAuthenticate(jwtRequest.getUserName(), jwtRequest.getPassword());
            System.err.println("==================================");
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(jwtRequest.getUserName());
            String token = jwtHelper.generateToken(userDetails);
            jwtResponse.setUsername(userDetails.getUsername());
            jwtResponse.setToken(token);
        } catch (BadCredentialsException e) {
            throw new GlobalException("Invalid Username or Password!", HttpStatus.NOT_FOUND, new Date(), webRequest.getDescription(false));
        }
        return jwtResponse;
    }

    @PostMapping(path = "/signup")
    public ResponseEntity<UserResponse> registerUser(WebRequest webRequest, @RequestBody UserRequest userRequest) {
        return (new ResponseEntity<>(userService.addUser(userRequest, webRequest), HttpStatus.CREATED));
    }

    private void doAuthenticate(String email, String password) throws BadCredentialsException {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        authenticationManager.authenticate(authentication);
    }
}
