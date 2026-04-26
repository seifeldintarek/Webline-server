package com.internship.xchat.message_service.entities;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

@Data
@Document(collection = "messages")
public class Message {

    @Id
    private String id;

    @Field("conversation_id")
    private String conversationId;

    @Field("sender_id")
    private Long senderId;

    @Field("content")
    private String content;

    @Field("content_type")
    private String contentType;

    @CreatedDate
    @Field("timestamp")
    private LocalDateTime timestamp;

    @Field("recieved_by")
    private List<Long> receivedBy;

    @Field("read_by")
    private List<Long> readBy;
}