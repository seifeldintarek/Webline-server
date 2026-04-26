package com.internship.xchat.message_service.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class LastMessageDTO {
    private String messageId;
    private String content;
    private LocalDateTime timestamp;
    private Integer senderId;
}
