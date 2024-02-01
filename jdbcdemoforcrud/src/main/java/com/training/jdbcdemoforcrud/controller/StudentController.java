package com.training.jdbcdemoforcrud.controller;

import com.training.jdbcdemoforcrud.entity.Student;
import com.training.jdbcdemoforcrud.model.request.StudentRequest;
import com.training.jdbcdemoforcrud.model.response.StudentResponse;
import com.training.jdbcdemoforcrud.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.UUID;

//http://localhost:8080/collage/student
@RestController
@RequestMapping(path = "/collage/student")
public class StudentController {
    @Autowired
    StudentService studentService;

    @GetMapping(path = "/all")
    public ResponseEntity<List<StudentResponse>> getStudents(WebRequest webRequest) {
        return (new ResponseEntity<>(studentService.getAllStudentList(webRequest), HttpStatus.OK));
    }

    @GetMapping(path = "/all/sorted")
    public ResponseEntity<List<StudentResponse>> getStudentsSorted(WebRequest webRequest, @RequestParam(name = "field") String field) {
        return (new ResponseEntity<>(studentService.getAllStudentsSorted(field, webRequest), HttpStatus.OK));
    }

    @GetMapping(path = "/all/paged")
    public ResponseEntity<List<Student>> getStudentsPaged(WebRequest webRequest, @RequestParam(name = "pageNumber") int pageNumber, @RequestParam(name = "pageSize") int pageSize) {

        return new ResponseEntity<>(studentService.getAllStudentWithPagination(pageNumber, pageSize, webRequest), HttpStatus.OK);
    }

    @GetMapping(path = "/single")
    public ResponseEntity<StudentResponse> getStudent(WebRequest webRequest, @RequestParam(name = "uuid") UUID uuid) {
        return new ResponseEntity<>(studentService.getStudent(uuid, webRequest), HttpStatus.OK);
    }
/*
{
    "name": "Imam",
    "deptID": 12,
    "address": {
        "city": "city",
        "state": "state",
        "country": "India",
        "pin": 431603
    }
}
*/
@PostMapping(path = "/add")
public ResponseEntity<StudentResponse> addStudent(WebRequest webRequest, @RequestBody StudentRequest studentRequest) {
    return new ResponseEntity<>(studentService.addStudent(studentRequest, webRequest), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/delete")
    public ResponseEntity<String> deleteStudent(WebRequest webRequest, @RequestParam("uuid") UUID uuid) {
        return new ResponseEntity<>(studentService.deleteStudent(uuid, webRequest), HttpStatus.OK);
    }
}