package com.training.jdbcdemoforcrud.service;

import com.training.jdbcdemoforcrud.model.request.DepartmentRequest;
import com.training.jdbcdemoforcrud.model.response.DepartmentResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.UUID;

@Service
public interface DepartmentService {
    List<DepartmentResponse> getAllDepartmentsList(WebRequest webRequest);

    DepartmentResponse getDepartment(UUID uuid, WebRequest webRequest);

    DepartmentResponse addDepartment(DepartmentRequest departmentRequest, WebRequest webRequest);

    String deleteDepartment(UUID uuid, WebRequest webRequest);

}