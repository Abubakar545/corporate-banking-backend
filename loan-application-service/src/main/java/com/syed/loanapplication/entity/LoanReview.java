package com.syed.loanapplication.entity;

import com.syed.loanapplication.enums.ApplicationStatus; // Update to use ApplicationStatus
import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "loan_reviews")
public class LoanReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    @ManyToOne
    @JoinColumn(name = "application_id", nullable = false)
    private LoanApplication loanApplication;

    @ManyToOne
    @JoinColumn(name = "officer_id", nullable = false)
    private LoanOfficer loanOfficer;

    @Column(name = "review_comments")
    private String reviewComments;

    @Enumerated(EnumType.STRING)
    @Column(name = "approval_status", nullable = false)
    private ApplicationStatus approvalStatus; // Update to use ApplicationStatus

    @Column(name = "review_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date reviewDate;

    @Column(name = "created_at", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date();
    }
}
