package com.training.jdbcdemoforcrud.controller;

import com.training.jdbcdemoforcrud.model.request.DepartmentRequest;
import com.training.jdbcdemoforcrud.model.response.DepartmentResponse;
import com.training.jdbcdemoforcrud.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

//http://localhost:8080/collage/department
@RestController
@RequestMapping("/collage/department")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    @GetMapping(path = "/all")
    public ResponseEntity<List<DepartmentResponse>> getDepartments() {
        return (new ResponseEntity<>(departmentService.getAllDepartmentsList(),HttpStatus.OK));

    }

    @GetMapping(path = "/single")
    public ResponseEntity<DepartmentResponse> getDepartment(@RequestParam(name = "uuid")UUID uuid) {
        return (new ResponseEntity<>(departmentService.getDepartment(uuid),HttpStatus.OK));
    }
/*
{
    "name": "dev"
}
*/
    @PostMapping()
    public ResponseEntity<DepartmentResponse> addDepartment(@RequestBody DepartmentRequest departmentRequest) {
        return new ResponseEntity<>(departmentService.addDepartment(departmentRequest), HttpStatus.CREATED);
    }

    @DeleteMapping()
    public ResponseEntity<String> deleteDepartment(@RequestParam(name = "uuid")UUID uuid) {
        return new ResponseEntity<>(departmentService.deleteDepartment(uuid), HttpStatus.OK);
    }
}