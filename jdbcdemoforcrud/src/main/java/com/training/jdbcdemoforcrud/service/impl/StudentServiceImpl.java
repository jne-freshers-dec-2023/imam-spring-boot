package com.training.jdbcdemoforcrud.service.impl;

import com.training.jdbcdemoforcrud.entity.Department;
import com.training.jdbcdemoforcrud.entity.Student;
import com.training.jdbcdemoforcrud.exception.GlobalException;
import com.training.jdbcdemoforcrud.model.request.StudentRequest;
import com.training.jdbcdemoforcrud.model.response.DepartmentResponse;
import com.training.jdbcdemoforcrud.model.response.StudentResponse;
import com.training.jdbcdemoforcrud.repository.StudentRepository;
import com.training.jdbcdemoforcrud.service.DepartmentService;
import com.training.jdbcdemoforcrud.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

import java.util.*;

@Service
@Slf4j
public class StudentServiceImpl implements StudentService {

    @Autowired
    DepartmentService departmentService;
    @Autowired
    StudentRepository studentRepository;


    @Override
    public List<StudentResponse> getAllStudentList(WebRequest webRequest) {
        log.info("Retrieving all students from student table");
        List<Student> studentList = studentRepository.findAll();
        if (!studentList.isEmpty()) {
            List<StudentResponse> studentResponseList = new ArrayList<>();
            for (Student student : studentList) {
                studentResponseList.add(toStudentResponse(student, webRequest));
            }
            return studentResponseList;
        } else {
            throw new GlobalException("Students are not Available", HttpStatus.NOT_FOUND, new Date(), webRequest.getDescription(false));
        }
    }

    @Override
    public List<StudentResponse> getAllStudentsSorted(String field, WebRequest webRequest) {
        log.info("Retrieving All Students from students table with sorting");
        List<Student> studentList = studentRepository.findAll(Sort.by(field));
        if (!studentList.isEmpty()) {
            List<StudentResponse> studentResponseList = new ArrayList<>();
            for (Student student : studentList) {
                studentResponseList.add(toStudentResponse(student, webRequest));
            }
            return studentResponseList;
        } else {
            throw new GlobalException("Students are not Available", HttpStatus.NOT_FOUND, new Date(), webRequest.getDescription(false));
        }
    }

    @Override
    public List<Student> getAllStudentWithPagination(int pageNumber, int pageSize, WebRequest webRequest) {
        log.info("Retrieving All Students from students table with Pagination");
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Student> studentPage = studentRepository.findAll(pageable);
        if (studentPage.hasContent()) {
            return studentPage.getContent();
        } else {
            throw new GlobalException("Students are not Available", HttpStatus.NOT_FOUND, new Date(), webRequest.getDescription(false));
        }
    }

    @Override
    public StudentResponse getStudent(UUID uuid, WebRequest webRequest) {
        log.info("Retrieving a student data from student table with provided id");
        Optional<Student> optionalStudent = studentRepository.findByUuid(uuid);
        if (optionalStudent.isPresent()) {
            return toStudentResponse(optionalStudent.get(), webRequest);
        } else {
            throw new GlobalException("Student not found with given id:" + uuid, HttpStatus.NOT_FOUND, new Date(), webRequest.getDescription(false));
        }
    }

    @Override
    public StudentResponse addStudent(StudentRequest studentRequest, WebRequest webRequest) {
        log.info("Adding new student in student table");
        return toStudentResponse(studentRepository.save(toStudent(studentRequest, webRequest)), webRequest);
    }

    @Override
    public String deleteStudent(UUID uuid, WebRequest webRequest) {
        log.info("Deleting a student from student table with provided id");
        if (studentRepository.existsByUuid(uuid)) {
            studentRepository.deleteByUuid(uuid);
            return ("Student Data deleted with provided id:" + uuid);
        } else {
            throw new GlobalException("Student not found with given id:" + uuid, HttpStatus.NOT_FOUND, new Date(), webRequest.getDescription(false));
        }
    }

    public Student toStudent(StudentRequest studentRequest, WebRequest webRequest) {
        log.info("Converting StudentRequest to Student");
        Student student = new Student();
        student.setName(studentRequest.getName());
        student.setAddress(studentRequest.getAddress());
        student.setDeptId(departmentService.getDepartment(studentRequest.getDeptID(), webRequest).getUuid());
        return student;
    }

    public StudentResponse toStudentResponse(Student student, WebRequest webRequest) {
        log.info("Converting Student to StudentResponse");
        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setUuid(student.getUuid());
        studentResponse.setName(student.getName());
        studentResponse.setAddress(student.getAddress());
        studentResponse.setDepartment(toDepartment(departmentService.getDepartment(student.getDeptId(), webRequest)));
        return studentResponse;
    }

    public Department toDepartment(DepartmentResponse departmentResponse) {
        log.info("Converting DepartmentResponse to Department");
        Department department = new Department();
        department.setUuid(departmentResponse.getUuid());
        department.setName(departmentResponse.getName());
        return department;
    }
}