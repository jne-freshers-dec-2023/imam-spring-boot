package com.training.jdbcdemoforcrud.service.impl;

import com.training.jdbcdemoforcrud.config.JwtHelper;
import com.training.jdbcdemoforcrud.exception.GlobalException;
import com.training.jdbcdemoforcrud.model.request.JwtRequest;
import com.training.jdbcdemoforcrud.model.request.UserRequest;
import com.training.jdbcdemoforcrud.model.response.JwtResponse;
import com.training.jdbcdemoforcrud.repository.UserRepository;
import com.training.jdbcdemoforcrud.service.AuthService;
import com.training.jdbcdemoforcrud.util.UserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtHelper jwtHelper;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserUtil userUtil;

    @Override
    public ResponseEntity<String> registerUser(UserRequest userRequest, WebRequest webRequest) {
        log.info("Adding new user to user table");
        if (userUtil.checkRole(userRequest.getRole().toUpperCase())) {
            if (!userRepository.existsByName(userRequest.getUserName())) {
                userRepository.save(userUtil.getUser(userRequest));
                return new ResponseEntity<>("Congratulations!! You are Registration is Successful.", HttpStatus.CREATED);
            } else {
                throw new GlobalException("Provided Username is Not Available", HttpStatus.NOT_ACCEPTABLE, new Date(), webRequest.getDescription(false));
            }
        } else {
            throw new GlobalException("Provided Role is Not Valid", HttpStatus.NOT_FOUND, new Date(), webRequest.getDescription(false));
        }
    }

    @Override
    public ResponseEntity<JwtResponse> validateUser(WebRequest webRequest, JwtRequest jwtRequest) {
        JwtResponse jwtResponse = new JwtResponse();
        try {
            doAuthenticate(jwtRequest.getUserName(), jwtRequest.getPassword());
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(jwtRequest.getUserName());
            String token = jwtHelper.generateToken(userDetails);
            jwtResponse.setUsername(userDetails.getUsername());
            jwtResponse.setToken(token);
        } catch (BadCredentialsException e) {
            throw new GlobalException("Invalid Username or Password!", HttpStatus.NOT_FOUND, new Date(), webRequest.getDescription(false));
        }
        return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
    }

    private void doAuthenticate(String email, String password) throws BadCredentialsException {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        authenticationManager.authenticate(authentication);
    }

}
