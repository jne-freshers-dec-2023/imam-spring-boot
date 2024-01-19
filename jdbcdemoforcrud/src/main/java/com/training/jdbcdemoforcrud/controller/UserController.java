package com.training.jdbcdemoforcrud.controller;

import com.training.jdbcdemoforcrud.model.request.UserRequest;
import com.training.jdbcdemoforcrud.model.response.UserResponse;
import com.training.jdbcdemoforcrud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/collage/admin")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(path = "/all")
    public ResponseEntity<List<UserResponse>> getUsers(WebRequest webRequest) {
        return (new ResponseEntity<>(userService.getAllUsers(webRequest), HttpStatus.OK));
    }

    @GetMapping(path = "/single")
    public ResponseEntity<UserResponse> getUser(WebRequest webRequest, @RequestParam(name = "uuid") UUID uuid) {
        return (new ResponseEntity<>(userService.getUser(uuid, webRequest), HttpStatus.OK));
    }

    @PostMapping(path = "/add")
    public ResponseEntity<UserResponse> addUser(WebRequest webRequest, @RequestBody UserRequest userRequest) {
        return (new ResponseEntity<>(userService.addUser(userRequest, webRequest), HttpStatus.CREATED));
    }

    @DeleteMapping(path = "/delete")
    public ResponseEntity<String> deleteUser(WebRequest webRequest, @RequestParam(name = "uuid") UUID uuid) {
        return (new ResponseEntity<>(userService.deleteUser(uuid, webRequest), HttpStatus.OK));
    }

}