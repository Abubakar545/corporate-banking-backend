package com.syed.loanapplication.mapper;

import com.syed.loanapplication.dto.LoanReviewDTO;
import com.syed.loanapplication.entity.LoanReview;
import com.syed.loanapplication.entity.LoanApplication;
import com.syed.loanapplication.entity.LoanOfficer;
import org.springframework.stereotype.Component;

@Component
public class LoanReviewMapper {

    // Convert Entity to DTO
    public LoanReviewDTO toDTO(LoanReview loanReview) {
        if (loanReview == null) {
            return null;
        }

        LoanReviewDTO dto = new LoanReviewDTO(
                loanReview.getReviewId(),
                loanReview.getLoanApplication() != null ? loanReview.getLoanApplication().getApplicationId() : null,
                loanReview.getLoanOfficer() != null ? loanReview.getLoanOfficer().getOfficerId() : null,
                loanReview.getReviewComments(),
                loanReview.getApprovalStatus(),
                loanReview.getReviewDate(),
                loanReview.getCreatedAt(),
                loanReview.getUpdatedAt()
        );

        return dto;
    }

    // Convert DTO to Entity
    public LoanReview toEntity(LoanReviewDTO loanReviewDTO) {
        if (loanReviewDTO == null) {
            return null;
        }

        LoanReview loanReview = new LoanReview();
        loanReview.setReviewId(loanReviewDTO.getReviewId());

        // Assume methods to fetch LoanApplication and LoanOfficer by their IDs
        LoanApplication application = findLoanApplicationById(loanReviewDTO.getApplicationId());
        LoanOfficer officer = findLoanOfficerById(loanReviewDTO.getOfficerId());

        loanReview.setLoanApplication(application);
        loanReview.setLoanOfficer(officer);
        loanReview.setReviewComments(loanReviewDTO.getReviewComments());
        loanReview.setApprovalStatus(loanReviewDTO.getApprovalStatus());
        loanReview.setReviewDate(loanReviewDTO.getReviewDate());
        loanReview.setCreatedAt(loanReviewDTO.getCreatedAt());
        loanReview.setUpdatedAt(loanReviewDTO.getUpdatedAt());

        return loanReview;
    }

    // Mock method to simulate fetching LoanApplication by ID
    private LoanApplication findLoanApplicationById(Long applicationId) {
        // Implementation to fetch LoanApplication by ID from the database or other source
        return new LoanApplication(); // Example placeholder
    }

    // Mock method to simulate fetching LoanOfficer by ID
    private LoanOfficer findLoanOfficerById(Long officerId) {
        // Implementation to fetch LoanOfficer by ID from the database or other source
        return new LoanOfficer(); // Example placeholder
    }
}
