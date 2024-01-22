package com.symbiosis.departmentservice.service.impl;

import com.symbiosis.departmentservice.entity.Department;
import com.symbiosis.departmentservice.exception.GlobalException;
import com.symbiosis.departmentservice.model.request.DepartmentRequest;
import com.symbiosis.departmentservice.model.response.DepartmentResponse;
import com.symbiosis.departmentservice.repository.DepartmentRepository;
import com.symbiosis.departmentservice.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

import java.util.*;

@Service
@Slf4j
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;

    @Override
    public List<DepartmentResponse> getAllDepartmentsList(WebRequest webRequest) {
        log.info("Retrieving all departments from department table");
        List<Department> departmentList = departmentRepository.findAll();
        if (!departmentList.isEmpty()) {
            List<DepartmentResponse> departmentResponseList = new ArrayList<>();
            for (Department department : departmentList) {
                departmentResponseList.add(toDepartmentResponse(department));
            }
            return departmentResponseList;
        } else {
            throw new GlobalException("Departments are not Available", HttpStatus.NOT_FOUND, new Date(), webRequest.getDescription(false));
        }
    }

    @Override
    public DepartmentResponse addDepartment(DepartmentRequest departmentRequest, WebRequest webRequest) {
        log.info("Adding new Department in department table");
        Optional<Department> department = departmentRepository.findByName(departmentRequest.getName());
        if (!department.isPresent()) {
            return toDepartmentResponse(departmentRepository.save(toDepartment(departmentRequest)));
        } else {
            throw (new GlobalException("Department Already Exist", HttpStatus.FOUND, new Date(), webRequest.getDescription(false)));
        }
    }

    @Override
    public DepartmentResponse getDepartment(UUID uuid, WebRequest webRequest) {
        log.info("Retrieving a department with provided id");
        Optional<Department> department = departmentRepository.findByUuid(uuid);
        if (department.isPresent()) {
            return toDepartmentResponse(department.get());
        } else {
            throw new GlobalException("Department Not found with provided ID:" + uuid, HttpStatus.NOT_FOUND, new Date(), webRequest.getDescription(false));
        }
    }
    @Override
    public String updateDepartment(Department department, WebRequest webRequest) {

        if(departmentRepository.existsById(department.getUuid())){
            departmentRepository.save(department);
            return "Department Updated Successfully";
        } else{
            throw new GlobalException("Department Not found with provided ID: " + department.getUuid(), HttpStatus.NOT_FOUND, new Date(), webRequest.getDescription(false));
        }
    }

    @Override
    public String deleteDepartment(UUID uuid, WebRequest webRequest) {
        log.info("Deleting a department from department table with provided id");
        if (departmentRepository.existsByUuid(uuid)) {
            departmentRepository.deleteByUuid(uuid);
            return ("Department Deleted with provided id:" + uuid);
        } else {
            throw new GlobalException("Department Not found with provided ID: " + uuid, HttpStatus.NOT_FOUND, new Date(), webRequest.getDescription(false));
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
        departmentResponse.setUuid(department.getUuid());
        departmentResponse.setName(department.getName());
        return departmentResponse;
    }

}