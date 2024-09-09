package com.syed.loanapplication.controller;

import com.syed.loanapplication.dto.CorporateClientDTO;
import com.syed.loanapplication.service.ICorporateClientService;
import com.syed.loanapplication.constants.ResponseConstants;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/v1/corporate-clients")
@Validated
@Tag(name = "Corporate Client", description = "Operations related to corporate clients")
public class CorporateClientController {

    private final ICorporateClientService corporateClientService;

    public CorporateClientController(ICorporateClientService corporateClientService) {
        this.corporateClientService = corporateClientService;
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get Corporate Client by ID",
            description = "Retrieve a corporate client by its ID.",
            responses = {
                    @ApiResponse(description = "Corporate client found", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = CorporateClientDTO.class))),
                    @ApiResponse(description = "Corporate client not found", responseCode = "404",
                            content = @Content(schema = @Schema(type = "string", example = "Resource not found")))
            }
    )
    public ResponseEntity<?> getCorporateClient(
            @Parameter(description = "ID of the corporate client", required = true, example = "1")
            @PathVariable Long id
    ) {
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
    @Operation(
            summary = "Get All Corporate Clients",
            description = "Retrieve a list of all corporate clients.",
            responses = {
                    @ApiResponse(description = "List of corporate clients", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = CorporateClientDTO.class, type = "array")))
            }
    )
    public ResponseEntity<List<CorporateClientDTO>> getAllCorporateClients() {
        List<CorporateClientDTO> corporateClients = corporateClientService.getAllCorporateClients();
        return ResponseEntity.ok(corporateClients);
    }

    @PostMapping
    @Operation(
            summary = "Create a Corporate Client",
            description = "Create a new corporate client.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Corporate client details",
                    content = @Content(schema = @Schema(implementation = CorporateClientDTO.class))
            ),
            responses = {
                    @ApiResponse(description = "Corporate client created", responseCode = "201",
                            content = @Content(schema = @Schema(implementation = CorporateClientDTO.class))),
                    @ApiResponse(description = "Bad request", responseCode = "400",
                            content = @Content(schema = @Schema(type = "string", example = "Bad request")))
            }
    )
    public ResponseEntity<?> createCorporateClient(@RequestBody @Valid CorporateClientDTO corporateClientDTO) {
        CorporateClientDTO createdClient = corporateClientService.createCorporateClient(corporateClientDTO);
        return ResponseEntity
                .status(ResponseConstants.STATUS_CREATED)
                .body(createdClient);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Update a Corporate Client",
            description = "Update the details of an existing corporate client.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Updated corporate client details",
                    content = @Content(schema = @Schema(implementation = CorporateClientDTO.class))
            ),
            responses = {
                    @ApiResponse(description = "Corporate client updated", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = CorporateClientDTO.class))),
                    @ApiResponse(description = "Corporate client not found", responseCode = "404",
                            content = @Content(schema = @Schema(type = "string", example = "Resource not found"))),
                    @ApiResponse(description = "Bad request", responseCode = "400",
                            content = @Content(schema = @Schema(type = "string", example = "Bad request")))
            }
    )
    public ResponseEntity<?> updateCorporateClient(
            @PathVariable Long id,
            @Valid @RequestBody CorporateClientDTO corporateClientDTO
    ) {
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
    @Operation(
            summary = "Delete a Corporate Client",
            description = "Delete a corporate client by its ID.",
            responses = {
                    @ApiResponse(description = "Corporate client deleted", responseCode = "204"),
                    @ApiResponse(description = "Corporate client not found", responseCode = "404",
                            content = @Content(schema = @Schema(type = "string", example = "Resource not found")))
            }
    )
    public ResponseEntity<?> deleteCorporateClient(
            @Parameter(description = "ID of the corporate client to be deleted", required = true, example = "1")
            @PathVariable Long id
    ) {
        corporateClientService.deleteCorporateClient(id);
        return ResponseEntity
                .status(ResponseConstants.STATUS_NO_CONTENT)
                .build();
    }
}
