package com.symbiosis.departmentservice.service;

import com.symbiosis.departmentservice.entity.Department;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DepartmentService {

    public List<Department> getAllDepartments();
    public Department getDepartmentByID(int id);
    public Department addDepartment(Department department);
    public boolean updateDepartment(Department department);
    public boolean deleteDepartmentById(int id);
}
