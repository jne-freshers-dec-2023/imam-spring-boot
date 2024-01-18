package com.training.jdbcdemoforcrud.service.impl;

import com.training.jdbcdemoforcrud.entity.Department;
import com.training.jdbcdemoforcrud.exception.StudentNotFoundException;
import com.training.jdbcdemoforcrud.entity.Student;
import com.training.jdbcdemoforcrud.model.request.StudentRequest;
import com.training.jdbcdemoforcrud.model.response.DepartmentResponse;
import com.training.jdbcdemoforcrud.model.response.StudentResponse;
import com.training.jdbcdemoforcrud.repository.DepartmentRepository;
import com.training.jdbcdemoforcrud.repository.StudentRepository;
import com.training.jdbcdemoforcrud.service.DepartmentService;
import com.training.jdbcdemoforcrud.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class StudentServiceImpl implements StudentService {

    @Autowired
    DepartmentService departmentService;
    @Autowired
    StudentRepository studentRepository;


    @Override
    public List<StudentResponse> getAllStudentList() {
        log.info("Retrieving all students from student table");
        List<Student> studentList = studentRepository.findAll();
        if (!studentList.isEmpty()) {
            List<StudentResponse> studentResponseList=new ArrayList<>();
            for(Student student:studentList){
                studentResponseList.add(toStudentResponse(student));
            }
            return studentResponseList;
        } else {
            throw new StudentNotFoundException("Students are not Available");
        }
    }

    @Override
    public StudentResponse getStudent(UUID uuid) {
        log.info("Retrieving a student data from student table with provided id");
        Optional<Student> optionalStudent = studentRepository.findByUuid(uuid);
        if (optionalStudent.isPresent()) {
            return toStudentResponse(optionalStudent.get());
        } else {
            throw new StudentNotFoundException("Student not found with given id: " + uuid);
        }
    }
    @Override
    public StudentResponse addStudent(StudentRequest studentRequest) {
        log.info("Adding new student in student table");
        return toStudentResponse(studentRepository.save(toStudent(studentRequest)));
    }

    @Override
    public String deleteStudent(UUID uuid) {
        log.info("Deleting a student from student table with provided id");
        if (studentRepository.existsByUuid(uuid)) {
            studentRepository.deleteByUuid(uuid);
            return ("Student Data deleted with provided id: "+uuid);
        } else {
            throw new StudentNotFoundException("Student not found with given id: " + uuid);
        }
    }
    public Student toStudent(StudentRequest studentRequest){
        log.info("Converting StudentRequest to Student");
        Student student=new Student();
        student.setName(studentRequest.getName());
        student.setAddress(studentRequest.getAddress());
        student.setDeptId(departmentService.getDepartment(studentRequest.getDeptID()).getUuid());
        return student;
    }

    public StudentResponse toStudentResponse(Student student){
        log.info("Converting Student to StudentResponse");
        StudentResponse studentResponse=new StudentResponse();
        studentResponse.setUuid(student.getUuid());
        studentResponse.setName(student.getName());
        studentResponse.setAddress(student.getAddress());
        studentResponse.setDepartment(toDepartment(departmentService.getDepartment(student.getDeptId())));
        return studentResponse;
    }

    public Department toDepartment(DepartmentResponse departmentResponse){
        log.info("Converting DepartmentResponse to Department");
        Department department=new Department();
        department.setUuid(departmentResponse.getUuid());
        department.setName(departmentResponse.getName());
        return department;
    }
}
