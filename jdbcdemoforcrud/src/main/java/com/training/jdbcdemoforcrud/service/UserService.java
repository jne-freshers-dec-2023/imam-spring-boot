package com.training.jdbcdemoforcrud.service;

import com.training.jdbcdemoforcrud.model.request.UserRequest;
import com.training.jdbcdemoforcrud.model.response.UserResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface UserService {


    List<UserResponse> getAllUsers();
    UserResponse getUser(UUID uuid);
    UserResponse addUser(UserRequest userRequest);
    String deleteUser(UUID uuid);
}
