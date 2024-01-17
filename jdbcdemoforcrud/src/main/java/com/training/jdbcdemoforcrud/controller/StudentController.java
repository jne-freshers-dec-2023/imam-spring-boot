package com.training.jdbcdemoforcrud.controller;

import com.training.jdbcdemoforcrud.model.request.StudentRequest;
import com.training.jdbcdemoforcrud.model.response.StudentResponse;
import com.training.jdbcdemoforcrud.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//http://localhost:8080/collage/student
@RestController
@RequestMapping("/collage/student")
public class StudentController {
    @Autowired
    StudentService studentService;

    @GetMapping()
    public ResponseEntity<List<StudentResponse>> getStudents() {
        return (new ResponseEntity<>(studentService.getAllStudentList(),HttpStatus.OK));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse> getStudent(@PathVariable int id) {
        return new ResponseEntity<>(studentService.getStudent(id), HttpStatus.OK);
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

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK, reason = "Deleted")
    public void deleteStudent(@PathVariable int id) {
        studentService.deleteStudent(id);
    }
}