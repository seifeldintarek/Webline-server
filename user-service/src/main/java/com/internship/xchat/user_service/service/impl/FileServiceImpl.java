package com.internship.xchat.user_service.service.impl;

import com.internship.xchat.user_service.repository.UserRepository;
import com.internship.xchat.user_service.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import java.util.Base64;
import java.util.Map;


@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private UserRepository userRepository;

    @Value("${supabase.url}")
    private String supaUrl;

    @Value("${supabase.key}")
    private String supaKey;

    @Value("${supabase.bucket}")
    private String supaBucket;

    @Override
    public String getImageUrl(Long uid)
    {
        return this.userRepository.findImageByid(uid);
    }

    @Override
    public String uploadImage(Long uid, String base64Image) {
        String fileName = uid + "_" + System.currentTimeMillis() + getFileExtension(base64Image);
        String filePath = this.supaBucket + "/" + fileName;

        uploadToSupabase(fileName, base64Image);

        String url = this.supaUrl.trim() + "/storage/v1/object/public/" + filePath.trim();
        System.out.println(url);

        this.userRepository.updateUserImage(uid, url);

        return url;
    }

    private void uploadToSupabase(String fileName, String base64Image) {
        try {
            String cleanBase64 = base64Image.contains(",") ? base64Image.split(",")[1] : base64Image;
            byte[] imageBytes = Base64.getDecoder().decode(cleanBase64);

            String uploadUrl = this.supaUrl + "/storage/v1/object/" + this.supaBucket + "/" + fileName;

            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + this.supaKey);
            headers.set("Content-Type", getContentType(base64Image));

            HttpEntity<byte[]> request = new HttpEntity<>(imageBytes, headers);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<Map> response = restTemplate.postForEntity(uploadUrl, request, Map.class);

            if (!response.getStatusCode().is2xxSuccessful()) {
                throw new RuntimeException("Upload failed: " + response.getStatusCode());
            }

        } catch (Exception e) {
            throw new RuntimeException("Image upload failed: " + e.getMessage(), e);
        }
    }

    private String getFileExtension(String base64Image) {
        if (base64Image.startsWith("data:image/jpeg")) return ".jpg";
        if (base64Image.startsWith("data:image/png")) return ".png";
        if (base64Image.startsWith("data:image/webp")) return ".webp";
        return ".jpg";
    }

    private String getContentType(String base64Image) {
        if (base64Image.startsWith("data:image/jpeg")) return "image/jpeg";
        if (base64Image.startsWith("data:image/png")) return "image/png";
        if (base64Image.startsWith("data:image/webp")) return "image/webp";
        return "image/jpeg";
    }
}