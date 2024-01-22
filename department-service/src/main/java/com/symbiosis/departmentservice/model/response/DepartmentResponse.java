package com.symbiosis.departmentservice.model.response;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Data
public class DepartmentResponse {
    public UUID uuid;
    public String name;
}
