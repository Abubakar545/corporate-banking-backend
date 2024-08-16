package com.syed.loanapplication.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Set;


@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "loan_officers")
public class LoanOfficer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long officerId;

    @Column(name = "officer_name", nullable = false)
    private String officerName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "created_at", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @OneToMany(mappedBy = "loanOfficer")
    private Set<LoanReview> loanReviews;

}
