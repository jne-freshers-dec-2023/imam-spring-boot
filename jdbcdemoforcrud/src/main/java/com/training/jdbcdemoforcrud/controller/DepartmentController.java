package com.training.jdbcdemoforcrud.controller;

import com.training.jdbcdemoforcrud.model.Department;
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
    public List<Department> getDepartments() {
        return departmentService.getAllDepartmentsList();
    }

    @GetMapping("/{id}")
    public Department getDepartment(@PathVariable int id) {
        return departmentService.getDepartment(id);
    }

    @PostMapping()
    public ResponseEntity<Department> addDepartment(@RequestBody Department department) {
        return new ResponseEntity<>(departmentService.addDepartment(department), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK , reason = "Deleted")
    public void deleteDepartment(@PathVariable int id) {
        departmentService.deleteDepartment(id);
    }
}
