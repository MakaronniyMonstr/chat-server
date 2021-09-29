package com.vesko.chatserver.exception;

public class ResourceNotFoundException extends JsonParsableException {
    public ResourceNotFoundException(String id) {
        super(id);
    }
}
