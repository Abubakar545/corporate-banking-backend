package com.syed.loanapplication.dto;

import com.syed.loanapplication.enums.ApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
public class LoanApplicationDTO {

    private Long applicationId;
    private Long clientId; // Foreign key
    private String loanType;
    private BigDecimal loanAmount;
    private ApplicationStatus applicationStatus;
    private Date submissionDate;
    private Date reviewDate;
    private Date createdAt;
    private Date updatedAt;
}

