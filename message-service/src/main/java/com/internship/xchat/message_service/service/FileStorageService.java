package com.internship.xchat.message_service.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
    public String uploadImage(MultipartFile file, String conversationId) throws Exception;
}
