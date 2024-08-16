package com.syed.loanapplication.exception;

import com.syed.loanapplication.constants.ResponseConstants;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception e) {
        return ResponseEntity
                .status(ResponseConstants.STATUS_INTERNAL_SERVER_ERROR)
                .body(ResponseConstants.MESSAGE_INTERNAL_SERVER_ERROR + ": " + e.getMessage());
    }

    // Add other exception handlers as needed
}
