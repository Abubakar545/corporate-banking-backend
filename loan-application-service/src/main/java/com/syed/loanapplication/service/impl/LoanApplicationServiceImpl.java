package com.syed.loanapplication.service.impl;

import com.syed.loanapplication.dto.LoanApplicationDTO;
import com.syed.loanapplication.entity.LoanApplication;
import com.syed.loanapplication.mapper.LoanApplicationMapper;
import com.syed.loanapplication.repository.LoanApplicationRepository;
import com.syed.loanapplication.service.ILoanApplicationService;
import com.syed.loanapplication.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanApplicationServiceImpl implements ILoanApplicationService {

    @Autowired
    private LoanApplicationRepository loanApplicationRepository;

    @Autowired
    private LoanApplicationMapper loanApplicationMapper;

    @Override
    public LoanApplicationDTO createLoanApplication(LoanApplicationDTO loanApplicationDTO) {
        LoanApplication loanApplication = loanApplicationMapper.toEntity(loanApplicationDTO);
        LoanApplication savedLoanApplication = loanApplicationRepository.save(loanApplication);
        return loanApplicationMapper.toDTO(savedLoanApplication);
    }

    @Override
    public LoanApplicationDTO updateLoanApplication(Long id, LoanApplicationDTO loanApplicationDTO) {
        if (!loanApplicationRepository.existsById(id)) {
            throw new ResourceNotFoundException("LoanApplication", "id", id);
        }
        LoanApplication loanApplication = loanApplicationMapper.toEntity(loanApplicationDTO);
        loanApplication.setApplicationId(id);
        LoanApplication updatedLoanApplication = loanApplicationRepository.save(loanApplication);
        return loanApplicationMapper.toDTO(updatedLoanApplication);
    }

    @Override
    public void deleteLoanApplication(Long id) {
        if (!loanApplicationRepository.existsById(id)) {
            throw new ResourceNotFoundException("LoanApplication", "id", id);
        }
        loanApplicationRepository.deleteById(id);
    }

    @Override
    public LoanApplicationDTO getLoanApplicationById(Long id) {
        LoanApplication loanApplication = loanApplicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("LoanApplication", "id", id));
        return loanApplicationMapper.toDTO(loanApplication);
    }

    @Override
    public List<LoanApplicationDTO> getAllLoanApplications() {
        return loanApplicationRepository.findAll().stream()
                .map(loanApplicationMapper::toDTO)
                .collect(Collectors.toList());
    }
}
