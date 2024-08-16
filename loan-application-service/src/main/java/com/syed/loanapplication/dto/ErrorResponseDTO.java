package com.syed.loanapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponseDTO {

    private String errorCode;
    private String errorMessage;
    private String errorDetails;
    private LocalDateTime errorTime;
    private String apiPath;
}
