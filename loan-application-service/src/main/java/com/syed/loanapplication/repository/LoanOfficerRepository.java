package com.syed.loanapplication.repository;

import com.syed.loanapplication.entity.LoanOfficer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanOfficerRepository extends JpaRepository<LoanOfficer, Long> {
    // Custom query methods can be defined here if needed
}
