package com.syed.loanapplication.controller;

import com.syed.loanapplication.constants.ResponseConstants;
import com.syed.loanapplication.dto.LoanOfficerDTO;
import com.syed.loanapplication.exception.ResourceNotFoundException;
import com.syed.loanapplication.service.ILoanOfficerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/loan-officers")
@Tag(name = "Loan Officers", description = "Operations related to loan officers.")
public class LoanOfficerController {

    private final ILoanOfficerService loanOfficerService;

    public LoanOfficerController(ILoanOfficerService loanOfficerService) {
        this.loanOfficerService = loanOfficerService;
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get Loan Officer by ID",
            description = "Retrieve a loan officer by their ID.",
            tags = {"Loan Officers"},
            responses = {
                    @ApiResponse(description = "Loan officer found", responseCode = "200",
                            content = @Content(
                                    schema = @Schema(implementation = LoanOfficerDTO.class),
                                    examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                                            name = "Loan Officer Example",
                                            summary = "Example of a loan officer retrieval response",
                                            value = "{ \"id\": 1, \"name\": \"John Doe\", \"email\": \"john.doe@example.com\", \"phone\": \"1234567890\" }"
                                    )
                            )
                    ),
                    @ApiResponse(description = "Loan officer not found", responseCode = "404",
                            content = @Content(
                                    schema = @Schema(type = "string", example = "Resource not found")
                            )
                    )
            }
    )
    public ResponseEntity<?> getLoanOfficer(
            @Parameter(description = "ID of the loan officer to be retrieved", example = "1") @PathVariable Long id) {
        Optional<LoanOfficerDTO> loanOfficerDTO = loanOfficerService.getLoanOfficer(id);
        if (loanOfficerDTO.isPresent()) {
            return ResponseEntity
                    .status(ResponseConstants.STATUS_OK)
                    .body(loanOfficerDTO.get());
        } else {
            return ResponseEntity
                    .status(ResponseConstants.STATUS_NOT_FOUND)
                    .body(ResponseConstants.MESSAGE_NOT_FOUND);
        }
    }

    @GetMapping
    @Operation(
            summary = "Get All Loan Officers",
            description = "Retrieve a list of all loan officers.",
            tags = {"Loan Officers"},
            responses = {
                    @ApiResponse(description = "List of loan officers", responseCode = "200",
                            content = @Content(
                                    schema = @Schema(implementation = LoanOfficerDTO.class, type = "array"),
                                    examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                                            name = "Loan Officers List Example",
                                            summary = "Example of a list of loan officers",
                                            value = "[{ \"id\": 1, \"name\": \"John Doe\", \"email\": \"john.doe@example.com\", \"phone\": \"1234567890\" }, { \"id\": 2, \"name\": \"Jane Smith\", \"email\": \"jane.smith@example.com\", \"phone\": \"0987654321\" }]"
                                    )
                            )
                    )
            }
    )
    public ResponseEntity<List<LoanOfficerDTO>> getAllLoanOfficers() {
        List<LoanOfficerDTO> loanOfficers = loanOfficerService.getAllLoanOfficers();
        return ResponseEntity
                .status(ResponseConstants.STATUS_OK)
                .body(loanOfficers);
    }

    @PostMapping
    @Operation(
            summary = "Create a Loan Officer",
            description = "Create a new loan officer.",
            tags = {"Loan Officers"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Details of the loan officer to create",
                    content = @Content(
                            schema = @Schema(implementation = LoanOfficerDTO.class),
                            examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                                    name = "Loan Officer Creation Example",
                                    summary = "Example of a loan officer creation request",
                                    value = "{ \"name\": \"John Doe\", \"email\": \"john.doe@example.com\", \"phone\": \"1234567890\" }"
                            )
                    )
            ),
            responses = {
                    @ApiResponse(description = "Loan officer created successfully", responseCode = "201",
                            content = @Content(
                                    schema = @Schema(implementation = LoanOfficerDTO.class),
                                    examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                                            name = "Loan Officer Created Example",
                                            summary = "Example of a successful loan officer creation response",
                                            value = "{ \"id\": 1, \"name\": \"John Doe\", \"email\": \"john.doe@example.com\", \"phone\": \"1234567890\" }"
                                    )
                            )
                    ),
                    @ApiResponse(description = "Internal server error", responseCode = "500",
                            content = @Content(
                                    schema = @Schema(type = "string", example = "Internal server error")
                            )
                    )
            }
    )
    public ResponseEntity<?> createLoanOfficer(@Valid @RequestBody LoanOfficerDTO loanOfficerDTO) {
        try {
            LoanOfficerDTO createdLoanOfficer = loanOfficerService.createLoanOfficer(loanOfficerDTO);
            return ResponseEntity
                    .status(ResponseConstants.STATUS_CREATED)
                    .body(createdLoanOfficer);
        } catch (Exception e) {
            // Consider logging the exception
            return ResponseEntity
                    .status(ResponseConstants.STATUS_INTERNAL_SERVER_ERROR)
                    .body(ResponseConstants.MESSAGE_INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Update a Loan Officer",
            description = "Update an existing loan officer by ID.",
            tags = {"Loan Officers"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Updated details of the loan officer",
                    content = @Content(
                            schema = @Schema(implementation = LoanOfficerDTO.class),
                            examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                                    name = "Loan Officer Update Example",
                                    summary = "Example of a loan officer update request",
                                    value = "{ \"name\": \"John Doe\", \"email\": \"john.doe@example.com\", \"phone\": \"1234567890\" }"
                            )
                    )
            ),
            responses = {
                    @ApiResponse(description = "Loan officer updated successfully", responseCode = "200",
                            content = @Content(
                                    schema = @Schema(implementation = LoanOfficerDTO.class),
                                    examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                                            name = "Loan Officer Updated Example",
                                            summary = "Example of a successful loan officer update response",
                                            value = "{ \"id\": 1, \"name\": \"John Doe\", \"email\": \"john.doe@example.com\", \"phone\": \"1234567890\" }"
                                    )
                            )
                    ),
                    @ApiResponse(description = "Loan officer not found", responseCode = "404",
                            content = @Content(
                                    schema = @Schema(type = "string", example = "Resource not found")
                            )
                    ),
                    @ApiResponse(description = "Internal server error", responseCode = "500",
                            content = @Content(
                                    schema = @Schema(type = "string", example = "Internal server error")
                            )
                    )
            }
    )
    public ResponseEntity<?> updateLoanOfficer(
            @Parameter(description = "ID of the loan officer to be updated", example = "1") @PathVariable Long id,
            @Valid @RequestBody LoanOfficerDTO loanOfficerDTO) {
        try {
            LoanOfficerDTO updatedLoanOfficer = loanOfficerService.updateLoanOfficer(id, loanOfficerDTO);
            return ResponseEntity
                    .status(ResponseConstants.STATUS_OK)
                    .body(updatedLoanOfficer);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity
                    .status(ResponseConstants.STATUS_NOT_FOUND)
                    .body(ResponseConstants.MESSAGE_NOT_FOUND);
        } catch (Exception e) {
            // Consider logging the exception
            return ResponseEntity
                    .status(ResponseConstants.STATUS_INTERNAL_SERVER_ERROR)
                    .body(ResponseConstants.MESSAGE_INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete a Loan Officer",
            description = "Delete a loan officer by ID.",
            tags = {"Loan Officers"},
            responses = {
                    @ApiResponse(description = "Loan officer deleted successfully", responseCode = "204"),
                    @ApiResponse(description = "Loan officer not found", responseCode = "404",
                            content = @Content(
                                    schema = @Schema(type = "string", example = "Resource not found")
                            )
                    ),
                    @ApiResponse(description = "Internal server error", responseCode = "500",
                            content = @Content(
                                    schema = @Schema(type = "string", example = "Internal server error")
                            )
                    )
            }
    )
    public ResponseEntity<?> deleteLoanOfficer(
            @Parameter(description = "ID of the loan officer to be deleted", example = "1") @PathVariable Long id) {
        try {
            loanOfficerService.deleteLoanOfficer(id);
            return ResponseEntity
                    .status(ResponseConstants.STATUS_NO_CONTENT)
                    .build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity
                    .status(ResponseConstants.STATUS_NOT_FOUND)
                    .body(ResponseConstants.MESSAGE_NOT_FOUND);
        } catch (Exception e) {
            // Consider logging the exception
            return ResponseEntity
                    .status(ResponseConstants.STATUS_INTERNAL_SERVER_ERROR)
                    .body(ResponseConstants.MESSAGE_INTERNAL_SERVER_ERROR);
        }
    }
}
