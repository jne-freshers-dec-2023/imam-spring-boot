package com.training.jdbcdemoforcrud.controller;

import com.training.jdbcdemoforcrud.entity.Department;
import com.training.jdbcdemoforcrud.model.request.DepartmentRequest;
import com.training.jdbcdemoforcrud.model.response.DepartmentResponse;
import com.training.jdbcdemoforcrud.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//http://localhost:8080/collage/department
@RestController
@RequestMapping("/collage/department")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    @GetMapping()
    public ResponseEntity<List<DepartmentResponse>> getDepartments() {
        return (new ResponseEntity<>(departmentService.getAllDepartmentsList(),HttpStatus.OK));

    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentResponse> getDepartment(@PathVariable int id) {
        return (new ResponseEntity<>(departmentService.getDepartment(id),HttpStatus.OK));
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

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK , reason = "Deleted")
    public void deleteDepartment(@PathVariable int id) {
        departmentService.deleteDepartment(id);
    }
}