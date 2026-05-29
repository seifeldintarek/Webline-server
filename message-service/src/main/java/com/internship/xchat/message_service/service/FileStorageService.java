package com.internship.xchat.message_service.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
    public String uploadFiles(MultipartFile file, String conversationId, String fileType) throws Exception;
}
