package com.symbiosis.departmentservice.service;

import com.symbiosis.departmentservice.entity.Department;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DepartmentService {

    public List<Department> getAllDepartments();
    public void getDepartmentByID();
    public void addDepartment();
    public void updateDepartment();
    public void deleteDepartmentById();
}
