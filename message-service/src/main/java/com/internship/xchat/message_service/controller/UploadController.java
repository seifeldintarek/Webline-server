package com.internship.xchat.message_service.controller;

import com.internship.xchat.message_service.dto.AttachmentDto;
import com.internship.xchat.message_service.service.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/messages/upload")
@RequiredArgsConstructor
public class UploadController {

    private final FileStorageService fileStorageService;

    @PostMapping
    public ResponseEntity<AttachmentDto> upload(@RequestParam("file") MultipartFile file,@RequestParam("conversationId") String conversationId)
            throws Exception {

        String url = fileStorageService.uploadImage(file, conversationId);

        AttachmentDto dto = new AttachmentDto();
        dto.setUrl(url);
        dto.setFileName(file.getOriginalFilename());
        dto.setMimeType(file.getContentType());
        dto.setFileSizeBytes(file.getSize());

        return ResponseEntity.ok(dto);
    }
}
