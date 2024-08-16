package com.syed.loanapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.Date;

@Data @AllArgsConstructor
public class LoanOfficerDTO {

    private Long officerId;
    private String officerName;
    private String email;
    private Date createdAt;
    private Date updatedAt;

}
