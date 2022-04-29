package com.prokhnov.spring_rest.exception_handling;

public class NotSuchEmployeeException extends RuntimeException {

    public NotSuchEmployeeException(String message) {
        super(message);
    }
}
