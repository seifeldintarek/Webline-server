package com.internship.xchat.message_service.dto;

import com.internship.xchat.message_service.enums.MessageType;
import lombok.Data;

@Data
public class WsMessagePayload {
    private String conversationId;
    private Long senderId;
    private MessageType type;
    private String textContent;
    private AttachmentDto attachment;
}