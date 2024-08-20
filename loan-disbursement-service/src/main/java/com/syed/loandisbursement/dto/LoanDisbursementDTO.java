package com.syed.loandisbursement.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanDisbursementDTO {

    @NotNull(message = "ID cannot be null")
    private Long id;

    @NotNull(message = "Loan Application ID cannot be null")
    private Long loanApplicationId;

    @NotBlank(message = "Status cannot be blank")
    @Size(max = 20, message = "Status cannot exceed 20 characters")
    private String status;

    @Size(max = 255, message = "Approval comments cannot exceed 255 characters")
    private String approvalComments;

}
