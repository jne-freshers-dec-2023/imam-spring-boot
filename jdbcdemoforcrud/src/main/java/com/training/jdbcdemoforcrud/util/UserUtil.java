package com.training.jdbcdemoforcrud.util;

import com.training.jdbcdemoforcrud.entity.User;
import com.training.jdbcdemoforcrud.enums.UserRole;
import com.training.jdbcdemoforcrud.model.request.UserRequest;
import com.training.jdbcdemoforcrud.model.response.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserUtil {

    @Autowired
    PasswordEncoder passwordEncoder;

    public User getUser(UserRequest userRequest) {
        log.info("Converting UserRequest to User");
        User user = new User();
        user.setName(userRequest.getUserName());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setRole(userRequest.getRole().toUpperCase());
        return user;
    }

    public boolean checkRole(String role) {
        log.info("Checking provided role is Valid or not");
        boolean avail = false;
        for (UserRole userRole : UserRole.values()) {
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
        userResponse.setName(user.getName());
        userResponse.setRole(user.getRole());
        return userResponse;
    }
}
