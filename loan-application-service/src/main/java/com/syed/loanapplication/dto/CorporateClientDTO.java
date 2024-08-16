package com.syed.loanapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data @AllArgsConstructor
public class CorporateClientDTO {
    private Long clientId;
    private String companyName;
    private String contactPerson;
    private String contactEmail;
    private String contactPhone;
    private String address;
    private Set<LoanApplicationDTO> loanApplicationDTOs; // Initialize this field

    // Default constructor
    public CorporateClientDTO() {
        // Initialize the Set if needed
        this.loanApplicationDTOs = new HashSet<>();
    }

}
