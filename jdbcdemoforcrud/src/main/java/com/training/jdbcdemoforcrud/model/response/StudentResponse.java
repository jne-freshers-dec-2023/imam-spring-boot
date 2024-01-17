package com.training.jdbcdemoforcrud.model.response;

import com.training.jdbcdemoforcrud.entity.Address;
import com.training.jdbcdemoforcrud.entity.Department;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class StudentResponse {

    private int id;
    private String name;
    private Address address;
    private Department department;
}
