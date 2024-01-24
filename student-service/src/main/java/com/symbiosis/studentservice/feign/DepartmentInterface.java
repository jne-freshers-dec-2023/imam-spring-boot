package com.symbiosis.studentservice.feign;

import com.symbiosis.studentservice.model.response.DepartmentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@FeignClient("DEPARTMENT-SERVICE")
public interface DepartmentInterface {

    @GetMapping(path = "/symbiosis/department/all")
    public ResponseEntity<List<DepartmentResponse>> getDepartments();

    @GetMapping(path = "/symbiosis/department/single")
    public ResponseEntity<DepartmentResponse> getDepartment(@RequestParam(name = "uuid") UUID uuid);

}
