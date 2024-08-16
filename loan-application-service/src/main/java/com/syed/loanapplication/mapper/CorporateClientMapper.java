package com.syed.loanapplication.mapper;

import com.syed.loanapplication.dto.CorporateClientDTO;
import com.syed.loanapplication.dto.LoanApplicationDTO;
import com.syed.loanapplication.entity.CorporateClient;
import com.syed.loanapplication.entity.LoanApplication;
import java.util.Set;
import java.util.HashSet;
import java.util.stream.Collectors;

public class CorporateClientMapper {

    private final LoanApplicationMapper loanApplicationMapper;

    // Constructor with LoanApplicationMapper dependency
    public CorporateClientMapper(LoanApplicationMapper loanApplicationMapper) {
        this.loanApplicationMapper = loanApplicationMapper;
    }

    // Convert CorporateClient to CorporateClientDTO
    public CorporateClientDTO toDTO(CorporateClient corporateClient) {
        if (corporateClient == null) {
            return null;
        }

        Set<LoanApplicationDTO> loanApplicationDTOs = corporateClient.getLoanApplications() == null ?
                new HashSet<>() :
                corporateClient.getLoanApplications().stream()
                        .map(loanApplicationMapper::toDTO)
                        .collect(Collectors.toSet());

        return new CorporateClientDTO(
                corporateClient.getClientId(),
                corporateClient.getCompanyName(),
                corporateClient.getContactPerson(),
                corporateClient.getContactEmail(),
                corporateClient.getContactPhone(),
                corporateClient.getAddress(),
                loanApplicationDTOs
        );
    }

    // Convert CorporateClientDTO to CorporateClient
    public CorporateClient toEntity(CorporateClientDTO corporateClientDTO) {
        if (corporateClientDTO == null) {
            return null;
        }

        Set<LoanApplication> loanApplications = corporateClientDTO.getLoanApplicationDTOs() == null ?
                new HashSet<>() :
                corporateClientDTO.getLoanApplicationDTOs().stream()
                        .map(loanApplicationMapper::toEntity)
                        .collect(Collectors.toSet());

        CorporateClient corporateClient = new CorporateClient();
        corporateClient.setClientId(corporateClientDTO.getClientId());
        corporateClient.setCompanyName(corporateClientDTO.getCompanyName());
        corporateClient.setContactPerson(corporateClientDTO.getContactPerson());
        corporateClient.setContactEmail(corporateClientDTO.getContactEmail());
        corporateClient.setContactPhone(corporateClientDTO.getContactPhone());
        corporateClient.setAddress(corporateClientDTO.getAddress());
        corporateClient.setLoanApplications(loanApplications);

        return corporateClient;
    }
}
