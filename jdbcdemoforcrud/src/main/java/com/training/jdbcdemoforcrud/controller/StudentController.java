package com.training.jdbcdemoforcrud.controller;

import com.training.jdbcdemoforcrud.model.Student;
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
    public List<Student> getStudents() {
        return studentService.getAllStudentList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable int id) {
        return new ResponseEntity<>(studentService.getStudent(id), HttpStatus.OK);
    }

    /*
        {
            "name": "Imam",
            "department": {
                   "name": "dev"
            },
            "address": {
                "city": "Nanded",
                "state": "Maharashtra",
                "country": "India",
                "pin": 431603
            }
        }
    */

    @PostMapping()
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        return new ResponseEntity<>(studentService.addStudent(student), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK, reason = "Deleted")
    public void deleteStudent(@PathVariable int id) {
        studentService.deleteStudent(id);
    }
}
