package com.training.jdbcdemoforcrud.model.request;

import com.training.jdbcdemoforcrud.entity.Address;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class StudentRequest {
    private String name;
    private Address address;
    private int deptID;
}
