package com.symbiosis.studentservice.service.impl;

import com.symbiosis.studentservice.entity.Student;
import com.symbiosis.studentservice.exception.GlobalException;
import com.symbiosis.studentservice.client.DepartmentInterface;
import com.symbiosis.studentservice.model.request.StudentRequest;
import com.symbiosis.studentservice.model.response.DepartmentResponse;
import com.symbiosis.studentservice.model.response.GlobalResponse;
import com.symbiosis.studentservice.model.response.StudentResponse;
import com.symbiosis.studentservice.repository.StudentRepository;
import com.symbiosis.studentservice.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
@Slf4j
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    @Autowired
    private final DepartmentInterface departmentInterface;
    private final RestTemplate restTemplate;

    public StudentServiceImpl(StudentRepository studentRepository, DepartmentInterface departmentInterface, RestTemplate restTemplate) {
        this.studentRepository = studentRepository;
        this.restTemplate = restTemplate;
        this.departmentInterface = departmentInterface;
    }
    @Override
    public GlobalResponse getAllStudents(String direction, String orderBy, Integer pageNumber, Integer pageSize) {
        log.info("Retrieving All Students from students table with sorting");
        List<Student> studentList = studentRepository.findStudentsWithPaginationOrderBy(PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.fromString(direction), orderBy)));
        if (!studentList.isEmpty()) {
            List<StudentResponse> studentResponseList = new ArrayList<>();
            for (Student student : studentList) {
                studentResponseList.add(toStudentResponse(student));
            }
            return new GlobalResponse(HttpStatus.OK, HttpStatus.OK.value(), studentResponseList, null);
        } else {
            throw new GlobalException("Students are not Available", HttpStatus.NOT_FOUND);
        }
    }
    @Override
    public GlobalResponse getStudent(UUID uuid) {
        log.info("Retrieving a student data from student table with provided id");
        Optional<Student> optionalStudent = studentRepository.findByUuid(uuid);
        if (optionalStudent.isPresent()) {
            return new GlobalResponse(HttpStatus.OK, HttpStatus.OK.value(), toStudentResponse(optionalStudent.get()), null);
        } else {
            throw new GlobalException("Student not found with given id:" + uuid, HttpStatus.NOT_FOUND);
        }
    }
    @Override
    public GlobalResponse addStudent(StudentRequest studentRequest) {
        ResponseEntity<DepartmentResponse> departmentResponseEntity = departmentInterface.getDepartment(studentRequest.getDeptId());
//        ResponseEntity<DepartmentResponse> departmentResponseEntity = restTemplate.getForEntity(StudentServiceConstants.EUREKA_DEPARTMENT_SERVICE_URL_UUID + studentRequest.getDeptId(), DepartmentResponse.class);
//        ResponseEntity<DepartmentResponse> departmentResponseEntity = restTemplate.getForEntity(StudentServiceConstants.DEPARTMENT_SERVICE_URL_UUID + studentRequest.getDeptId(), DepartmentResponse.class);
        if (departmentResponseEntity.getStatusCode() == HttpStatus.OK) {
            log.info("Adding new student in student table");
            Student student = new Student();
            student.setName(studentRequest.getName());
            student.setAddress(studentRequest.getAddress());
            student.setDeptId(studentRequest.getDeptId());
            studentRepository.save(student);
            return new GlobalResponse(HttpStatus.CREATED, HttpStatus.CREATED.value(), null, "Student Saved Successfully");
        } else {
            throw new GlobalException("Provided or Associated Department is not Available", HttpStatus.NOT_FOUND);
        }
    }
    @Override
    public GlobalResponse updateStudent(StudentRequest studentRequest) {
        ResponseEntity<DepartmentResponse> departmentResponseEntity = departmentInterface.getDepartment(studentRequest.getDeptId());
//        ResponseEntity<DepartmentResponse> departmentResponseEntity = restTemplate.getForEntity(StudentServiceConstants.EUREKA_DEPARTMENT_SERVICE_URL_UUID + studentRequest.getDeptId(), DepartmentResponse.class);
//        ResponseEntity<DepartmentResponse> departmentResponseEntity = restTemplate.getForEntity(StudentServiceConstants.DEPARTMENT_SERVICE_URL_UUID + studentRequest.getDeptId(), DepartmentResponse.class);
        if (departmentResponseEntity.getStatusCode() == HttpStatus.OK) {
            if (studentRepository.existsById(studentRequest.getUuid())) {
                Student student = new Student();
                student.setUuid(studentRequest.getUuid());
                student.setName(studentRequest.getName());
                student.setAddress(studentRequest.getAddress());
                student.setDeptId(studentRequest.getDeptId());
                studentRepository.save(student);
                return new GlobalResponse(HttpStatus.OK, HttpStatus.OK.value(), null, "Student Updated Successfully");
            } else {
                throw new GlobalException("Student Not found with provided ID: " + studentRequest.getUuid(), HttpStatus.NOT_FOUND);
            }
        } else {
            throw new GlobalException("Provided or Associated Department is not Available", HttpStatus.NOT_FOUND);
        }
    }
    @Override
    public GlobalResponse deleteStudent(UUID uuid) {
        log.info("Deleting a student from student table with provided id");
        int count = studentRepository.deleteByUuid(uuid);
        if (count > 0) {
            return new GlobalResponse(HttpStatus.OK, HttpStatus.OK.value(), null, count + " - Student Data deleted with provided id: " + uuid);
        } else {
            throw new GlobalException("Student not found with given id:" + uuid, HttpStatus.NOT_FOUND);
        }
    }
    public StudentResponse toStudentResponse(Student student) {
        log.info("Converting Student to StudentResponse");
        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setUuid(student.getUuid());
        studentResponse.setName(student.getName());
        studentResponse.setAddress(student.getAddress());
        studentResponse.setDepartment(checkDepartment(student.getDeptId()));
        return studentResponse;
    }

    public DepartmentResponse checkDepartment(UUID uuid) {
        Map<UUID, String> departmentMap = getAllDepartment();
        if (departmentMap.containsKey(uuid)) {
            DepartmentResponse departmentResponse = new DepartmentResponse();
            departmentResponse.setUuid(uuid);
            departmentResponse.setName(departmentMap.get(uuid));
            return departmentResponse;
        } else {
            throw new GlobalException("Provided or Associated Department is not Available", HttpStatus.NOT_FOUND);
        }
    }

    public Map<UUID, String> getAllDepartment() {
        log.info("Calling a rest Template to get All the Departments form Department-Service");
        Map<UUID, String> departmentMap = new HashMap<>();
        ResponseEntity<List<DepartmentResponse>> responseEntity = departmentInterface.getDepartments();
//        ResponseEntity<DepartmentResponse[]> responseEntity = restTemplate.getForEntity(StudentServiceConstants.EUREKA_DEPARTMENT_SERVICE_URL_ALL, DepartmentResponse[].class);
//        ResponseEntity<DepartmentResponse[]> responseEntity = restTemplate.getForEntity(StudentServiceConstants.DEPARTMENT_SERVICE_URL_ALL, DepartmentResponse[].class);
        List<DepartmentResponse> departmentResponsesList = responseEntity.getBody();
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            for (DepartmentResponse response : departmentResponsesList) {
                departmentMap.put(response.getUuid(), response.getName());
            }
            return departmentMap;
        } else {
            throw new GlobalException("Data not Received from Department-Service", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}