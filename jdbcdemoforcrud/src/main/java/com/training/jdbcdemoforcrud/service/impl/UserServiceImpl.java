package com.training.jdbcdemoforcrud.service.impl;

import com.training.jdbcdemoforcrud.entity.User;
import com.training.jdbcdemoforcrud.exception.GlobalException;
import com.training.jdbcdemoforcrud.model.request.UserRequest;
import com.training.jdbcdemoforcrud.model.response.UserResponse;
import com.training.jdbcdemoforcrud.repository.UserRepository;
import com.training.jdbcdemoforcrud.service.UserService;
import com.training.jdbcdemoforcrud.util.UserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

import java.util.*;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserUtil userUtil;

    @Override
    public List<UserResponse> getAllUsers(WebRequest webRequest) {
        log.info("Retrieving All Users from users table");
        List<User> userList=userRepository.findAll();
        if (!userList.isEmpty()){
            List<UserResponse> userResponseList=new ArrayList<>();
            for (User user:userList){
                userResponseList.add(userUtil.toUserResponse(user));
            }
            return userResponseList;
        } else {
            throw (new GlobalException("Users are Not Available", HttpStatus.NOT_FOUND, new Date(), webRequest.getDescription(false)));
        }
    }

    @Override
    public UserResponse getUser(String name, WebRequest webRequest) {
        log.info("Retrieving a User from user table");
        Optional<User> optionalUser=userRepository.findByName(name);
        if(optionalUser.isPresent()){
            return userUtil.toUserResponse(optionalUser.get());
        } else {
            throw (new GlobalException("User not found with provided name:" + name, HttpStatus.NOT_FOUND, new Date(), webRequest.getDescription(false)));
        }
    }

    @Override
    public UserResponse addUser(UserRequest userRequest, WebRequest webRequest) {
        log.info("Adding new user to user table");
        if (userUtil.checkRole(userRequest.getRole().toUpperCase())) {
            if (!userRepository.existsByName(userRequest.getUserName())) {
                return userUtil.toUserResponse(userRepository.save(userUtil.getUser(userRequest)));
            } else {
                throw new GlobalException("Provided Username is Not Available", HttpStatus.NOT_ACCEPTABLE, new Date(), webRequest.getDescription(false));
            }
        } else {
            throw new GlobalException("Provided Role is Not Valid", HttpStatus.NOT_FOUND, new Date(), webRequest.getDescription(false));
        }
    }

    @Override
    public String deleteUser(String name, WebRequest webRequest) {
        log.info("Deleting a user form user table");
        if (userRepository.existsByName(name)){
            userRepository.deleteByName(name);
            return "User Deleted with Provided name:"+name;
        } else {
            throw (new GlobalException("User not found with provided Id:" + name, HttpStatus.NOT_FOUND, new Date(), webRequest.getDescription(false)));
        }

    }
}
