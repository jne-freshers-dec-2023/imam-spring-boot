package com.training.jdbcdemoforcrud.service;

import com.training.jdbcdemoforcrud.model.Address;
import com.training.jdbcdemoforcrud.model.Department;
import com.training.jdbcdemoforcrud.model.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DepartmentService {
    List<Department> getAllDepartmentsList();

    Department getDepartment(int id);

    Department addDepartment(Department department);

    void deleteDepartment(int id);

}