package com.symbiosis.studentservice.controller;

import com.symbiosis.studentservice.model.request.StudentRequest;
import com.symbiosis.studentservice.model.response.GlobalResponse;
import com.symbiosis.studentservice.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.symbiosis.studentservice.constants.ApiConstants.STUDENT_CONTROLLER;
import static com.symbiosis.studentservice.constants.ApiConstants.UUID;
import static com.symbiosis.studentservice.constants.StudentServiceConstants.*;

//http://localhost:8081/api/v1/symbiosis/student
@RestController
@RequestMapping(path = "/api/v1/symbiosis/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping()
    public ResponseEntity<GlobalResponse> getStudents(@RequestParam(required = false, defaultValue = DEFAULT_ORDER) String direction,
                                                      @RequestParam(required = false, defaultValue = DEFAULT_ORDER_BY) String orderBy,
                                                      @RequestParam(required = false, defaultValue = DEFAULT_PAGE) Integer pageNumber,
                                                      @RequestParam(required = false, defaultValue = DEFAULT_PAGE_SIZE) Integer pageSize) {
        return (new ResponseEntity<>(studentService.getAllStudents(direction, orderBy, pageNumber, pageSize), HttpStatus.OK));
    }

    @GetMapping(path = UUID)
    public ResponseEntity<GlobalResponse> getStudent(@PathVariable UUID uuid) {
        return new ResponseEntity<>(studentService.getStudent(uuid), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<GlobalResponse> addStudent(@RequestBody StudentRequest studentRequest) {
        return new ResponseEntity<>(studentService.addStudent(studentRequest), HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<GlobalResponse> updateStudent(@RequestBody StudentRequest studentRequest) {
        return new ResponseEntity<>(studentService.updateStudent(studentRequest), HttpStatus.OK);

    }

    @DeleteMapping(path = UUID)
    public ResponseEntity<GlobalResponse> deleteStudent(@PathVariable("uuid") UUID uuid) {
        return new ResponseEntity<>(studentService.deleteStudent(uuid), HttpStatus.OK);
    }
}