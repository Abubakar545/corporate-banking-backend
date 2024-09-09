package com.syed.loanapplication.controller;

import com.syed.loanapplication.dto.LoanReviewDTO;
import com.syed.loanapplication.exception.ResourceNotFoundException;
import com.syed.loanapplication.service.ILoanReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/loan-reviews")
@Tag(name = "Loan Reviews", description = "Operations related to loan reviews.")
public class LoanReviewController {

    @Autowired
    private ILoanReviewService loanReviewService;

    @GetMapping("/{id}")
    @Operation(
            summary = "Get Loan Review by ID",
            description = "Retrieve a loan review by its ID.",
            tags = {"Loan Reviews"},
            responses = {
                    @ApiResponse(description = "Loan review found", responseCode = "200",
                            content = @Content(
                                    schema = @Schema(implementation = LoanReviewDTO.class),
                                    examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                                            name = "Loan Review Example",
                                            summary = "Example of a loan review retrieval response",
                                            value = "{ \"id\": 1, \"review\": \"Approved\", \"comments\": \"Reviewed and approved.\" }"
                                    )
                            )
                    ),
                    @ApiResponse(description = "Loan review not found", responseCode = "404",
                            content = @Content(
                                    schema = @Schema(type = "string", example = "Resource not found")
                            )
                    )
            }
    )
    public ResponseEntity<LoanReviewDTO> getLoanReview(
            @Parameter(description = "ID of the loan review to be retrieved", example = "1") @PathVariable Long id) {
        Optional<LoanReviewDTO> loanReviewDTO = loanReviewService.getLoanReview(id);
        return loanReviewDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(
            summary = "Create a Loan Review",
            description = "Create a new loan review.",
            tags = {"Loan Reviews"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Details of the loan review to create",
                    content = @Content(
                            schema = @Schema(implementation = LoanReviewDTO.class),
                            examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                                    name = "Loan Review Creation Example",
                                    summary = "Example of a loan review creation request",
                                    value = "{ \"review\": \"Approved\", \"comments\": \"Reviewed and approved.\" }"
                            )
                    )
            ),
            responses = {
                    @ApiResponse(description = "Loan review created successfully", responseCode = "201",
                            content = @Content(
                                    schema = @Schema(implementation = LoanReviewDTO.class),
                                    examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                                            name = "Loan Review Created Example",
                                            summary = "Example of a successful loan review creation response",
                                            value = "{ \"id\": 1, \"review\": \"Approved\", \"comments\": \"Reviewed and approved.\" }"
                                    )
                            )
                    ),
                    @ApiResponse(description = "Bad request", responseCode = "400",
                            content = @Content(
                                    schema = @Schema(type = "string", example = "Invalid request")
                            )
                    )
            }
    )
    public ResponseEntity<LoanReviewDTO> createLoanReview(@Valid @RequestBody LoanReviewDTO loanReviewDTO) {
        LoanReviewDTO createdLoanReview = loanReviewService.createLoanReview(loanReviewDTO);
        return new ResponseEntity<>(createdLoanReview, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Update a Loan Review",
            description = "Update an existing loan review by ID.",
            tags = {"Loan Reviews"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Updated details of the loan review",
                    content = @Content(
                            schema = @Schema(implementation = LoanReviewDTO.class),
                            examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                                    name = "Loan Review Update Example",
                                    summary = "Example of a loan review update request",
                                    value = "{ \"review\": \"Rejected\", \"comments\": \"Reviewed and rejected.\" }"
                            )
                    )
            ),
            responses = {
                    @ApiResponse(description = "Loan review updated successfully", responseCode = "200",
                            content = @Content(
                                    schema = @Schema(implementation = LoanReviewDTO.class),
                                    examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                                            name = "Loan Review Updated Example",
                                            summary = "Example of a successful loan review update response",
                                            value = "{ \"id\": 1, \"review\": \"Rejected\", \"comments\": \"Reviewed and rejected.\" }"
                                    )
                            )
                    ),
                    @ApiResponse(description = "Loan review not found", responseCode = "404",
                            content = @Content(
                                    schema = @Schema(type = "string", example = "Resource not found")
                            )
                    ),
                    @ApiResponse(description = "Bad request", responseCode = "400",
                            content = @Content(
                                    schema = @Schema(type = "string", example = "Invalid request")
                            )
                    )
            }
    )
    public ResponseEntity<LoanReviewDTO> updateLoanReview(
            @Parameter(description = "ID of the loan review to be updated", example = "1") @PathVariable Long id,
            @Valid @RequestBody LoanReviewDTO loanReviewDTO) {
        try {
            LoanReviewDTO updatedLoanReview = loanReviewService.updateLoanReview(id, loanReviewDTO);
            return ResponseEntity.ok(updatedLoanReview);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete a Loan Review",
            description = "Delete a loan review by ID.",
            tags = {"Loan Reviews"},
            responses = {
                    @ApiResponse(description = "Loan review deleted successfully", responseCode = "204"),
                    @ApiResponse(description = "Loan review not found", responseCode = "404",
                            content = @Content(
                                    schema = @Schema(type = "string", example = "Resource not found")
                            )
                    ),
                    @ApiResponse(description = "Bad request", responseCode = "400",
                            content = @Content(
                                    schema = @Schema(type = "string", example = "Invalid request")
                            )
                    )
            }
    )
    public ResponseEntity<Void> deleteLoanReview(
            @Parameter(description = "ID of the loan review to be deleted", example = "1") @PathVariable Long id) {
        try {
            loanReviewService.deleteLoanReview(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    @Operation(
            summary = "Get All Loan Reviews",
            description = "Retrieve a list of all loan reviews.",
            tags = {"Loan Reviews"},
            responses = {
                    @ApiResponse(description = "List of loan reviews", responseCode = "200",
                            content = @Content(
                                    schema = @Schema(implementation = LoanReviewDTO.class, type = "array"),
                                    examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                                            name = "Loan Reviews List Example",
                                            summary = "Example of a list of loan reviews",
                                            value = "[{ \"id\": 1, \"review\": \"Approved\", \"comments\": \"Reviewed and approved.\" }, { \"id\": 2, \"review\": \"Rejected\", \"comments\": \"Reviewed and rejected.\" }]"
                                    )
                            )
                    )
            }
    )
    public ResponseEntity<List<LoanReviewDTO>> getAllLoanReviews() {
        List<LoanReviewDTO> loanReviews = loanReviewService.getAllLoanReviews();
        return ResponseEntity.ok(loanReviews);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @Operation(
            summary = "Handle Validation Exceptions",
            description = "Handle validation exceptions for request parameters.",
            tags = {"Error Handling"},
            responses = {
                    @ApiResponse(description = "Validation errors", responseCode = "400",
                            content = @Content(
                                    schema = @Schema(type = "object", example = "{ \"fieldName\": \"error message\" }")
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
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errors);
    }
}
