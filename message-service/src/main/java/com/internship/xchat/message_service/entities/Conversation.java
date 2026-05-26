package com.internship.xchat.message_service.entities;

import lombok.Data;

import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.*;

import com.internship.xchat.message_service.enums.ConversationType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
@Document(collection = "conversations")
public class Conversation {

    @Id
    private String id;

    @Field("type")
    private ConversationType type;

    @Field("groupId")
    private Long groupId;

    @Field("name")
    private String name;

    @Field("participants")
    private List<Long> participants;

    @Field("is_blocked")
    private Map<String, Boolean> isBlocked;

    // @Field("last_message")
    // private LastMessage lastMessage;

    @CreatedDate
    @Field("created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Field("updated_at")
    private LocalDateTime updatedAt;

    @Field("last_modified_by")
    private Long lastModifiedBy;

    // @Data
    // public static class LastMessage {
    //     @Field("message_id")
    //     private String messageId;

    //     @Field("content")
    //     private String content;

    //     @Field("content_type")
    //     private String contentType;

    //     @Field("timestamp")
    //     private LocalDateTime timestamp;

    //     @Field("sender_id")
    //     private Long senderId;
    // }
}