package com.symbiosis.departmentservice.service.impl;

import com.symbiosis.departmentservice.entity.Department;
import com.symbiosis.departmentservice.repository.DepartmentRepository;
import com.symbiosis.departmentservice.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;
    @Override
    public List<Department> getAllDepartments() {

        return departmentRepository.findAll();
    }

    @Override
    public Department getDepartmentByID(int id) {

        Optional<Department> department = departmentRepository.findById(id);
        if (department.isPresent()) {
            return department.get();
        } else {
            return null;
        }
    }

    @Override
    public Department addDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public boolean updateDepartment(Department department) {

        if(departmentRepository.existsById(department.getDeptId())){
            departmentRepository.save(department);
            return true;
        } else{
            departmentRepository.save(department);
            return false;
        }
    }

    @Override
    public boolean deleteDepartmentById(int id) {

        if(departmentRepository.existsById(id)){
            departmentRepository.deleteById(id);
            return true;
        } else{
            return false;
        }
    }
}
