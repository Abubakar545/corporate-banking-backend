package com.syed.loanapplication.repository;


import com.syed.loanapplication.entity.LoanReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface LoanReviewRepository extends JpaRepository<LoanReview, Long> {

}
