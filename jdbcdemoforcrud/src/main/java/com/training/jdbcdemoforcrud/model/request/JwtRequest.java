package com.training.jdbcdemoforcrud.model.request;

import lombok.Data;

@Data
public class JwtRequest {
    String userName;
    String password;
}
