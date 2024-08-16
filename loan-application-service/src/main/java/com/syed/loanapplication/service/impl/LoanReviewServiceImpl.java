package com.syed.loanapplication.service.impl;

import com.syed.loanapplication.dto.LoanReviewDTO;
import com.syed.loanapplication.entity.LoanApplication;
import com.syed.loanapplication.entity.LoanOfficer;
import com.syed.loanapplication.entity.LoanReview;
import com.syed.loanapplication.exception.ResourceNotFoundException;
import com.syed.loanapplication.mapper.LoanReviewMapper;
import com.syed.loanapplication.repository.LoanApplicationRepository;
import com.syed.loanapplication.repository.LoanOfficerRepository;
import com.syed.loanapplication.repository.LoanReviewRepository;
import com.syed.loanapplication.service.ILoanReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LoanReviewServiceImpl implements ILoanReviewService {

    @Autowired
    private LoanReviewRepository loanReviewRepository;

    @Autowired
    private LoanApplicationRepository loanApplicationRepository;

    @Autowired
    private LoanOfficerRepository loanOfficerRepository;

    @Autowired
    private LoanReviewMapper loanReviewMapper;

    @Override
    public Optional<LoanReviewDTO> getLoanReview(Long id) {
        return loanReviewRepository.findById(id)
                .map(loanReviewMapper::toDTO);
    }

    @Override
    public LoanReviewDTO createLoanReview(LoanReviewDTO loanReviewDTO) {
        // Fetch and verify the existence of related entities
        LoanApplication loanApplication = loanApplicationRepository.findById(loanReviewDTO.getApplicationId())
                .orElseThrow(() -> new ResourceNotFoundException("LoanApplication", "id", loanReviewDTO.getApplicationId()));
        LoanOfficer loanOfficer = loanOfficerRepository.findById(loanReviewDTO.getOfficerId())
                .orElseThrow(() -> new ResourceNotFoundException("LoanOfficer", "id", loanReviewDTO.getOfficerId()));

        // Create a new LoanReview entity and map from DTO
        LoanReview loanReview = loanReviewMapper.toEntity(loanReviewDTO);
        loanReview.setLoanApplication(loanApplication);
        loanReview.setLoanOfficer(loanOfficer);

        // Save the LoanReview entity
        LoanReview savedLoanReview = loanReviewRepository.save(loanReview);
        return loanReviewMapper.toDTO(savedLoanReview);
    }

    @Override
    public LoanReviewDTO updateLoanReview(Long id, LoanReviewDTO loanReviewDTO) {
        if (!loanReviewRepository.existsById(id)) {
            throw new ResourceNotFoundException("LoanReview", "id", id);
        }

        // Fetch and verify the existence of related entities
        LoanApplication loanApplication = loanApplicationRepository.findById(loanReviewDTO.getApplicationId())
                .orElseThrow(() -> new ResourceNotFoundException("LoanApplication", "id", loanReviewDTO.getApplicationId()));
        LoanOfficer loanOfficer = loanOfficerRepository.findById(loanReviewDTO.getOfficerId())
                .orElseThrow(() -> new ResourceNotFoundException("LoanOfficer", "id", loanReviewDTO.getOfficerId()));

        // Update the LoanReview entity
        LoanReview loanReview = loanReviewMapper.toEntity(loanReviewDTO);
        loanReview.setReviewId(id);
        loanReview.setLoanApplication(loanApplication);
        loanReview.setLoanOfficer(loanOfficer);

        LoanReview updatedLoanReview = loanReviewRepository.save(loanReview);
        return loanReviewMapper.toDTO(updatedLoanReview);
    }

    @Override
    public void deleteLoanReview(Long id) {
        if (!loanReviewRepository.existsById(id)) {
            throw new ResourceNotFoundException("LoanReview", "id", id);
        }
        loanReviewRepository.deleteById(id);
    }

    @Override
    public List<LoanReviewDTO> getAllLoanReviews() {
        return loanReviewRepository.findAll().stream()
                .map(loanReviewMapper::toDTO)
                .collect(Collectors.toList());
    }
}
