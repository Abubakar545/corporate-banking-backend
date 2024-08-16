package com.syed.loanapplication.constants;

public class ResponseConstants {

    // Status Codes
    public static final int STATUS_OK = 200;
    public static final int STATUS_CREATED = 201;
    public static final int STATUS_ACCEPTED = 202;
    public static final int STATUS_NO_CONTENT = 204;
    public static final int STATUS_BAD_REQUEST = 400;
    public static final int STATUS_UNAUTHORIZED = 401;
    public static final int STATUS_FORBIDDEN = 403;
    public static final int STATUS_NOT_FOUND = 404;
    public static final int STATUS_CONFLICT = 409;
    public static final int STATUS_UNPROCESSABLE_ENTITY = 422;
    public static final int STATUS_INTERNAL_SERVER_ERROR = 500;

    // Status Messages
    public static final String MESSAGE_SUCCESS = "Operation was successful."; // Generic success message
    public static final String MESSAGE_OK = "Request was successful.";
    public static final String MESSAGE_CREATED = "Resource has been created.";
    public static final String MESSAGE_ACCEPTED = "Request accepted but not yet processed.";
    public static final String MESSAGE_NO_CONTENT = "No content to return.";
    public static final String MESSAGE_BAD_REQUEST = "The request could not be understood or was missing required parameters.";
    public static final String MESSAGE_UNAUTHORIZED = "Authentication failed or user does not have permissions.";
    public static final String MESSAGE_FORBIDDEN = "Access is forbidden.";
    public static final String MESSAGE_NOT_FOUND = "The requested resource was not found.";
    public static final String MESSAGE_CONFLICT = "Conflict in request. Resource may already exist.";
    public static final String MESSAGE_UNPROCESSABLE_ENTITY = "The request was well-formed but was unable to be followed due to semantic errors.";
    public static final String MESSAGE_INTERNAL_SERVER_ERROR = "An error occurred on the server.";

    private ResponseConstants() {
        // Prevent instantiation
    }
}
