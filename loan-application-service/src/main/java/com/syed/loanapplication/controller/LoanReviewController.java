package com.syed.loanapplication.controller;

import com.syed.loanapplication.dto.LoanReviewDTO;
import com.syed.loanapplication.exception.ResourceNotFoundException;
import com.syed.loanapplication.service.ILoanReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/loan-reviews")
public class LoanReviewController {

    @Autowired
    private ILoanReviewService loanReviewService;

    @GetMapping("/{id}")
    public ResponseEntity<LoanReviewDTO> getLoanReview(@PathVariable Long id) {
        Optional<LoanReviewDTO> loanReviewDTO = loanReviewService.getLoanReview(id);
        return loanReviewDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<LoanReviewDTO> createLoanReview(@RequestBody LoanReviewDTO loanReviewDTO) {
        LoanReviewDTO createdLoanReview = loanReviewService.createLoanReview(loanReviewDTO);
        return new ResponseEntity<>(createdLoanReview, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LoanReviewDTO> updateLoanReview(@PathVariable Long id, @RequestBody LoanReviewDTO loanReviewDTO) {
        try {
            LoanReviewDTO updatedLoanReview = loanReviewService.updateLoanReview(id, loanReviewDTO);
            return ResponseEntity.ok(updatedLoanReview);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLoanReview(@PathVariable Long id) {
        try {
            loanReviewService.deleteLoanReview(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<LoanReviewDTO>> getAllLoanReviews() {
        List<LoanReviewDTO> loanReviews = loanReviewService.getAllLoanReviews();
        return ResponseEntity.ok(loanReviews);
    }
}
