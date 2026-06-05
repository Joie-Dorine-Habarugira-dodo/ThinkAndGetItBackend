package com.thinkAndGetIt.backend.constants;

public enum StatusCodes {
    OK(200, "Request succeeded"),
    CREATED(201, "Resource created successfully"),
    BAD_REQUEST(400, "Invalid or expired token"),
    UNAUTHORIZED(401, "Authentication required or failed"),
    FORBIDDEN(403, "Access denied"),
    NOT_FOUND(404, "Resource not found"),
    CONFLICT(409, "Resource already exists"),
    INTERNAL_SERVER_ERROR(500, "Unexpected server error"),
    SERVICE_UNAVAILABLE(503, "Service temporarily unavailable");

    private final int code;
    private final String message;

    StatusCodes(int code, String message) {
        this.code = code;
        this.message = message;
    }
    public int code() {
        return code;
    }
    public String message() {
        return message;
    }
}
