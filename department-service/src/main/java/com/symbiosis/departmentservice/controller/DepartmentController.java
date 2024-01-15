package com.symbiosis.departmentservice.controller;


import com.symbiosis.departmentservice.entity.Department;
import com.symbiosis.departmentservice.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/symbiosis/department")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @GetMapping()
    public ResponseEntity<List<Department>> getAllDepartment() {

        List<Department> departmentList = departmentService.getAllDepartments();
        if (departmentList != null) {
            return (new ResponseEntity<>(departmentList, HttpStatus.OK));
        } else {
            return (new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Department> getDepartment(@PathVariable int id) {
        Department department = departmentService.getDepartmentByID(id);
        if (department != null) {
            return (new ResponseEntity<>(department, HttpStatus.OK));
        } else {
            return (new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }

    }

    @PostMapping()
    public ResponseEntity<Department> addDepartment(@RequestBody Department department) {
        return new ResponseEntity<>(departmentService.addDepartment(department), HttpStatus.CREATED);

    }

    @PutMapping()
    public ResponseEntity<String> updateDepartment(@RequestBody Department department) {
        if (departmentService.updateDepartment(department)) {
            return new ResponseEntity<>("Saved The Department", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Created new Department", HttpStatus.CREATED);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable int id) {
        if (departmentService.deleteDepartmentById(id)) {
            return new ResponseEntity<>("Deleted The Department", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Department Not Available", HttpStatus.NOT_FOUND);
        }

    }
}
