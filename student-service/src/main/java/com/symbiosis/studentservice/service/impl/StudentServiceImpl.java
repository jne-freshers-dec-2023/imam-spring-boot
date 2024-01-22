package com.symbiosis.studentservice.service.impl;

import com.symbiosis.studentservice.entity.Student;
import com.symbiosis.studentservice.exception.GlobalException;
import com.symbiosis.studentservice.model.request.StudentRequest;
import com.symbiosis.studentservice.model.response.StudentResponse;
import com.symbiosis.studentservice.repository.StudentRepository;
import com.symbiosis.studentservice.service.StudentService;
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
    StudentRepository studentRepository;


    @Override
    public List<StudentResponse> getAllStudentList(WebRequest webRequest) {
        log.info("Retrieving all students from student table");
        List<Student> studentList = studentRepository.findAll();
        if (!studentList.isEmpty()) {
            List<StudentResponse> studentResponseList = new ArrayList<>();
            for (Student student : studentList) {
                studentResponseList.add(toStudentResponse(student));
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
                studentResponseList.add(toStudentResponse(student));
            }
            return studentResponseList;
        } else {
            throw new GlobalException("Students are not Available", HttpStatus.NOT_FOUND, new Date(), webRequest.getDescription(false));
        }
    }

    @Override
    public List<StudentResponse> getAllStudentWithPagination(int pageNumber, int pageSize, WebRequest webRequest) {
        log.info("Retrieving All Students from students table with Pagination");
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Student> studentPage = studentRepository.findAll(pageable);
        if (studentPage.hasContent()) {
            List<StudentResponse> studentResponseList = new ArrayList<>();
            for (Student student : studentPage) {
                studentResponseList.add(toStudentResponse(student));
            }
            return studentResponseList;
        } else {
            throw new GlobalException("Students are not Available", HttpStatus.NOT_FOUND, new Date(), webRequest.getDescription(false));
        }
    }

    @Override
    public StudentResponse getStudent(UUID uuid, WebRequest webRequest) {
        log.info("Retrieving a student data from student table with provided id");
        Optional<Student> optionalStudent = studentRepository.findByUuid(uuid);
        if (optionalStudent.isPresent()) {
            return toStudentResponse(optionalStudent.get());
        } else {
            throw new GlobalException("Student not found with given id:" + uuid, HttpStatus.NOT_FOUND, new Date(), webRequest.getDescription(false));
        }
    }

    @Override
    public StudentResponse addStudent(StudentRequest studentRequest, WebRequest webRequest) {
        log.info("Adding new student in student table");
        Student student = new Student();
        student.setName(studentRequest.getName());
        student.setAddress(studentRequest.getAddress());
        student.setDeptId(studentRequest.getDeptId());
        return toStudentResponse(studentRepository.save(student));
    }

    @Override
    public String updateStudent(StudentRequest studentRequest, WebRequest webRequest) {
        if (studentRepository.existsById(studentRequest.getUuid())) {
            Student student = new Student();
            student.setUuid(studentRequest.getUuid());
            student.setName(studentRequest.getName());
            student.setAddress(studentRequest.getAddress());
            student.setDeptId(studentRequest.getDeptId());
            studentRepository.save(student);
            return "Student Updated Successfully";
        } else {
            throw new GlobalException("Student Not found with provided ID: " + studentRequest.getUuid(), HttpStatus.NOT_FOUND, new Date(), webRequest.getDescription(false));
        }
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

    public StudentResponse toStudentResponse(Student student) {
        log.info("Converting Student to StudentResponse");
        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setUuid(student.getUuid());
        studentResponse.setName(student.getName());
        studentResponse.setAddress(student.getAddress());
        studentResponse.setDeptId(student.getDeptId());
        return studentResponse;
    }
}