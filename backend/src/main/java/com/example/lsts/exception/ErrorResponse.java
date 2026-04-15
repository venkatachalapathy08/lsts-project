package com.example.lsts.exception;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class ErrorResponse {

    private LocalDateTime timestamp;
    private String message;
    private String error;

    public ErrorResponse(LocalDateTime timestamp, String message, String error) {
        this.timestamp = timestamp;
        this.message = message;
        this.error = error;
    }

    // Getters & Setters
}