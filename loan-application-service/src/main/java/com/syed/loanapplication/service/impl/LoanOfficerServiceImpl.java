package com.syed.loanapplication.service.impl;

import com.syed.loanapplication.dto.LoanOfficerDTO;
import com.syed.loanapplication.entity.LoanOfficer;
import com.syed.loanapplication.mapper.LoanOfficerMapper;
import com.syed.loanapplication.repository.LoanOfficerRepository;
import com.syed.loanapplication.service.ILoanOfficerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LoanOfficerServiceImpl implements ILoanOfficerService {

    private final LoanOfficerRepository repository;
    private final LoanOfficerMapper mapper = new LoanOfficerMapper();

    @Autowired
    public LoanOfficerServiceImpl(LoanOfficerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<LoanOfficerDTO> getLoanOfficer(Long id) {
        return repository.findById(id)
                .map(mapper::toDTO);
    }

    @Override
    public LoanOfficerDTO createLoanOfficer(LoanOfficerDTO loanOfficerDTO) {
        LoanOfficer loanOfficer = mapper.toEntity(loanOfficerDTO);
        LoanOfficer savedLoanOfficer = repository.save(loanOfficer);
        return mapper.toDTO(savedLoanOfficer);
    }

    @Override
    public LoanOfficerDTO updateLoanOfficer(Long id, LoanOfficerDTO loanOfficerDTO) {
        Optional<LoanOfficer> existingOfficer = repository.findById(id);
        if (existingOfficer.isPresent()) {
            LoanOfficer updatedOfficer = mapper.toEntity(loanOfficerDTO);
            updatedOfficer.setOfficerId(id); // Ensure the ID is preserved
            LoanOfficer savedOfficer = repository.save(updatedOfficer);
            return mapper.toDTO(savedOfficer);
        }
        return null; // Handle not found case if needed
    }

    @Override
    public void deleteLoanOfficer(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<LoanOfficerDTO> getAllLoanOfficers() {
        return repository.findAll().stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }
}
