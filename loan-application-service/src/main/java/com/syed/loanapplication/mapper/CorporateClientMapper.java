package com.syed.loanapplication.mapper;

import com.syed.loanapplication.dto.CorporateClientDTO;
import com.syed.loanapplication.entity.CorporateClient;

public class CorporateClientMapper {

    // Convert Entity to DTO
    public CorporateClientDTO toDTO(CorporateClient corporateClient) {
        if (corporateClient == null) {
            return null;
        }

        CorporateClientDTO dto = new CorporateClientDTO(
                corporateClient.getClientId(),
                corporateClient.getCompanyName(),
                corporateClient.getContactPerson(),
                corporateClient.getContactEmail(),
                corporateClient.getContactPhone(),
                corporateClient.getAddress(),
                corporateClient.getCreatedAt(),
                corporateClient.getUpdatedAt()
        );

        return dto;
    }

    // Convert DTO to Entity
    public CorporateClient toEntity(CorporateClientDTO corporateClientDTO) {
        if (corporateClientDTO == null) {
            return null;
        }

        CorporateClient corporateClient = new CorporateClient();
        corporateClient.setClientId(corporateClientDTO.getClientId());
        corporateClient.setCompanyName(corporateClientDTO.getCompanyName());
        corporateClient.setContactPerson(corporateClientDTO.getContactPerson());
        corporateClient.setContactEmail(corporateClientDTO.getContactEmail());
        corporateClient.setContactPhone(corporateClientDTO.getContactPhone());
        corporateClient.setAddress(corporateClientDTO.getAddress());
        corporateClient.setCreatedAt(corporateClientDTO.getCreatedAt());
        corporateClient.setUpdatedAt(corporateClientDTO.getUpdatedAt());

        return corporateClient;
    }
}
