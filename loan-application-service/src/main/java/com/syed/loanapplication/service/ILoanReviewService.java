package com.syed.loanapplication.service;

import com.syed.loanapplication.dto.LoanReviewDTO;

import java.util.List;
import java.util.Optional;

public interface ILoanReviewService {

    Optional<LoanReviewDTO> getLoanReview(Long id);

    LoanReviewDTO createLoanReview(LoanReviewDTO loanReviewDTO);

    LoanReviewDTO updateLoanReview(Long id, LoanReviewDTO loanReviewDTO);

    void deleteLoanReview(Long id);

    List<LoanReviewDTO> getAllLoanReviews();
}
