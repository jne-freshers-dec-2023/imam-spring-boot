package com.training.jdbcdemoforcrud.model.response;

import lombok.Data;

@Data
public class JwtResponse {
    String username;
    String token;
}
