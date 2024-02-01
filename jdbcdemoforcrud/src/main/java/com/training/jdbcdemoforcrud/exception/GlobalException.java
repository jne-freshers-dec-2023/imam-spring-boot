package com.training.jdbcdemoforcrud.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class GlobalException extends RuntimeException {
    String message;
    HttpStatus httpStatus;
    Date timeStamp;
    String details;
}
