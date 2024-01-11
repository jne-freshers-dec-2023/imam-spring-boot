package com.training.daytthreedemo.controller;

import com.training.daytthreedemo.model.Details;
import com.training.daytthreedemo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    @Autowired
    DemoService demoService;


    @RequestMapping("/details")
    public Details responseDetails() {
        return demoService.getDetails();
    }

}
