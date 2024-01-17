package com.training.jdbcdemoforcrud.service;

import com.training.jdbcdemoforcrud.model.request.StudentRequest;
import com.training.jdbcdemoforcrud.model.response.StudentResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {

    List<StudentResponse> getAllStudentList();

    StudentResponse getStudent(int id);

    StudentResponse addStudent(StudentRequest studentRequest);

    void deleteStudent(int id);

}