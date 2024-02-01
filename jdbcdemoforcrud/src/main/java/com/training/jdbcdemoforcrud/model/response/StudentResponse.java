package com.training.jdbcdemoforcrud.model.response;

import com.training.jdbcdemoforcrud.entity.Address;
import com.training.jdbcdemoforcrud.entity.Department;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Data
public class StudentResponse {

    private UUID uuid;
    private String name;
    private Address address;
    private Department department;
}
