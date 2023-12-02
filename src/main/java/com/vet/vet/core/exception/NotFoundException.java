package com.vet.vet.core.exception;

// else throw larda lambda açıp içine new NotFoundException de içine mesaj koy
// globalexceptionhandlerda bunun metodunu yaz

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message) {
        super(message);
    }
}
