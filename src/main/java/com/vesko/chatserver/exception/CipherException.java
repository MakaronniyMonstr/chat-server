package com.vesko.chatserver.exception;

import java.util.Collections;
import java.util.Map;

public class CipherException extends RuntimeException {
    public CipherException(String message) {
        super(message);
    }

    public Map<String, String> getJsonSerializableMessage() {
        return Collections.singletonMap("error", getMessage());
    }
}
