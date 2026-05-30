package com.internship.xchat.message_service.dto;


import lombok.Data;

@Data
public class CallInitiateDto {
    private String roomId;
    private Long callerId;
    private String callerName;
    private String callerAvatar;
    private String conversationId;
    private Long groupId;
    private String callType;   // "PRIVATE" or "GROUP"
    private Long receiverId;
}
