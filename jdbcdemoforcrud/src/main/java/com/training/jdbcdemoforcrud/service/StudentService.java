package com.training.jdbcdemoforcrud.service;

import com.training.jdbcdemoforcrud.model.Address;
import com.training.jdbcdemoforcrud.model.Department;
import com.training.jdbcdemoforcrud.model.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {

    List<Student> getAllStudentList();

    Student getStudent(int id);

    Student addStudent(Student student);

    void deleteStudent(int id);

}