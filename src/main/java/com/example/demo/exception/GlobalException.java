package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ProblemDetail> IllegalArgumentException(IllegalArgumentException ex,
            WebRequest request) {
        final ProblemDetail errorDetails = ProblemDetail.forStatusAndDetail(HttpStatus.PRECONDITION_FAILED,
                ex.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.PRECONDITION_FAILED);
    }

}