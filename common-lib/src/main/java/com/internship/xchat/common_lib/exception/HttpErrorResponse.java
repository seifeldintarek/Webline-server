package com.internship.xchat.common_lib.exception;

public class HttpErrorResponse {
    private int status;
    private String error;
    private String message;

    public HttpErrorResponse(int status, String error, String message) {
        this.status = status;
        this.error = error;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }
}

