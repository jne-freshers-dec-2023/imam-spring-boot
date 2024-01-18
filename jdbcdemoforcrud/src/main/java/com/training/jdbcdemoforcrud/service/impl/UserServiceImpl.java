package com.training.jdbcdemoforcrud.service.impl;

import com.training.jdbcdemoforcrud.entity.User;
import com.training.jdbcdemoforcrud.enums.UserRole;
import com.training.jdbcdemoforcrud.exception.RoleNotFoundException;
import com.training.jdbcdemoforcrud.exception.UserNotFoundException;
import com.training.jdbcdemoforcrud.model.request.UserRequest;
import com.training.jdbcdemoforcrud.model.response.UserResponse;
import com.training.jdbcdemoforcrud.repository.UserRepository;
import com.training.jdbcdemoforcrud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Override
    public List<UserResponse> getAllUsers() {
        List<User> userList=userRepository.findAll();
        if (!userList.isEmpty()){
            List<UserResponse> userResponseList=new ArrayList<>();
            for (User user:userList){
                userResponseList.add(toUserResponse(user));
            }
            return userResponseList;
        } else {
            throw (new UserNotFoundException("Users Not Available"));
        }
    }

    @Override
    public UserResponse getUser(UUID uuid) {
        Optional<User> optionalUser=userRepository.findByUuid(uuid);
        if(optionalUser.isPresent()){
            return toUserResponse(optionalUser.get());
        } else {
            throw (new UserNotFoundException("User not found with provided Id: "+uuid));
        }
    }

    @Override
    public UserResponse addUser(UserRequest userRequest) {
        if(checkRole(userRequest.getRole().toUpperCase())) {
            return toUserResponse(userRepository.save(getUser(userRequest)));
        } else {
            throw new RoleNotFoundException("Provided Role is Not Valid");
        }
    }

    @Override
    public String deleteUser(UUID uuid) {
        if (userRepository.existsByUuid(uuid)){
            userRepository.deleteByUuid(uuid);
            return "User Deleted with Provided id: "+uuid;
        } else {
            throw (new UserNotFoundException("User not found with provided Id: "+uuid));
        }

    }

    public User getUser(UserRequest userRequest){
        User user=new User();
        user.setName(userRequest.getName());
        user.setRole(userRequest.getRole().toUpperCase());
        return user;
    }

    public boolean checkRole(String role){
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
        UserResponse userResponse=new UserResponse();
        userResponse.setId(user.getUuid());
        userResponse.setName(user.getName());
        userResponse.setRole(user.getRole());
        return userResponse;
    }
}
