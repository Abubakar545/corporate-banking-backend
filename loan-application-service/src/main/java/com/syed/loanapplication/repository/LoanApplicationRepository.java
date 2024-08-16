package com.syed.loanapplication.repository;

import com.syed.loanapplication.entity.LoanApplication;
import com.syed.loanapplication.enums.ApplicationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanApplicationRepository extends JpaRepository<LoanApplication, Long> {
    
    // Find applications by status
    List<LoanApplication> findByApplicationStatus(ApplicationStatus status);

    // Custom query methods can be defined here if needed
}
