package com.internship.xchat.message_service.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.internship.xchat.message_service.enums.MessageType;
import lombok.Data;

@Data
public class MessageDTO {
    private String id;
    private String conversationId;
    private Long senderId;
    private String content;
    private MessageType contentType;
    private LocalDateTime timestamp;
    private List<Long> receivedBy;
    private List<Long> readBy;
    private AttachmentDto attachment;
}
