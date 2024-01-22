package com.symbiosis.studentservice.model.response;

import com.symbiosis.studentservice.entity.Address;
import jakarta.persistence.Column;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.security.PrivateKey;
import java.util.UUID;

@Component
@Data
public class StudentResponse {

    private UUID uuid;
    private String name;
    private Address address;
    private UUID deptId;
}
