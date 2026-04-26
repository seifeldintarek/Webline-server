package com.internship.xchat.message_service.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import com.internship.xchat.message_service.enums.ConversationType;

import lombok.Data;

@Data
public class ConversationDTO {
    private String id;
    private ConversationType type;
    private Long groupId;
    private String name;
    private List<Long> participants;
    private Map<String, Boolean> isBlocked;
    //private LastMessageDTO lastMessage;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long lastModifiedBy;
}