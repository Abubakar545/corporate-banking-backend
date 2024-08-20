package com.syed.loandisbursement.controller;

import com.syed.loandisbursement.dto.LoanApplicationDTO;
import com.syed.loandisbursement.dto.LoanDisbursementDTO;
import com.syed.loandisbursement.exception.ResourceNotFoundException;
import com.syed.loandisbursement.client.LoanApplicationClient;
import com.syed.loandisbursement.service.LoanDisbursementService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/loan-disbursements")
@Validated
public class LoanDisbursementController {

    @Autowired
    private LoanApplicationClient loanApplicationClient;

    @Autowired
    private LoanDisbursementService loanDisbursementService;

    // Fetch a loan application by ID
    @GetMapping("/fetch-application/{id}")
    public ResponseEntity<LoanApplicationDTO> fetchApplicationById(@PathVariable("id") Long id) {
        LoanApplicationDTO loanApplicationDTO = loanApplicationClient.getApplicationById(id);
        return ResponseEntity.ok(loanApplicationDTO);
    }

    // Submit a new loan disbursement
    @PostMapping("/submit")
    public ResponseEntity<LoanDisbursementDTO> submitDisbursement(
            @RequestBody @Valid LoanDisbursementDTO loanDisbursementDTO) throws ResourceNotFoundException {
        LoanDisbursementDTO createdDisbursement = loanDisbursementService.submitDisbursement(loanDisbursementDTO);
        return ResponseEntity.ok(createdDisbursement);
    }

    // Get all loan disbursements
    @GetMapping("/all")
    public ResponseEntity<List<LoanDisbursementDTO>> getAllDisbursements() {
        List<LoanDisbursementDTO> disbursements = loanDisbursementService.getAllDisbursements();
        return ResponseEntity.ok(disbursements);
    }

    // Approve a loan disbursement by ID
    @PutMapping("/approve/{id}")
    public ResponseEntity<LoanDisbursementDTO> approveDisbursement(@PathVariable Long id) throws ResourceNotFoundException {
        LoanDisbursementDTO approvedDisbursement = loanDisbursementService.approveDisbursement(id);
        return ResponseEntity.ok(approvedDisbursement);
    }

    // Reject a loan disbursement by ID
    @PutMapping("/reject/{id}")
    public ResponseEntity<LoanDisbursementDTO> rejectDisbursement(@PathVariable Long id) throws ResourceNotFoundException {
        LoanDisbursementDTO rejectedDisbursement = loanDisbursementService.rejectDisbursement(id);
        return ResponseEntity.ok(rejectedDisbursement);
    }
}
