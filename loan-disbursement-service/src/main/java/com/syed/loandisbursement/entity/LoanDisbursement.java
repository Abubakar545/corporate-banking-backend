package com.syed.loandisbursement.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "loan_disbursement")
public class LoanDisbursement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "loan_application_id")
    private Long loanApplicationId;

    @Column(name = "application_status")
    private String applicationStatus;

    @Column(name = "approval_comments")
    private String approvalComments;

}
