package com.vesko.chatserver.exception;

public class ActionNotPermittedException extends JsonParsableException {

    public ActionNotPermittedException() {
        super("Action is not permitted");
    }
}
