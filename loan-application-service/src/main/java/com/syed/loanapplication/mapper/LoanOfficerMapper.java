package com.syed.loanapplication.mapper;

import com.syed.loanapplication.dto.LoanOfficerDTO;
import com.syed.loanapplication.entity.LoanOfficer;

public class LoanOfficerMapper {

    // Convert Entity to DTO
    public LoanOfficerDTO toDTO(LoanOfficer loanOfficer) {
        if (loanOfficer == null) {
            return null;
        }

        LoanOfficerDTO dto = new LoanOfficerDTO(
                loanOfficer.getOfficerId(),
                loanOfficer.getOfficerName(),
                loanOfficer.getEmail(),
                loanOfficer.getCreatedAt(),
                loanOfficer.getUpdatedAt()
        );

        return dto;
    }

    // Convert DTO to Entity
    public LoanOfficer toEntity(LoanOfficerDTO loanOfficerDTO) {
        if (loanOfficerDTO == null) {
            return null;
        }

        LoanOfficer loanOfficer = new LoanOfficer();
        loanOfficer.setOfficerId(loanOfficerDTO.getOfficerId());
        loanOfficer.setOfficerName(loanOfficerDTO.getOfficerName());
        loanOfficer.setEmail(loanOfficerDTO.getEmail());
        loanOfficer.setCreatedAt(loanOfficerDTO.getCreatedAt());
        loanOfficer.setUpdatedAt(loanOfficerDTO.getUpdatedAt());

        return loanOfficer;
    }
}
