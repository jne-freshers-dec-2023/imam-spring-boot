package com.training.jdbcdemoforcrud.model.response;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class DepartmentResponse {
    public int id;
    public String name;
}
