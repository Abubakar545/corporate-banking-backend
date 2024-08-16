package com.syed.loanapplication.mapper;

import com.syed.loanapplication.dto.LoanApplicationDTO;
import com.syed.loanapplication.entity.LoanApplication;
import com.syed.loanapplication.entity.CorporateClient;
import com.syed.loanapplication.enums.ApplicationStatus;
import com.syed.loanapplication.repository.CorporateClientRepository;
import com.syed.loanapplication.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoanApplicationMapper {

    @Autowired
    private CorporateClientRepository corporateClientRepository;

    // Convert Entity to DTO
    public LoanApplicationDTO toDTO(LoanApplication loanApplication) {
        if (loanApplication == null) {
            return null;
        }

        return new LoanApplicationDTO(
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
    }

    // Convert DTO to Entity
    public LoanApplication toEntity(LoanApplicationDTO loanApplicationDTO) {
        if (loanApplicationDTO == null) {
            return null;
        }

        LoanApplication loanApplication = new LoanApplication();
        loanApplication.setApplicationId(loanApplicationDTO.getApplicationId());
        // Fetch CorporateClient by ID
        CorporateClient client = corporateClientRepository.findById(loanApplicationDTO.getClientId())
                .orElseThrow(() -> new ResourceNotFoundException("CorporateClient", "clientId", loanApplicationDTO.getClientId()));
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
}
