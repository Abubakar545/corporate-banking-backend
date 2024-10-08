package com.syed.loandisbursement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class LoanDisbursementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoanDisbursementServiceApplication.class, args);
	}

}
