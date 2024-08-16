package com.syed.loanapplication.repository;


import com.syed.loanapplication.entity.LoanReview;
import com.syed.loanapplication.enums.ApprovalStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanReviewRepository extends JpaRepository<LoanReview, Long> {
    // Custom query methods can be defined here if needed
    List<LoanReview> findByApprovalStatus(ApprovalStatus status);
}
