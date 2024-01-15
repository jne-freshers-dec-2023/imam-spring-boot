package com.symbiosis.departmentservice.controller;


import com.symbiosis.departmentservice.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/symbiosis/department")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;
    @GetMapping(value = "/getalldepartment")
    public String getAllDepartment(){

        departmentService.getAllDepartments();
        return "Sent All Department";
    }
    @GetMapping(value = "getdepartment")
    public String getDepartment(){
        return "Sent A Department";

    }
    @PostMapping(value = "/adddepartment")
    public String addDepartment(){
        return "Added A Department";

    }
    @PutMapping(value = "/updatedepartment")
    public String updateDepartment(){
        return "Updated A Department";

    }
    @DeleteMapping(value = "/deletedepartment")
    public String deleteDepartment(){
        return "Deleted A Department";

    }
}
