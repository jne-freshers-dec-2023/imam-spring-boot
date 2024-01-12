package com.training.jdbcdemoforcrud.service.impl;

import com.training.jdbcdemoforcrud.model.Address;
import com.training.jdbcdemoforcrud.model.Department;
import com.training.jdbcdemoforcrud.model.Student;
import com.training.jdbcdemoforcrud.repository.AddressRepository;
import com.training.jdbcdemoforcrud.repository.DepartmentRepository;
import com.training.jdbcdemoforcrud.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollageServiceImpl implements CollageService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    AddressRepository addressRepository;

    @Override
    public List<Student> getAllStudentList() {
        return studentRepository.findAll();
    }

    @Override
    public List<Department> getAllDepartmentsList() {
        return departmentRepository.findAll();
    }

    @Override
    public List<Address> getAllAddresssList() {
        return addressRepository.findAll();
    }

    @Override
    public Department addDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

}
