package com.symbiosis.studentservice.service;

import com.symbiosis.studentservice.model.request.StudentRequest;
import com.symbiosis.studentservice.model.response.GlobalResponse;
import com.symbiosis.studentservice.model.response.StudentResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public interface StudentService {
    GlobalResponse getAllStudents(String direction, String orderBy, Integer pageNumber,Integer pageSize);

    GlobalResponse getStudent(UUID uuid);

    GlobalResponse addStudent(StudentRequest studentRequest);

    GlobalResponse updateStudent(StudentRequest studentRequest);

    GlobalResponse deleteStudent(UUID uuid);
}