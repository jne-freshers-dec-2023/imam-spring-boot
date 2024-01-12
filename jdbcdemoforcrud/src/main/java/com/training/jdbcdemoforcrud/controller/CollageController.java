package com.training.jdbcdemoforcrud.controller;

import com.training.jdbcdemoforcrud.model.Address;
import com.training.jdbcdemoforcrud.model.Department;
import com.training.jdbcdemoforcrud.model.Student;
import com.training.jdbcdemoforcrud.service.impl.CollageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/collage")
public class CollageController {
    @Autowired
    CollageService collageService;

    //http://localhost:8080/collage/allstudents
    @GetMapping("/allstudents")
    public List<Student> getStudents() {
        return collageService.getAllStudentList();
    }

    //http://localhost:8080/collage/alldepartments
    @GetMapping("/alldepartments")
    public List<Department> getDepartments() {
        return collageService.getAllDepartmentsList();
    }

    //http://localhost:8080/collage/alladdresses
    @GetMapping("/alladdresses")
    public List<Address> getAddress() {
        return collageService.getAllAddresssList();
    }

    @PostMapping("/addDepartment")
    public ResponseEntity<Department> addDepartment(@RequestBody Department department) {
        return new ResponseEntity<>(collageService.addDepartment(department), HttpStatus.OK);
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

    @PostMapping("/addStudent")
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        return new ResponseEntity<>(collageService.addStudent(student), HttpStatus.OK);
    }
}
