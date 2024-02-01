package com.training.jdbcdemoforcrud.controller;

import com.training.jdbcdemoforcrud.model.request.DepartmentRequest;
import com.training.jdbcdemoforcrud.model.response.DepartmentResponse;
import com.training.jdbcdemoforcrud.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.UUID;

//http://localhost:8080/collage/department
@RestController
@RequestMapping("/collage/department")
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

    @DeleteMapping(path = "/delete")
    public ResponseEntity<String> deleteDepartment(WebRequest webRequest, @RequestParam(name = "uuid") UUID uuid) {
        return new ResponseEntity<>(departmentService.deleteDepartment(uuid, webRequest), HttpStatus.OK);
    }
}