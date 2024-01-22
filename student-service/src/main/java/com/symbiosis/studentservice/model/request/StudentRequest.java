package com.symbiosis.studentservice.model.request;

import com.symbiosis.studentservice.entity.Address;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Data
public class StudentRequest {
    private UUID uuid;
    private String name;
    private Address address;
    private UUID deptId;
}
