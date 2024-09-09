package com.syed.loandisbursement.service;

import com.syed.loandisbursement.dto.LoanApplicationDTO;
import com.syed.loandisbursement.dto.LoanDisbursementDTO;
import com.syed.loandisbursement.exception.ResourceNotFoundException;

import java.util.List;

public interface LoanDisbursementService {

    /**
     * Submit a loan disbursement after checking if the loan application is approved.
     *
     * @param loanDisbursementDTO the details of the loan disbursement to be submitted
     * @return the submitted LoanDisbursementDTO
     * @throws ResourceNotFoundException if the loan application is not found or not approved
     */
    LoanDisbursementDTO submitDisbursement(LoanDisbursementDTO loanDisbursementDTO) throws ResourceNotFoundException;


    /**
     * Get all loan disbursements.
     *
     * @return a list of all LoanDisbursementDTOs
     */
    List<LoanDisbursementDTO> getAllDisbursements();

    /**
     * Approve a loan disbursement.
     *
     * @param id the ID of the loan disbursement to be approved
     * @return the approved LoanDisbursementDTO
     * @throws ResourceNotFoundException if the loan disbursement or loan application is not found or not approved
     */
    LoanDisbursementDTO approveDisbursement(Long id) throws ResourceNotFoundException;

    /**
     * Reject a loan disbursement.
     *
     * @param id the ID of the loan disbursement to be rejected
     * @return the rejected LoanDisbursementDTO
     * @throws ResourceNotFoundException if the loan disbursement is not found
     */
    LoanDisbursementDTO rejectDisbursement(Long id) throws ResourceNotFoundException;
}
