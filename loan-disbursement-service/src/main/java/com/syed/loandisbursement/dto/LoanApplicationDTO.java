package com.syed.loandisbursement.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class LoanApplicationDTO {

    @NotNull(message = "ID cannot be null")
    private Long id;

    @NotBlank(message = "Applicant name cannot be blank")
    @Size(max = 50, message = "Applicant name cannot exceed 50 characters")
    private String applicantName;

    @NotBlank(message = "Application status cannot be blank")
    @Size(max = 20, message = "Application status cannot exceed 20 characters")
    private String applicationStatus;

    @NotNull(message = "Amount cannot be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Amount must be greater than zero")
    private Double amount;

    @Size(max = 500, message = "Application details cannot exceed 500 characters")
    private String applicationDetails;

}
