package com.syed.loanapplication.dto;

import jakarta.validation.constraints.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
public class CorporateClientDTO {

    private Long clientId;

    @NotBlank(message = "Company name is required")
    private String companyName;

    @NotBlank(message = "Contact person is required")
    private String contactPerson;

    @NotBlank(message = "Contact email is required")
    @Email(message = "Email should be valid")
    private String contactEmail;

    @NotBlank(message = "Contact phone is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number should be valid and contain 10 digits")
    private String contactPhone;

    @NotBlank(message = "Address is required")
    private String address;

    @NotEmpty(message = "Loan applications cannot be empty")
    private Set<LoanApplicationDTO> loanApplicationDTOs;

    // Default constructor
    public CorporateClientDTO() {
        this.loanApplicationDTOs = new HashSet<>();
    }
}
