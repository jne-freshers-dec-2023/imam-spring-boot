package com.training.jdbcdemoforcrud.service;

import com.training.jdbcdemoforcrud.model.request.DepartmentRequest;
import com.training.jdbcdemoforcrud.model.response.DepartmentResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DepartmentService {
    List<DepartmentResponse> getAllDepartmentsList();

    DepartmentResponse getDepartment(int id);

    DepartmentResponse addDepartment(DepartmentRequest departmentRequest);

    void deleteDepartment(int id);

}