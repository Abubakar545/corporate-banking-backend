package com.syed.loanapplication.controller;

import com.syed.loanapplication.constants.ResponseConstants;
import com.syed.loanapplication.dto.LoanOfficerDTO;
import com.syed.loanapplication.exception.ResourceNotFoundException;
import com.syed.loanapplication.service.ILoanOfficerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/loan-officers")
public class LoanOfficerController {

    private final ILoanOfficerService loanOfficerService;

    public LoanOfficerController(ILoanOfficerService loanOfficerService) {
        this.loanOfficerService = loanOfficerService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getLoanOfficer(@PathVariable Long id) {
        Optional<LoanOfficerDTO> loanOfficerDTO = loanOfficerService.getLoanOfficer(id);
        if (loanOfficerDTO.isPresent()) {
            return ResponseEntity
                    .status(ResponseConstants.STATUS_OK)
                    .body(loanOfficerDTO.get());
        } else {
            return ResponseEntity
                    .status(ResponseConstants.STATUS_NOT_FOUND)
                    .body(ResponseConstants.MESSAGE_NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<LoanOfficerDTO>> getAllLoanOfficers() {
        List<LoanOfficerDTO> loanOfficers = loanOfficerService.getAllLoanOfficers();
        return ResponseEntity
                .status(ResponseConstants.STATUS_OK)
                .body(loanOfficers);
    }

    @PostMapping
    public ResponseEntity<?> createLoanOfficer(@Valid @RequestBody LoanOfficerDTO loanOfficerDTO) {
        try {
            LoanOfficerDTO createdLoanOfficer = loanOfficerService.createLoanOfficer(loanOfficerDTO);
            return ResponseEntity
                    .status(ResponseConstants.STATUS_CREATED)
                    .body(createdLoanOfficer);
        } catch (Exception e) {
            // Consider logging the exception
            return ResponseEntity
                    .status(ResponseConstants.STATUS_INTERNAL_SERVER_ERROR)
                    .body(ResponseConstants.MESSAGE_INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateLoanOfficer(@PathVariable Long id, @Valid @RequestBody LoanOfficerDTO loanOfficerDTO) {
        try {
            LoanOfficerDTO updatedLoanOfficer = loanOfficerService.updateLoanOfficer(id, loanOfficerDTO);
            return ResponseEntity
                    .status(ResponseConstants.STATUS_OK)
                    .body(updatedLoanOfficer);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity
                    .status(ResponseConstants.STATUS_NOT_FOUND)
                    .body(ResponseConstants.MESSAGE_NOT_FOUND);
        } catch (Exception e) {
            // Consider logging the exception
            return ResponseEntity
                    .status(ResponseConstants.STATUS_INTERNAL_SERVER_ERROR)
                    .body(ResponseConstants.MESSAGE_INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLoanOfficer(@PathVariable Long id) {
        try {
            loanOfficerService.deleteLoanOfficer(id);
            return ResponseEntity
                    .status(ResponseConstants.STATUS_NO_CONTENT)
                    .body(ResponseConstants.MESSAGE_OK); // Changed to MESSAGE_OK if there's no specific message
        } catch (ResourceNotFoundException e) {
            return ResponseEntity
                    .status(ResponseConstants.STATUS_NOT_FOUND)
                    .body(ResponseConstants.MESSAGE_NOT_FOUND);
        } catch (Exception e) {
            // Consider logging the exception
            return ResponseEntity
                    .status(ResponseConstants.STATUS_INTERNAL_SERVER_ERROR)
                    .body(ResponseConstants.MESSAGE_INTERNAL_SERVER_ERROR);
        }
    }
}
