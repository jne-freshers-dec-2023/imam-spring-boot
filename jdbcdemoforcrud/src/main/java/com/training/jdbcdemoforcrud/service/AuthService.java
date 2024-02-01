package com.training.jdbcdemoforcrud.service;

import com.training.jdbcdemoforcrud.model.request.JwtRequest;
import com.training.jdbcdemoforcrud.model.request.UserRequest;
import com.training.jdbcdemoforcrud.model.response.JwtResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

public interface AuthService {
    public ResponseEntity<String> registerUser(UserRequest userRequest, WebRequest webRequest);

    public ResponseEntity<JwtResponse> validateUser(WebRequest webRequest, JwtRequest jwtRequest);

}
