package com.internship.xchat.message_service.enums;

public enum ConversationType {
    GROUP("GROUP"),
    PRIVATE("PRIVATE");

    private final String type;

    ConversationType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
