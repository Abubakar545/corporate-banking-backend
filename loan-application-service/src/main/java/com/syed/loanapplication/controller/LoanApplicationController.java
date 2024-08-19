package com.syed.loanapplication.controller;

import com.syed.loanapplication.dto.LoanApplicationDTO;
import com.syed.loanapplication.service.ILoanApplicationService;
import com.syed.loanapplication.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/loan-applications")
public class LoanApplicationController {

    @Autowired
    private ILoanApplicationService loanApplicationService;


    @PostMapping
    public ResponseEntity<LoanApplicationDTO> createLoanApplication(@RequestBody LoanApplicationDTO loanApplicationDTO) {
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
            @RequestBody LoanApplicationDTO loanApplicationDTO) {
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
}
