package com.symbiosis.studentservice.controller;

import com.symbiosis.studentservice.model.request.StudentRequest;
import com.symbiosis.studentservice.model.response.StudentResponse;
import com.symbiosis.studentservice.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.UUID;

//http://localhost:8081/symbiosis/student
@RestController
@RequestMapping(path = "/symbiosis/student")
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
    public ResponseEntity<List<StudentResponse>> getStudentsPaged(WebRequest webRequest, @RequestParam(name = "pageNumber") int pageNumber, @RequestParam(name = "pageSize") int pageSize) {

        return new ResponseEntity<>(studentService.getAllStudentWithPagination(pageNumber, pageSize, webRequest), HttpStatus.OK);
    }

    @GetMapping(path = "/single")
    public ResponseEntity<StudentResponse> getStudent(WebRequest webRequest, @RequestParam(name = "uuid") UUID uuid) {
        return new ResponseEntity<>(studentService.getStudent(uuid, webRequest), HttpStatus.OK);
    }

    /*
    {
        "name": "Imam",
        "deptId": "f47c1f32-1c5d-453d-9a0e-afef5197a9d7",
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

    /*
{
    "uuid": "96d3bf95-ef10-4ccb-b497-6f9805a246c4",
    "name": "Imam",
    "address": {
        "city": "city",
        "state": "Maharashtra",
        "country": "India",
        "pin": 431603
    },
    "deptId": "f47c1f32-1c5d-453d-9a0e-afef5197a9d7"
}
    */

    @PutMapping(path = "/update")
    public ResponseEntity<String> updateDepartment(WebRequest webRequest, @RequestBody StudentRequest studentRequest) {
        return new ResponseEntity<>(studentService.updateStudent(studentRequest, webRequest), HttpStatus.OK);

    }

    @DeleteMapping(path = "/delete")
    public ResponseEntity<String> deleteStudent(WebRequest webRequest, @RequestParam("uuid") UUID uuid) {
        return new ResponseEntity<>(studentService.deleteStudent(uuid, webRequest), HttpStatus.OK);
    }
}