package com.internship.xchat.user_service.service;

public interface FileService {
    public String getImageUrl(Long uid);
    public String uploadImage(Long uid, String base64Image);
}
