package com.symbiosis.studentservice.model.response;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Data
public class DepartmentResponse {
    public UUID uuid;
    public String name;
}
