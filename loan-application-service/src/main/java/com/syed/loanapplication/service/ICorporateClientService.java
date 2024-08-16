package com.syed.loanapplication.service;

import com.syed.loanapplication.dto.CorporateClientDTO;

import java.util.List;
import java.util.Optional;

public interface ICorporateClientService {

    Optional<CorporateClientDTO> getCorporateClient(Long id);

    CorporateClientDTO createCorporateClient(CorporateClientDTO corporateClientDTO);

    CorporateClientDTO updateCorporateClient(Long id, CorporateClientDTO corporateClientDTO);

    void deleteCorporateClient(Long id);

    List<CorporateClientDTO> getAllCorporateClients();
}
