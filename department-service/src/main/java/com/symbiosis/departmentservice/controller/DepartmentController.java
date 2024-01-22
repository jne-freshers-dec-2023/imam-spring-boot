package com.symbiosis.departmentservice.controller;


import com.symbiosis.departmentservice.entity.Department;
import com.symbiosis.departmentservice.model.request.DepartmentRequest;
import com.symbiosis.departmentservice.model.response.DepartmentResponse;
import com.symbiosis.departmentservice.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.UUID;

//http://localhost:8082/symbiosis/department
@RestController
@RequestMapping("/symbiosis/department")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    @GetMapping(path = "/all")
    public ResponseEntity<List<DepartmentResponse>> getDepartments(WebRequest webRequest) {
        return (new ResponseEntity<>(departmentService.getAllDepartmentsList(webRequest), HttpStatus.OK));

    }

    @GetMapping(path = "/single")
    public ResponseEntity<DepartmentResponse> getDepartment(WebRequest webRequest, @RequestParam(name = "uuid") UUID uuid) {
        return (new ResponseEntity<>(departmentService.getDepartment(uuid, webRequest), HttpStatus.OK));
    }

    /*
    {
        "name": "dev"
    }
    */
    @PostMapping(path = "/add")
    public ResponseEntity<DepartmentResponse> addDepartment(WebRequest webRequest, @RequestBody DepartmentRequest departmentRequest) {
        return new ResponseEntity<>(departmentService.addDepartment(departmentRequest, webRequest), HttpStatus.CREATED);
    }

    /*
    {
    "uuid":"f47c1f32-1c5d-453d-9a0e-afef5197a9d7",
    "name":"ADMIN"
    }
    */

    @PutMapping(path = "/update")
    public ResponseEntity<String> updateDepartment(WebRequest webRequest, @RequestBody Department department) {
        return new ResponseEntity<>(departmentService.updateDepartment(department, webRequest), HttpStatus.OK);

    }

    @DeleteMapping(path = "/delete")
    public ResponseEntity<String> deleteDepartment(WebRequest webRequest, @RequestParam(name = "uuid") UUID uuid) {
        return new ResponseEntity<>(departmentService.deleteDepartment(uuid, webRequest), HttpStatus.OK);
    }
}