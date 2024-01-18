package com.training.jdbcdemoforcrud.service;

import com.training.jdbcdemoforcrud.model.request.DepartmentRequest;
import com.training.jdbcdemoforcrud.model.response.DepartmentResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface DepartmentService {
    List<DepartmentResponse> getAllDepartmentsList();

    DepartmentResponse getDepartment(UUID uuid);

    DepartmentResponse addDepartment(DepartmentRequest departmentRequest);

    String deleteDepartment(UUID uuid);

}