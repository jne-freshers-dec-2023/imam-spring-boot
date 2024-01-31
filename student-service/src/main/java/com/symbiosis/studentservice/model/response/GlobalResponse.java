package com.symbiosis.studentservice.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class GlobalResponse {
    HttpStatus status;
    Integer code;
    Object data;
    String message;
}
