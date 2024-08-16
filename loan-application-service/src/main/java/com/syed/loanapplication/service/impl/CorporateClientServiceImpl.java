package com.syed.loanapplication.service.impl;

import com.syed.loanapplication.dto.CorporateClientDTO;
import com.syed.loanapplication.entity.CorporateClient;
import com.syed.loanapplication.mapper.CorporateClientMapper;
import com.syed.loanapplication.mapper.LoanApplicationMapper;
import com.syed.loanapplication.repository.CorporateClientRepository;
import com.syed.loanapplication.service.ICorporateClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CorporateClientServiceImpl implements ICorporateClientService {

    private final CorporateClientRepository repository;
    private final CorporateClientMapper mapper;

    @Autowired
    public CorporateClientServiceImpl(CorporateClientRepository repository, LoanApplicationMapper loanApplicationMapper) {
        this.repository = repository;
        this.mapper = new CorporateClientMapper(loanApplicationMapper);
    }

    @Override
    public Optional<CorporateClientDTO> getCorporateClient(Long id) {
        return repository.findById(id)
                .map(mapper::toDTO);
    }

    @Override
    public CorporateClientDTO createCorporateClient(CorporateClientDTO corporateClientDTO) {
        CorporateClient corporateClient = mapper.toEntity(corporateClientDTO);
        CorporateClient savedCorporateClient = repository.save(corporateClient);
        return mapper.toDTO(savedCorporateClient);
    }

    @Override
    public CorporateClientDTO updateCorporateClient(Long id, CorporateClientDTO corporateClientDTO) {
        Optional<CorporateClient> existingClient = repository.findById(id);
        if (existingClient.isPresent()) {
            CorporateClient updatedClient = mapper.toEntity(corporateClientDTO);
            updatedClient.setClientId(id); // Ensure the ID is preserved
            CorporateClient savedClient = repository.save(updatedClient);
            return mapper.toDTO(savedClient);
        }
        return null; // Handle not found case if needed
    }

    @Override
    public void deleteCorporateClient(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<CorporateClientDTO> getAllCorporateClients() {
        return repository.findAll().stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }
}
