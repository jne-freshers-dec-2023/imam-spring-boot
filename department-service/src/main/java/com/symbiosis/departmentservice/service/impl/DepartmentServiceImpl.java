package com.symbiosis.departmentservice.service.impl;

import com.symbiosis.departmentservice.entity.Department;
import com.symbiosis.departmentservice.repository.DepartmentRepository;
import com.symbiosis.departmentservice.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;
    @Override
    public List<Department> getAllDepartments() {

        List<Department> departmentList=departmentRepository.findAll();
        return departmentList;
    }

    @Override
    public void getDepartmentByID() {

    }

    @Override
    public void addDepartment() {

    }

    @Override
    public void updateDepartment() {

    }

    @Override
    public void deleteDepartmentById() {

    }
}
