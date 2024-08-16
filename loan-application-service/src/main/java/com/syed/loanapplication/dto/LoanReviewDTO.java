package com.syed.loanapplication.dto;

import com.syed.loanapplication.enums.ApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.Date;

@Data
@AllArgsConstructor
public class LoanReviewDTO {

    private Long reviewId;
    private Long applicationId; // Foreign key
    private Long officerId; // Foreign key
    private String reviewComments;
    private ApplicationStatus approvalStatus; // Update to use ApplicationStatus
    private Date reviewDate;
    private Date createdAt;
    private Date updatedAt;
}
