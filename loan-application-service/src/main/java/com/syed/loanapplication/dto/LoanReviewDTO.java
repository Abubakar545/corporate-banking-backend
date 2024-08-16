package com.syed.loanapplication.dto;

import com.syed.loanapplication.enums.ApprovalStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.Date;

@Data @AllArgsConstructor
public class LoanReviewDTO {

    private Long reviewId;
    private Long applicationId; // Foreign key
    private Long officerId; // Foreign key
    private String reviewComments;
    private ApprovalStatus approvalStatus;
    private Date reviewDate;
    private Date createdAt;
    private Date updatedAt;

}
