package com.internship.xchat.message_service.dto;

import lombok.Data;

@Data
public class CallDeclineDto {
    private String roomId;
    private Long declinedBy;
    private Long callerId;
}