package com.internship.xchat.common_lib.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.internship.xchat.common_lib.exception.HttpErrorResponse;
import com.internship.xchat.common_lib.exception.ResourceNotFoundException;

@RestController
@ControllerAdvice
public class AdviceController {
    private Logger logger = LoggerFactory.getLogger(AdviceController.class);

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<HttpErrorResponse> handleResourceNotFound(
            ResourceNotFoundException ex, WebRequest request) {

        HttpErrorResponse response = new HttpErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                "Resource Not Found",
                ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<HttpErrorResponse> handleGenericException(Exception ex) {
        logger.error(ex.getMessage());
        HttpErrorResponse response = new HttpErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "An unexpected error occurred",
                "");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
