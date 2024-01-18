package com.training.jdbcdemoforcrud.service;

import com.training.jdbcdemoforcrud.model.request.StudentRequest;
import com.training.jdbcdemoforcrud.model.response.StudentResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface StudentService {

    List<StudentResponse> getAllStudentList();

    StudentResponse getStudent(UUID uuid);

    StudentResponse addStudent(StudentRequest studentRequest);

    String deleteStudent(UUID uuid);

}