package com.training.jdbcdemoforcrud.service.impl;

import com.training.jdbcdemoforcrud.model.Address;
import com.training.jdbcdemoforcrud.model.Department;
import com.training.jdbcdemoforcrud.model.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CollageService {

    List<Student> getAllStudentList();

    List<Department> getAllDepartmentsList();

    List<Address> getAllAddresssList();

    Department addDepartment(Department department);

    Student addStudent(Student student);

}