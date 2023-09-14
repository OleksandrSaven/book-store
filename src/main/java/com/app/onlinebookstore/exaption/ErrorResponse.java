package com.app.onlinebookstore.exaption;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorResponse(LocalDateTime timestamp, HttpStatus status, List<String> errors) {
}
