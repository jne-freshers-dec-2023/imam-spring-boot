package com.symbiosis.studentservice.service;

import com.symbiosis.studentservice.entity.Student;
import com.symbiosis.studentservice.model.request.StudentRequest;
import com.symbiosis.studentservice.model.response.StudentResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.UUID;

@Service
public interface StudentService {

    List<StudentResponse> getAllStudentList(WebRequest webRequest);

    List<StudentResponse> getAllStudentsSorted(String field, WebRequest webRequest);

    List<StudentResponse> getAllStudentWithPagination(int pageNumber, int pageSize, WebRequest webRequest);

    StudentResponse getStudent(UUID uuid, WebRequest webRequest);

    StudentResponse addStudent(StudentRequest studentRequest, WebRequest webRequest);

    String updateStudent(StudentRequest studentRequest, WebRequest webRequest);

    String deleteStudent(UUID uuid, WebRequest webRequest);

}