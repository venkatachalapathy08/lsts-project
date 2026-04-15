package com.example.lsts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Handle Not Found
    @ExceptionHandler(ResourceNotFoundException.class)
    public ProblemDetail handleNotFound(ResourceNotFoundException ex) {

        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problem.setTitle("Resource Not Found");
        problem.setDetail(ex.getMessage());
        problem.setProperty("timestamp", LocalDateTime.now());

        return problem;
    }

    // Handle Duplicate
    @ExceptionHandler(DuplicateResourceException.class)
    public ProblemDetail handleDuplicate(DuplicateResourceException ex) {

        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problem.setTitle("Duplicate Resource");
        problem.setDetail(ex.getMessage());
        problem.setProperty("timestamp", LocalDateTime.now());

        return problem;
    }

    // Handle Generic Exception
    @ExceptionHandler(Exception.class)
    public ProblemDetail handleGlobal(Exception ex) {

        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        problem.setTitle("Internal Server Error");
        problem.setDetail(ex.getMessage());
        problem.setProperty("timestamp", LocalDateTime.now());

        return problem;
    }
}