package com.training.jdbcdemoforcrud.controller;

import com.training.jdbcdemoforcrud.model.request.UserRequest;
import com.training.jdbcdemoforcrud.model.response.UserResponse;
import com.training.jdbcdemoforcrud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/collage/admin")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponse>> getUsers() {
        return (new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable UUID uuid) {
        return (new ResponseEntity<>(userService.getUser(uuid), HttpStatus.OK));
    }

    @PostMapping
    public ResponseEntity<UserResponse> addUser(@RequestBody UserRequest userRequest) {
        return (new ResponseEntity<>(userService.addUser(userRequest),HttpStatus.CREATED));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable UUID uuid) {
        return (new ResponseEntity<>(userService.deleteUser(uuid),HttpStatus.OK));
    }

}