package com.syed.loanapplication.dto;

import com.syed.loanapplication.enums.ApplicationStatus;
import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Data;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
public class LoanApplicationDTO {

    private Long applicationId;

    @NotNull(message = "Client ID is required")
    private Long clientId; // Foreign key

    @NotBlank(message = "Loan type is required")
    private String loanType;

    @NotNull(message = "Loan amount is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Loan amount must be greater than zero")
    private BigDecimal loanAmount;

    @NotNull(message = "Application status is required")
    private ApplicationStatus applicationStatus;

    @NotNull(message = "Submission date is required")
    private Date submissionDate;

    private Date reviewDate;

    private Date createdAt;
    private Date updatedAt;
}
