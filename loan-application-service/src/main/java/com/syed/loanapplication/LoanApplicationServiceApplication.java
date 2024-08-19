package com.syed.loanapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication(scanBasePackages = "com.syed.loanapplication")
public class LoanApplicationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoanApplicationServiceApplication.class, args);
	}

}
