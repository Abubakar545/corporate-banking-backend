package com.syed.loanapplication.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoanApplicationController {

    @GetMapping("sayhello")
    public String sayHello(){
        return "Hii Hello EveryOne";
    }
}
