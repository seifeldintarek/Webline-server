package com.internship.xchat.message_service.service.impl;

import com.internship.xchat.message_service.service.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileStorageServiceImpl implements FileStorageService {
    private final WebClient supabaseWebClient;

    @Value("${supabase.bucket}")
    private String bucket;

    @Value("${supabase.url}")
    private String supabaseUrl;

    @Override
    public String uploadImage(MultipartFile file, String conversationId) throws Exception{
        String fileName = conversationId + "/" + UUID.randomUUID() + "_" + file.getOriginalFilename();

        supabaseWebClient.post()
                .uri("/storage/v1/object/" + bucket + "/" + fileName)
                .header("Content-Type", file.getContentType())
                .bodyValue(file.getBytes())
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return supabaseUrl + "/storage/v1/object/public/" + bucket + "/" + fileName;

    }


}
