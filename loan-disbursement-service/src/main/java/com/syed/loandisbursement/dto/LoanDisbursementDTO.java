package com.syed.loandisbursement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanDisbursementDTO {

    private Long id;
    private Long loanApplicationId;
    private String status;
    private String approvalComments;

}