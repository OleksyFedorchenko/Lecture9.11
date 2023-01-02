package com.example.Lecture91.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorDTO {
    private String message;
    private LocalDateTime time;

    public ErrorDTO(String message) {
        this.message = message;
        this.time = LocalDateTime.now();
    }
}
