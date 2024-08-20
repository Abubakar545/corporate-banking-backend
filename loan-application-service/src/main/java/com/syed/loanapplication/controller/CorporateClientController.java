package com.syed.loanapplication.controller;

import com.syed.loanapplication.dto.CorporateClientDTO;
import com.syed.loanapplication.service.ICorporateClientService;
import com.syed.loanapplication.constants.ResponseConstants;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/corporate-clients")
@Validated
public class CorporateClientController {

    private final ICorporateClientService corporateClientService;

    public CorporateClientController(ICorporateClientService corporateClientService) {
        this.corporateClientService = corporateClientService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCorporateClient(@PathVariable Long id) {
        Optional<CorporateClientDTO> corporateClientDTO = corporateClientService.getCorporateClient(id);
        if (corporateClientDTO.isPresent()) {
            return ResponseEntity
                    .status(ResponseConstants.STATUS_OK)
                    .body(corporateClientDTO.get());
        } else {
            return ResponseEntity
                    .status(ResponseConstants.STATUS_NOT_FOUND)
                    .body(ResponseConstants.MESSAGE_NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<CorporateClientDTO>> getAllCorporateClients() {
        List<CorporateClientDTO> corporateClients = corporateClientService.getAllCorporateClients();
        return ResponseEntity.ok(corporateClients);
    }

    @PostMapping
    public ResponseEntity<?> createCorporateClient(@RequestBody @Valid CorporateClientDTO corporateClientDTO) {
        CorporateClientDTO createdClient = corporateClientService.createCorporateClient(corporateClientDTO);
        return ResponseEntity
                .status(ResponseConstants.STATUS_CREATED)
                .body(createdClient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCorporateClient(@PathVariable Long id,@Valid @RequestBody CorporateClientDTO corporateClientDTO) {
        CorporateClientDTO updatedClient = corporateClientService.updateCorporateClient(id, corporateClientDTO);
        if (updatedClient != null) {
            return ResponseEntity.ok(updatedClient);
        } else {
            return ResponseEntity
                    .status(ResponseConstants.STATUS_NOT_FOUND)
                    .body(ResponseConstants.MESSAGE_NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCorporateClient(@PathVariable Long id) {
        corporateClientService.deleteCorporateClient(id);
        return ResponseEntity
                .status(ResponseConstants.STATUS_NO_CONTENT)
                .build();
    }
}
