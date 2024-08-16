package com.syed.loanapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.Date;

@Data @AllArgsConstructor
public class CorporateClientDTO {

    private Long clientId;
    private String companyName;
    private String contactPerson;
    private String contactEmail;
    private String contactPhone;
    private String address;
    private Date createdAt;
    private Date updatedAt;

}
