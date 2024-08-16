package com.syed.loanapplication.config;

import com.syed.loanapplication.service.ILoanApplicationService;
import com.syed.loanapplication.service.impl.LoanApplicationServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public ILoanApplicationService loanApplicationService() {
        return new LoanApplicationServiceImpl();
    }
}
