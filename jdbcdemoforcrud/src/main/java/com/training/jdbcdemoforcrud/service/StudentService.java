package com.training.jdbcdemoforcrud.service;

import com.training.jdbcdemoforcrud.entity.Student;
import com.training.jdbcdemoforcrud.model.request.StudentRequest;
import com.training.jdbcdemoforcrud.model.response.StudentResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.UUID;

@Service
public interface StudentService {

    List<StudentResponse> getAllStudentList(WebRequest webRequest);

    List<StudentResponse> getAllStudentsSorted(String field, WebRequest webRequest);

    List<Student> getAllStudentWithPagination(int pageNumber, int pageSize, WebRequest webRequest);

    StudentResponse getStudent(UUID uuid, WebRequest webRequest);

    StudentResponse addStudent(StudentRequest studentRequest, WebRequest webRequest);

    String deleteStudent(UUID uuid, WebRequest webRequest);

}