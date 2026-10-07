package com.example.integrationhub.exception;


public class ExternalCustomerNotFoundException extends RuntimeException {

    public ExternalCustomerNotFoundException(String message) {
        super(message);
    }
}