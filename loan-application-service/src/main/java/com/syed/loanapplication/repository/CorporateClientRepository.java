package com.syed.loanapplication.repository;

import com.syed.loanapplication.entity.CorporateClient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CorporateClientRepository extends JpaRepository<CorporateClient, Long> {
}
