package com.syed.loandisbursement.dto;

import lombok.Data;

@Data
public class LoanApplicationDTO {
    private Long id;
    private String applicantName;
    private String applicationStatus;
    private Double amount;
    private String applicationDetails;

}