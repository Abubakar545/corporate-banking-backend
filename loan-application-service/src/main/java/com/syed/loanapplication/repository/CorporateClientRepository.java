package com.syed.loanapplication.repository;

import com.syed.loanapplication.entity.CorporateClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CorporateClientRepository extends JpaRepository<CorporateClient, Long> {
    // Custom query methods can be defined here if needed
}
