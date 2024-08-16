package com.syed.loanapplication.mapper;

import com.syed.loanapplication.dto.LoanOfficerDTO;
import com.syed.loanapplication.entity.LoanOfficer;

public class LoanOfficerMapper {

    public LoanOfficerDTO toDTO(LoanOfficer loanOfficer) {
        if (loanOfficer == null) {
            return null;
        }
        return new LoanOfficerDTO(
                loanOfficer.getOfficerId(),
                loanOfficer.getOfficerName(),
                loanOfficer.getOfficerEmail(),
                loanOfficer.getOfficerPhone() // New Field
        );
    }

    public LoanOfficer toEntity(LoanOfficerDTO loanOfficerDTO) {
        if (loanOfficerDTO == null) {
            return null;
        }
        LoanOfficer loanOfficer = new LoanOfficer();
        loanOfficer.setOfficerId(loanOfficerDTO.getOfficerId());
        loanOfficer.setOfficerName(loanOfficerDTO.getOfficerName());
        loanOfficer.setOfficerEmail(loanOfficerDTO.getOfficerEmail());
        loanOfficer.setOfficerPhone(loanOfficerDTO.getOfficerPhone()); // New Field
        return loanOfficer;
    }
}
