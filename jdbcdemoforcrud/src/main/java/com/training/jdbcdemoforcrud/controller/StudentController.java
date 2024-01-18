package com.training.jdbcdemoforcrud.controller;

import com.training.jdbcdemoforcrud.model.request.StudentRequest;
import com.training.jdbcdemoforcrud.model.response.StudentResponse;
import com.training.jdbcdemoforcrud.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

//http://localhost:8080/collage/student
@RestController
@RequestMapping(path = "/collage/student")
public class StudentController {
    @Autowired
    StudentService studentService;

    @GetMapping(path = "/all")
    public ResponseEntity<List<StudentResponse>> getStudents() {
        return (new ResponseEntity<>(studentService.getAllStudentList(),HttpStatus.OK));
    }

    @GetMapping(path = "/single")
    public ResponseEntity<StudentResponse> getStudent(@RequestParam(name = "uuid") UUID uuid) {
        return new ResponseEntity<>(studentService.getStudent(uuid), HttpStatus.OK);
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
    @PostMapping()
    public ResponseEntity<StudentResponse> addStudent(@RequestBody StudentRequest studentRequest) {
        return new ResponseEntity<>(studentService.addStudent(studentRequest), HttpStatus.CREATED);
    }

    @DeleteMapping()
    public ResponseEntity<String> deleteStudent(@RequestParam("uuid") UUID uuid) {
        return new ResponseEntity<>(studentService.deleteStudent(uuid), HttpStatus.OK);
    }
}