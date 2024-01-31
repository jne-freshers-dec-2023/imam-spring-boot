package com.symbiosis.departmentservice.service;

import com.symbiosis.departmentservice.entity.Department;
import com.symbiosis.departmentservice.model.request.DepartmentRequest;
import com.symbiosis.departmentservice.model.response.DepartmentResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.UUID;

@Service
public interface DepartmentService {
    List<DepartmentResponse> getAllDepartmentsList();

    DepartmentResponse getDepartment(UUID uuid);

    DepartmentResponse addDepartment(DepartmentRequest departmentRequest, WebRequest webRequest);

    public String updateDepartment(Department department, WebRequest webRequest);

    String deleteDepartment(UUID uuid, WebRequest webRequest);
}
