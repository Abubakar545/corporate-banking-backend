package com.syed.loanapplication.service;

import com.syed.loanapplication.dto.LoanApplicationDTO;
import java.util.List;

public interface ILoanApplicationService {

    LoanApplicationDTO createLoanApplication(LoanApplicationDTO loanApplicationDTO);

    LoanApplicationDTO updateLoanApplication(Long id, LoanApplicationDTO loanApplicationDTO);

    void deleteLoanApplication(Long id);

    LoanApplicationDTO getLoanApplicationById(Long id);

    List<LoanApplicationDTO> getAllLoanApplications();
}
