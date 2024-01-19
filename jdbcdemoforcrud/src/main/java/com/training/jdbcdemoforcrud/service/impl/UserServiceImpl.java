package com.training.jdbcdemoforcrud.service.impl;

import com.training.jdbcdemoforcrud.entity.User;
import com.training.jdbcdemoforcrud.enums.UserRole;
import com.training.jdbcdemoforcrud.exception.GlobalException;
import com.training.jdbcdemoforcrud.model.request.UserRequest;
import com.training.jdbcdemoforcrud.model.response.UserResponse;
import com.training.jdbcdemoforcrud.repository.UserRepository;
import com.training.jdbcdemoforcrud.service.UserService;
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
    @Override
    public List<UserResponse> getAllUsers(WebRequest webRequest) {
        log.info("Retrieving All Users from users table");
        List<User> userList=userRepository.findAll();
        if (!userList.isEmpty()){
            List<UserResponse> userResponseList=new ArrayList<>();
            for (User user:userList){
                userResponseList.add(toUserResponse(user));
            }
            return userResponseList;
        } else {
            throw (new GlobalException("Users are Not Available", HttpStatus.NOT_FOUND, new Date(), webRequest.getDescription(false)));
        }
    }

    @Override
    public UserResponse getUser(UUID uuid, WebRequest webRequest) {
        log.info("Retrieving a User from user table");
        Optional<User> optionalUser=userRepository.findByUuid(uuid);
        if(optionalUser.isPresent()){
            return toUserResponse(optionalUser.get());
        } else {
            throw (new GlobalException("User not found with provided Id:" + uuid, HttpStatus.NOT_FOUND, new Date(), webRequest.getDescription(false)));
        }
    }

    @Override
    public UserResponse addUser(UserRequest userRequest, WebRequest webRequest) {
        log.info("Adding new user to user table");
        if(checkRole(userRequest.getRole().toUpperCase())) {
            return toUserResponse(userRepository.save(getUser(userRequest)));
        } else {
            throw new GlobalException("Provided Role is Not Valid", HttpStatus.NOT_FOUND, new Date(), webRequest.getDescription(false));
        }
    }

    @Override
    public String deleteUser(UUID uuid, WebRequest webRequest) {
        log.info("Deleting a user form user table");
        if (userRepository.existsByUuid(uuid)){
            userRepository.deleteByUuid(uuid);
            return "User Deleted with Provided id:"+uuid;
        } else {
            throw (new GlobalException("User not found with provided Id:" + uuid, HttpStatus.NOT_FOUND, new Date(), webRequest.getDescription(false)));
        }

    }

    public User getUser(UserRequest userRequest){
        log.info("Converting UserRequest to User");
        User user=new User();
        user.setName(userRequest.getName());
        user.setRole(userRequest.getRole().toUpperCase());
        return user;
    }

    public boolean checkRole(String role){
        log.info("Checking provided role is Valid or not");
        boolean avail=false;
        for(UserRole userRole:UserRole.values()){
            if (role.equals(userRole.name())) {
                avail = true;
                break;
            }
        }
        return avail;
    }
    public UserResponse toUserResponse(User user){
        log.info("Converting User to User Response");
        UserResponse userResponse=new UserResponse();
        userResponse.setId(user.getUuid());
        userResponse.setName(user.getName());
        userResponse.setRole(user.getRole());
        return userResponse;
    }
}
