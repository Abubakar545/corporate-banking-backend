package com.syed.loanapplication.mapper;

import com.syed.loanapplication.dto.LoanApplicationDTO;
import com.syed.loanapplication.entity.LoanApplication;
import com.syed.loanapplication.entity.CorporateClient;
import com.syed.loanapplication.enums.ApplicationStatus;

public class LoanApplicationMapper {

    // Convert Entity to DTO
    public LoanApplicationDTO toDTO(LoanApplication loanApplication) {
        if (loanApplication == null) {
            return null;
        }

        LoanApplicationDTO dto = new LoanApplicationDTO(
                loanApplication.getApplicationId(),
                loanApplication.getCorporateClient() != null ? loanApplication.getCorporateClient().getClientId() : null,
                loanApplication.getLoanType(),
                loanApplication.getLoanAmount(),
                loanApplication.getApplicationStatus(),
                loanApplication.getSubmissionDate(),
                loanApplication.getReviewDate(),
                loanApplication.getCreatedAt(),
                loanApplication.getUpdatedAt()
        );

        return dto;
    }

    // Convert DTO to Entity
    public LoanApplication toEntity(LoanApplicationDTO loanApplicationDTO) {
        if (loanApplicationDTO == null) {
            return null;
        }

        LoanApplication loanApplication = new LoanApplication();
        loanApplication.setApplicationId(loanApplicationDTO.getApplicationId());
        // Assume a method to fetch CorporateClient by ID
        CorporateClient client = findCorporateClientById(loanApplicationDTO.getClientId());
        loanApplication.setCorporateClient(client);
        loanApplication.setLoanType(loanApplicationDTO.getLoanType());
        loanApplication.setLoanAmount(loanApplicationDTO.getLoanAmount());
        loanApplication.setApplicationStatus(loanApplicationDTO.getApplicationStatus());
        loanApplication.setSubmissionDate(loanApplicationDTO.getSubmissionDate());
        loanApplication.setReviewDate(loanApplicationDTO.getReviewDate());
        loanApplication.setCreatedAt(loanApplicationDTO.getCreatedAt());
        loanApplication.setUpdatedAt(loanApplicationDTO.getUpdatedAt());

        return loanApplication;
    }

    // Mock method to simulate fetching CorporateClient
    private CorporateClient findCorporateClientById(Long clientId) {
        // Implementation to fetch CorporateClient by ID from the database or other source
        return new CorporateClient(); // Example placeholder
    }
}
