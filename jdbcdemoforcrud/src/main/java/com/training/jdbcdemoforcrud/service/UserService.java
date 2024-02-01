package com.training.jdbcdemoforcrud.service;

import com.training.jdbcdemoforcrud.model.request.UserRequest;
import com.training.jdbcdemoforcrud.model.response.UserResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.UUID;

@Service
public interface UserService {


    List<UserResponse> getAllUsers(WebRequest webRequest);

    UserResponse getUser(String name, WebRequest webRequest);

    UserResponse addUser(UserRequest userRequest, WebRequest webRequest);

    String deleteUser(String name, WebRequest webRequest);
}
