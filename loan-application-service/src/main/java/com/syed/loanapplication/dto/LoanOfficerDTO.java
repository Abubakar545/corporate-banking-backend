package com.syed.loanapplication.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoanOfficerDTO {
    private Long officerId;
    private String officerName;
    private String officerEmail;
    private String officerPhone; // New Field
}
