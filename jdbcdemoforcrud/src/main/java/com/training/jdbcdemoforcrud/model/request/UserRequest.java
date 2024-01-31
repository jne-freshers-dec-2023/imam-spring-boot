package com.training.jdbcdemoforcrud.model.request;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class UserRequest {
    String userName;
    String password;
    String role;
}
