package com.training.jdbcdemoforcrud.model.request;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class DepartmentRequest {
    private String name;
}
