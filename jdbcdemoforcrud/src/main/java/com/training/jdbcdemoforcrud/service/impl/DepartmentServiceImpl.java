package com.training.jdbcdemoforcrud.service.impl;

import com.training.jdbcdemoforcrud.entity.Department;
import com.training.jdbcdemoforcrud.exception.DepartmentAlreadyExistException;
import com.training.jdbcdemoforcrud.exception.DepartmentNotFoundException;
import com.training.jdbcdemoforcrud.model.request.DepartmentRequest;
import com.training.jdbcdemoforcrud.model.response.DepartmentResponse;
import com.training.jdbcdemoforcrud.repository.DepartmentRepository;
import com.training.jdbcdemoforcrud.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;

    @Override
    public List<DepartmentResponse> getAllDepartmentsList() {
        log.info("Retrieving all departments from department table");
        List<Department> departmentList = departmentRepository.findAll();
        if (!departmentList.isEmpty()) {
            List<DepartmentResponse> departmentResponseList = new ArrayList<>();
            for (Department department : departmentList) {
                departmentResponseList.add(toDepartmentResponse(department));
            }
            return departmentResponseList;
        } else {
            throw new DepartmentNotFoundException("Departments are not Available");
        }
    }

    @Override
    public DepartmentResponse addDepartment(DepartmentRequest departmentRequest) {
        log.info("Adding new Department in department table");
        Optional<Department> department = departmentRepository.findByName(departmentRequest.getName());
        if (!department.isPresent()) {
            return toDepartmentResponse(departmentRepository.save(toDepartment(departmentRequest)));
        } else {
            throw new DepartmentAlreadyExistException("Department Already Exist");
        }
    }

    @Override
    public DepartmentResponse getDepartment(int id) {
        log.info("Retrieving a department with provided id");
        Optional<Department> department = departmentRepository.findById(id);
        if (department.isPresent()) {
            return toDepartmentResponse(department.get());
        } else {
            throw new DepartmentNotFoundException("Department Not found with provided ID: " + id);
        }
    }

    @Override
    public void deleteDepartment(int id) {
        log.info("Deleting a department from department table with provided id");
        if (departmentRepository.existsById(id)) {
            departmentRepository.deleteById(id);
        } else {
            throw new DepartmentNotFoundException("Department Not found with provided ID: " + id);
        }
    }

    public Department toDepartment(DepartmentRequest departmentRequest) {
        log.info("Converting DepartmentRequest to Department");
        Department department = new Department();
        department.setName(departmentRequest.getName());
        return department;
    }

    public DepartmentResponse toDepartmentResponse(Department department) {
        log.info("Converting Department to DepartmentResponse");
        DepartmentResponse departmentResponse = new DepartmentResponse();
        departmentResponse.setId(department.getId());
        departmentResponse.setName(department.getName());
        return departmentResponse;
    }

}