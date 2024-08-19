package com.syed.loandisbursement.client;

import com.syed.loandisbursement.dto.LoanApplicationDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "loan-application-service", url = "http://localhost:8071")
public interface LoanApplicationClient {
    @GetMapping("/api/v1/loan-applications/{id}")
    LoanApplicationDTO getApplicationById(@PathVariable("id") Long id);


}
