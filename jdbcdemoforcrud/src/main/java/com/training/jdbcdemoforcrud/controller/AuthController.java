package com.training.jdbcdemoforcrud.controller;

import com.training.jdbcdemoforcrud.model.request.JwtRequest;
import com.training.jdbcdemoforcrud.model.request.UserRequest;
import com.training.jdbcdemoforcrud.model.response.JwtResponse;
import com.training.jdbcdemoforcrud.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@RestController
@RequestMapping(path = "/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping(path = "/login")
    public ResponseEntity<JwtResponse> loginUser(WebRequest webRequest, @RequestBody JwtRequest jwtRequest) {
        return authService.validateUser(webRequest, jwtRequest);
    }

    @PostMapping(path = "/signup")
    public ResponseEntity<String> registerUser(WebRequest webRequest, @RequestBody UserRequest userRequest) {
        return authService.registerUser(userRequest, webRequest);
    }
}
