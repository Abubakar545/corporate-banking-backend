package com.syed.loanapplication.dto;

import com.syed.loanapplication.enums.ApplicationStatus;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class LoanReviewDTO {

    private Long reviewId;

    @NotNull(message = "Application ID is required")
    private Long applicationId; // Foreign key

    @NotNull(message = "Officer ID is required")
    private Long officerId; // Foreign key

    @NotBlank(message = "Review comments are required")
    private String reviewComments;

    @NotNull(message = "Approval status is required")
    private ApplicationStatus approvalStatus;

    @NotNull(message = "Review date is required")
    private Date reviewDate;

    private Date createdAt;
    private Date updatedAt;
}
