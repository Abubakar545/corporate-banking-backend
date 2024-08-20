package com.syed.loanapplication.controller;

import com.syed.loanapplication.dto.LoanApplicationDTO;
import com.syed.loanapplication.exception.ResourceNotFoundException;
import com.syed.loanapplication.service.ILoanApplicationService;
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

@RestController
@RequestMapping("/api/v1/loan-applications")
public class LoanApplicationController {

    @Autowired
    private ILoanApplicationService loanApplicationService;

    @PostMapping
    public ResponseEntity<LoanApplicationDTO> createLoanApplication(@Valid @RequestBody LoanApplicationDTO loanApplicationDTO) {
        try {
            LoanApplicationDTO createdLoanApplication = loanApplicationService.createLoanApplication(loanApplicationDTO);
            return new ResponseEntity<>(createdLoanApplication, HttpStatus.CREATED);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<LoanApplicationDTO> updateLoanApplication(
            @PathVariable("id") Long id,
            @Valid @RequestBody LoanApplicationDTO loanApplicationDTO) {
        try {
            LoanApplicationDTO updatedLoanApplication = loanApplicationService.updateLoanApplication(id, loanApplicationDTO);
            return new ResponseEntity<>(updatedLoanApplication, HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLoanApplication(@PathVariable("id") Long id) {
        try {
            loanApplicationService.deleteLoanApplication(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanApplicationDTO> getLoanApplicationById(@PathVariable("id") Long id) {
        try {
            LoanApplicationDTO loanApplicationDTO = loanApplicationService.getLoanApplicationById(id);
            return new ResponseEntity<>(loanApplicationDTO, HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<LoanApplicationDTO>> getAllLoanApplications() {
        List<LoanApplicationDTO> loanApplications = loanApplicationService.getAllLoanApplications();
        return new ResponseEntity<>(loanApplications, HttpStatus.OK);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
