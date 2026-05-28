package com.internship.xchat.message_service.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class AttachmentDto {
    private String url;
    private String fileName;
    private String mimeType;
    private Long fileSizeBytes;
    private Integer durationMs;
}
