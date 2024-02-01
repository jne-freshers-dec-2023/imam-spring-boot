package com.training.daytthreedemo.service;


import com.training.daytthreedemo.model.Details;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

@Service
public class DemoService {
    @Autowired
    Details details;

    public Details getDetails(){
        return details;
    }
}
