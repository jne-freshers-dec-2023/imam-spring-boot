package com.training.jdbcdemoforcrud.service.impl;

import com.training.jdbcdemoforcrud.exception.DepartmentNotFoundException;
import com.training.jdbcdemoforcrud.model.Department;
import com.training.jdbcdemoforcrud.repository.DepartmentRepository;
import com.training.jdbcdemoforcrud.service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    Logger logger = LoggerFactory.getLogger(DepartmentServiceImpl.class);
    @Autowired
    DepartmentRepository departmentRepository;

    @Override
    public List<Department> getAllDepartmentsList() {
        logger.info("Retrieving all departments from department table");
        List<Department> departmentList = departmentRepository.findAll();
        if (!departmentList.isEmpty()) {
            return departmentList;
        } else {
            throw new DepartmentNotFoundException("Departments are not Available");
        }
    }

    @Override
    public Department addDepartment(Department department) {
        logger.info("Adding new Department in department table");
        return departmentRepository.save(department);
    }

    @Override
    public Department getDepartment(int id) {
        logger.info("Retrieving a department with provided id");
        Optional<Department> department = departmentRepository.findById(id);
        if (department.isPresent()) {
            return department.get();
        } else {
            throw new DepartmentNotFoundException("Department Not found with provided ID: " + id);
        }
    }

    @Override
    public void deleteDepartment(int id) {
        logger.info("Deleting a department from department table with provided id");
        if (departmentRepository.existsById(id)) {
            departmentRepository.deleteById(id);
        } else {
            throw new DepartmentNotFoundException("Department Not found with provided ID: " + id);
        }
    }

}
