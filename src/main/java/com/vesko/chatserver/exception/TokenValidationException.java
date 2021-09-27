package com.vesko.chatserver.exception;

public class TokenValidationException extends JsonParsableException {
    public TokenValidationException(String message) {
        super(message);
    }
}
