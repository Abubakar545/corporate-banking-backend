package com.syed.loanapplication.controller;

import com.syed.loanapplication.dto.LoanApplicationDTO;
import com.syed.loanapplication.exception.ResourceNotFoundException;
import com.syed.loanapplication.service.ILoanApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/loan-applications")
@Tag(name = "Loan Application", description = "Operations related to loan application")
public class LoanApplicationController {

    @Autowired
    private ILoanApplicationService loanApplicationService;

    @PostMapping
    @Operation(
            summary = "Create a Loan Application",
            description = "Create a new loan application.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Details of the loan application to create",
                    content = @Content(
                            schema = @Schema(implementation = LoanApplicationDTO.class),
                            examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                                    name = "Loan Application Example",
                                    summary = "Example of a loan application creation request",
                                    value = "{ \"clientId\": 1, \"loanType\": \"PERSONAL\", \"loanAmount\": 10000, \"applicationStatus\": \"PENDING\", \"submissionDate\": \"2024-08-20\" }"
                            )
                    )
            ),
            responses = {
                    @ApiResponse(description = "Loan application created successfully", responseCode = "201",
                            content = @Content(
                                    schema = @Schema(implementation = LoanApplicationDTO.class),
                                    examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                                            name = "Loan Application Created Example",
                                            summary = "Example of a successful loan application creation response",
                                            value = "{ \"applicationId\": 1, \"clientId\": 1, \"loanType\": \"PERSONAL\", \"loanAmount\": 10000, \"applicationStatus\": \"PENDING\", \"submissionDate\": \"2024-08-20\", \"reviewDate\": null, \"createdAt\": \"2024-08-20T10:00:00\", \"updatedAt\": \"2024-08-20T10:00:00\" }"
                                    )
                            )
                    ),
                    @ApiResponse(description = "Bad request due to invalid input", responseCode = "400",
                            content = @Content(
                                    schema = @Schema(type = "object"),
                                    examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                                            name = "Validation Error Example",
                                            summary = "Example of validation errors",
                                            value = "{ \"loanAmount\": \"must be greater than 0\" }"
                                    )
                            )
                    )
            }
    )
    public ResponseEntity<LoanApplicationDTO> createLoanApplication(@Valid @RequestBody LoanApplicationDTO loanApplicationDTO) {
        try {
            LoanApplicationDTO createdLoanApplication = loanApplicationService.createLoanApplication(loanApplicationDTO);
            return new ResponseEntity<>(createdLoanApplication, HttpStatus.CREATED);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Update a Loan Application",
            description = "Update an existing loan application by ID.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Updated details of the loan application",
                    content = @Content(
                            schema = @Schema(implementation = LoanApplicationDTO.class),
                            examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                                    name = "Loan Application Update Example",
                                    summary = "Example of a loan application update request",
                                    value = "{ \"loanType\": \"AUTO\", \"loanAmount\": 20000, \"applicationStatus\": \"APPROVED\" }"
                            )
                    )
            ),
            responses = {
                    @ApiResponse(description = "Loan application updated successfully", responseCode = "200",
                            content = @Content(
                                    schema = @Schema(implementation = LoanApplicationDTO.class),
                                    examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                                            name = "Loan Application Updated Example",
                                            summary = "Example of a successful loan application update response",
                                            value = "{ \"applicationId\": 1, \"clientId\": 1, \"loanType\": \"AUTO\", \"loanAmount\": 20000, \"applicationStatus\": \"APPROVED\", \"submissionDate\": \"2024-08-20\", \"reviewDate\": \"2024-08-21\", \"createdAt\": \"2024-08-20T10:00:00\", \"updatedAt\": \"2024-08-21T10:00:00\" }"
                                    )
                            )
                    ),
                    @ApiResponse(description = "Loan application not found", responseCode = "404",
                            content = @Content(
                                    schema = @Schema(type = "string", example = "Resource not found")
                            )
                    ),
                    @ApiResponse(description = "Bad request due to invalid input", responseCode = "400",
                            content = @Content(
                                    schema = @Schema(type = "object"),
                                    examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                                            name = "Validation Error Example",
                                            summary = "Example of validation errors",
                                            value = "{ \"loanAmount\": \"must be greater than 0\" }"
                                    )
                            )
                    )
            }
    )
    public ResponseEntity<LoanApplicationDTO> updateLoanApplication(
            @Parameter(description = "ID of the loan application to be updated", example = "1") @PathVariable("id") Long id,
            @Valid @RequestBody LoanApplicationDTO loanApplicationDTO) {
        try {
            LoanApplicationDTO updatedLoanApplication = loanApplicationService.updateLoanApplication(id, loanApplicationDTO);
            return new ResponseEntity<>(updatedLoanApplication, HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete a Loan Application",
            description = "Delete a loan application by ID.",
            responses = {
                    @ApiResponse(description = "Loan application deleted successfully", responseCode = "204"),
                    @ApiResponse(description = "Loan application not found", responseCode = "404",
                            content = @Content(
                                    schema = @Schema(type = "string", example = "Resource not found")
                            )
                    )
            }
    )
    public ResponseEntity<Void> deleteLoanApplication(
            @Parameter(description = "ID of the loan application to be deleted", example = "1") @PathVariable("id") Long id) {
        try {
            loanApplicationService.deleteLoanApplication(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get Loan Application by ID",
            description = "Retrieve a loan application by its ID.",
            responses = {
                    @ApiResponse(description = "Loan application found", responseCode = "200",
                            content = @Content(
                                    schema = @Schema(implementation = LoanApplicationDTO.class),
                                    examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                                            name = "Loan Application Details Example",
                                            summary = "Example of a loan application retrieval response",
                                            value = "{ \"applicationId\": 1, \"clientId\": 1, \"loanType\": \"PERSONAL\", \"loanAmount\": 10000, \"applicationStatus\": \"PENDING\", \"submissionDate\": \"2024-08-20\", \"reviewDate\": null, \"createdAt\": \"2024-08-20T10:00:00\", \"updatedAt\": \"2024-08-20T10:00:00\" }"
                                    )
                            )
                    ),
                    @ApiResponse(description = "Loan application not found", responseCode = "404",
                            content = @Content(
                                    schema = @Schema(type = "string", example = "Resource not found")
                            )
                    )
            }
    )
    public ResponseEntity<LoanApplicationDTO> getLoanApplicationById(
            @Parameter(description = "ID of the loan application to be retrieved", example = "1") @PathVariable("id") Long id) {
        try {
            LoanApplicationDTO loanApplicationDTO = loanApplicationService.getLoanApplicationById(id);
            return new ResponseEntity<>(loanApplicationDTO, HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    @Operation(
            summary = "Get All Loan Applications",
            description = "Retrieve a list of all loan applications.",
            responses = {
                    @ApiResponse(description = "List of loan applications", responseCode = "200",
                            content = @Content(
                                    schema = @Schema(implementation = LoanApplicationDTO.class, type = "array"),
                                    examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                                            name = "Loan Applications List Example",
                                            summary = "Example of a list of loan applications",
                                            value = "[{ \"applicationId\": 1, \"clientId\": 1, \"loanType\": \"PERSONAL\", \"loanAmount\": 10000, \"applicationStatus\": \"PENDING\", \"submissionDate\": \"2024-08-20\", \"reviewDate\": null, \"createdAt\": \"2024-08-20T10:00:00\", \"updatedAt\": \"2024-08-20T10:00:00\" }, { \"applicationId\": 2, \"clientId\": 2, \"loanType\": \"AUTO\", \"loanAmount\": 20000, \"applicationStatus\": \"APPROVED\", \"submissionDate\": \"2024-08-21\", \"reviewDate\": \"2024-08-22\", \"createdAt\": \"2024-08-21T10:00:00\", \"updatedAt\": \"2024-08-22T10:00:00\" }]"
                                    )
                            )
                    )
            }
    )
    public ResponseEntity<List<LoanApplicationDTO>> getAllLoanApplications() {
        List<LoanApplicationDTO> loanApplications = loanApplicationService.getAllLoanApplications();
        return new ResponseEntity<>(loanApplications, HttpStatus.OK);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @Operation(
            summary = "Handle validation exceptions",
            description = "Handle validation errors that occur during request processing.",
            responses = {
                    @ApiResponse(description = "Validation errors", responseCode = "400",
                            content = @Content(
                                    schema = @Schema(type = "object"),
                                    examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                                            name = "Validation Error Example",
                                            summary = "Example of validation errors",
                                            value = "{ \"loanAmount\": \"must be greater than 0\", \"submissionDate\": \"must not be null\" }"
                                    )
                            )
                    )
            }
    )
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
