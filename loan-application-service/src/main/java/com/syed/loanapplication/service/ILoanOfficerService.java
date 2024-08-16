package com.syed.loanapplication.service;

import com.syed.loanapplication.dto.LoanOfficerDTO;

import java.util.List;
import java.util.Optional;

public interface ILoanOfficerService {

    Optional<LoanOfficerDTO> getLoanOfficer(Long id);

    LoanOfficerDTO createLoanOfficer(LoanOfficerDTO loanOfficerDTO);

    LoanOfficerDTO updateLoanOfficer(Long id, LoanOfficerDTO loanOfficerDTO);

    void deleteLoanOfficer(Long id);

    List<LoanOfficerDTO> getAllLoanOfficers();
}
