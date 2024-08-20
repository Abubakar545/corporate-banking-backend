package com.syed.loanapplication.dto;

import jakarta.validation.constraints.*;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoanOfficerDTO {

    private Long officerId;

    @NotBlank(message = "Officer name is required")
    private String officerName;

    @NotBlank(message = "Officer email is required")
    @Email(message = "Email should be valid")
    private String officerEmail;

    @NotBlank(message = "Officer phone is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number should be valid and contain 10 digits")
    private String officerPhone;
}
