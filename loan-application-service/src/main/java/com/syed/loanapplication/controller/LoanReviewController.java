package com.syed.loanapplication.controller;

import com.syed.loanapplication.dto.LoanReviewDTO;
import com.syed.loanapplication.exception.ResourceNotFoundException;
import com.syed.loanapplication.service.ILoanReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    public ResponseEntity<LoanReviewDTO> createLoanReview(@Valid @RequestBody LoanReviewDTO loanReviewDTO) {
        LoanReviewDTO createdLoanReview = loanReviewService.createLoanReview(loanReviewDTO);
        return new ResponseEntity<>(createdLoanReview, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LoanReviewDTO> updateLoanReview(@PathVariable Long id, @Valid @RequestBody LoanReviewDTO loanReviewDTO) {
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errors);
    }
}
