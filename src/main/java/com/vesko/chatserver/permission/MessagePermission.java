package com.vesko.chatserver.permission;

import org.springframework.stereotype.Component;

@Component
public class MessagePermission {
    boolean canWrite() {
        return false;
    }

    boolean cabRead() {
        return false;
    }
}
