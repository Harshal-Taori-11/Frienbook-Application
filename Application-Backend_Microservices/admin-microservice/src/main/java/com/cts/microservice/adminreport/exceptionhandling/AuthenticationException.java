package com.cts.microservice.adminreport.exceptionhandling;

public class AuthenticationException extends RuntimeException {

	public AuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
}
