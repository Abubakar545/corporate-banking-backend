package com.syed.loandisbursement.service.impl;

import com.syed.loandisbursement.client.LoanApplicationClient;
import com.syed.loandisbursement.dto.LoanApplicationDTO;
import com.syed.loandisbursement.dto.LoanDisbursementDTO;
import com.syed.loandisbursement.entity.LoanDisbursement;
import com.syed.loandisbursement.exception.LoanApplicationNotApprovedException;
import com.syed.loandisbursement.exception.ResourceNotFoundException;
import com.syed.loandisbursement.repository.LoanDisbursementRepository;
import com.syed.loandisbursement.service.LoanDisbursementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanDisbursementServiceImpl implements LoanDisbursementService {

    @Autowired
    private LoanDisbursementRepository loanDisbursementRepository;

    @Autowired
    private LoanApplicationClient loanApplicationClient;

    @Override
    public LoanDisbursementDTO submitDisbursement(LoanDisbursementDTO loanDisbursementDTO) throws ResourceNotFoundException {
        // Fetch the loan application details using Feign Client
        LoanApplicationDTO loanApplicationDTO = loanApplicationClient.getApplicationById(loanDisbursementDTO.getLoanApplicationId());

        // Check if the loan application is approved
        if (loanApplicationDTO == null) {
            throw new ResourceNotFoundException("Loan application with ID " + loanDisbursementDTO.getLoanApplicationId() + " not found.");
        }

        if (!"APPROVED".equals(loanApplicationDTO.getApplicationStatus())) {
            throw new LoanApplicationNotApprovedException("Loan application with ID " + loanDisbursementDTO.getLoanApplicationId() + " is not approved.");
        }

        // Create and save LoanDisbursement
        LoanDisbursement loanDisbursement = new LoanDisbursement();
        loanDisbursement.setLoanApplicationId(loanDisbursementDTO.getLoanApplicationId());
        loanDisbursement.setApplicationStatus(loanApplicationDTO.getApplicationStatus());
        loanDisbursement.setApprovalComments(loanDisbursementDTO.getApprovalComments());
        loanDisbursement = loanDisbursementRepository.save(loanDisbursement);

        // Update DTO with saved data
        loanDisbursementDTO.setId(loanDisbursement.getId());
        loanDisbursementDTO.setStatus(loanDisbursement.getApplicationStatus());
        return loanDisbursementDTO;
    }


    @Override
    public List<LoanDisbursementDTO> getAllDisbursements() {
        List<LoanDisbursement> disbursements = loanDisbursementRepository.findAll();
        return disbursements.stream()
                .map(disbursement -> new LoanDisbursementDTO(
                        disbursement.getId(),
                        disbursement.getLoanApplicationId(),
                        disbursement.getApplicationStatus(),
                        disbursement.getApprovalComments()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public LoanDisbursementDTO approveDisbursement(Long id) throws ResourceNotFoundException {
        // Fetch Loan Disbursement from DB
        LoanDisbursement disbursement = loanDisbursementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("LoanDisbursement not found for this id :: " + id));

        // Fetch Loan Application using Feign Client
        LoanApplicationDTO loanApplicationDTO = loanApplicationClient.getApplicationById(disbursement.getLoanApplicationId());

        // Check if Loan Application is Approved
        if (loanApplicationDTO == null)
            throw new ResourceNotFoundException("Loan application with ID " + disbursement.getLoanApplicationId() + " not found.");

        if (!"APPROVED".equals(loanApplicationDTO.getApplicationStatus())) {
            throw new LoanApplicationNotApprovedException("Loan application with ID " + disbursement.getLoanApplicationId() + " is not approved.");
        }

        // Approve Disbursement
        disbursement.setApplicationStatus("Approved");
        loanDisbursementRepository.save(disbursement);

        // Prepare and return the DTO
        return new LoanDisbursementDTO(
                disbursement.getId(),
                disbursement.getLoanApplicationId(),
                disbursement.getApplicationStatus(),
                disbursement.getApprovalComments()
        );
    }

    @Override
    public LoanDisbursementDTO rejectDisbursement(Long id) throws ResourceNotFoundException {
        LoanDisbursement disbursement = loanDisbursementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("LoanDisbursement not found for this id :: " + id));

        // Reject Disbursement
        disbursement.setApplicationStatus("Rejected");
        loanDisbursementRepository.save(disbursement);

        // Prepare and return the DTO
        return new LoanDisbursementDTO(
                disbursement.getId(),
                disbursement.getLoanApplicationId(),
                disbursement.getApplicationStatus(),
                disbursement.getApprovalComments()
        );
    }
}
