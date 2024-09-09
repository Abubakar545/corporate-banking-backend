package com.syed.loandisbursement.controller;

import com.syed.loandisbursement.dto.LoanApplicationDTO;
import com.syed.loandisbursement.dto.LoanDisbursementDTO;
import com.syed.loandisbursement.exception.ResourceNotFoundException;
import com.syed.loandisbursement.client.LoanApplicationClient;
import com.syed.loandisbursement.service.LoanDisbursementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/loan-disbursements")
@Validated
@Tag(name = "Loan Disbursements", description = "Operations related to loan disbursements.")
public class LoanDisbursementController {

    @Autowired
    private LoanApplicationClient loanApplicationClient;

    @Autowired
    private LoanDisbursementService loanDisbursementService;

    @GetMapping("/fetch-application/{id}")
    @Operation(
            summary = "Fetch Loan Application by ID",
            description = "Retrieve a loan application by its ID.",
            tags = {"Loan Disbursements"},
            responses = {
                    @ApiResponse(description = "Loan application found", responseCode = "200",
                            content = @Content(
                                    schema = @Schema(implementation = LoanApplicationDTO.class),
                                    examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                                            name = "Loan Application Example",
                                            summary = "Example of a loan application retrieval response",
                                            value = "{ \"id\": 1, \"loanType\": \"Personal\", \"loanAmount\": 10000, \"applicationStatus\": \"Approved\" }"
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
    public ResponseEntity<LoanApplicationDTO> fetchApplicationById(
            @Parameter(description = "ID of the loan application to be retrieved", example = "1") @PathVariable("id") Long id) {
        LoanApplicationDTO loanApplicationDTO = loanApplicationClient.getApplicationById(id);
        return ResponseEntity.ok(loanApplicationDTO);
    }

    @PostMapping("/submit")
    @Operation(
            summary = "Submit a Loan Disbursement",
            description = "Submit a new loan disbursement.",
            tags = {"Loan Disbursements"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Details of the loan disbursement to submit",
                    content = @Content(
                            schema = @Schema(implementation = LoanDisbursementDTO.class),
                            examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                                    name = "Loan Disbursement Submission Example",
                                    summary = "Example of a loan disbursement submission request",
                                    value = "{ \"loanId\": 1, \"disbursementAmount\": 10000, \"disbursementDate\": \"2024-08-20\" }"
                            )
                    )
            ),
            responses = {
                    @ApiResponse(description = "Loan disbursement submitted successfully", responseCode = "200",
                            content = @Content(
                                    schema = @Schema(implementation = LoanDisbursementDTO.class),
                                    examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                                            name = "Loan Disbursement Created Example",
                                            summary = "Example of a successful loan disbursement submission response",
                                            value = "{ \"id\": 1, \"loanId\": 1, \"disbursementAmount\": 10000, \"disbursementDate\": \"2024-08-20\" }"
                                    )
                            )
                    ),
                    @ApiResponse(description = "Resource not found", responseCode = "404",
                            content = @Content(
                                    schema = @Schema(type = "string", example = "Resource not found")
                            )
                    )
            }
    )
    public ResponseEntity<LoanDisbursementDTO> submitDisbursement(
            @RequestBody @Valid LoanDisbursementDTO loanDisbursementDTO) throws ResourceNotFoundException {
        LoanDisbursementDTO createdDisbursement = loanDisbursementService.submitDisbursement(loanDisbursementDTO);
        return ResponseEntity.ok(createdDisbursement);
    }

    @GetMapping("/all")
    @Operation(
            summary = "Get All Loan Disbursements",
            description = "Retrieve a list of all loan disbursements.",
            tags = {"Loan Disbursements"},
            responses = {
                    @ApiResponse(description = "List of all loan disbursements", responseCode = "200",
                            content = @Content(
                                    schema = @Schema(implementation = LoanDisbursementDTO.class, type = "array"),
                                    examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                                            name = "Loan Disbursements List Example",
                                            summary = "Example of a list of loan disbursements",
                                            value = "[{ \"id\": 1, \"loanId\": 1, \"disbursementAmount\": 10000, \"disbursementDate\": \"2024-08-20\" }, { \"id\": 2, \"loanId\": 2, \"disbursementAmount\": 15000, \"disbursementDate\": \"2024-08-21\" }]"
                                    )
                            )
                    )
            }
    )
    public ResponseEntity<List<LoanDisbursementDTO>> getAllDisbursements() {
        List<LoanDisbursementDTO> disbursements = loanDisbursementService.getAllDisbursements();
        return ResponseEntity.ok(disbursements);
    }

    @PutMapping("/approve/{id}")
    @Operation(
            summary = "Approve a Loan Disbursement",
            description = "Approve a loan disbursement by its ID.",
            tags = {"Loan Disbursements"},
            responses = {
                    @ApiResponse(description = "Loan disbursement approved successfully", responseCode = "200",
                            content = @Content(
                                    schema = @Schema(implementation = LoanDisbursementDTO.class),
                                    examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                                            name = "Loan Disbursement Approved Example",
                                            summary = "Example of a successful loan disbursement approval response",
                                            value = "{ \"id\": 1, \"loanId\": 1, \"disbursementAmount\": 10000, \"disbursementDate\": \"2024-08-20\", \"status\": \"Approved\" }"
                                    )
                            )
                    ),
                    @ApiResponse(description = "Loan disbursement not found", responseCode = "404",
                            content = @Content(
                                    schema = @Schema(type = "string", example = "Resource not found")
                            )
                    )
            }
    )
    public ResponseEntity<LoanDisbursementDTO> approveDisbursement(
            @Parameter(description = "ID of the loan disbursement to be approved", example = "1") @PathVariable Long id) throws ResourceNotFoundException {
        LoanDisbursementDTO approvedDisbursement = loanDisbursementService.approveDisbursement(id);
        return ResponseEntity.ok(approvedDisbursement);
    }

    @PutMapping("/reject/{id}")
    @Operation(
            summary = "Reject a Loan Disbursement",
            description = "Reject a loan disbursement by its ID.",
            tags = {"Loan Disbursements"},
            responses = {
                    @ApiResponse(description = "Loan disbursement rejected successfully", responseCode = "200",
                            content = @Content(
                                    schema = @Schema(implementation = LoanDisbursementDTO.class),
                                    examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                                            name = "Loan Disbursement Rejected Example",
                                            summary = "Example of a successful loan disbursement rejection response",
                                            value = "{ \"id\": 1, \"loanId\": 1, \"disbursementAmount\": 10000, \"disbursementDate\": \"2024-08-20\", \"status\": \"Rejected\" }"
                                    )
                            )
                    ),
                    @ApiResponse(description = "Loan disbursement not found", responseCode = "404",
                            content = @Content(
                                    schema = @Schema(type = "string", example = "Resource not found")
                            )
                    )
            }
    )
    public ResponseEntity<LoanDisbursementDTO> rejectDisbursement(
            @Parameter(description = "ID of the loan disbursement to be rejected", example = "1") @PathVariable Long id) throws ResourceNotFoundException {
        LoanDisbursementDTO rejectedDisbursement = loanDisbursementService.rejectDisbursement(id);
        return ResponseEntity.ok(rejectedDisbursement);
    }
}
