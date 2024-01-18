package com.training.jdbcdemoforcrud.model.response;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Data
public class UserResponse {
    UUID id;
    String name;
    String role;
}
