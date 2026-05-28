package com.internship.xchat.message_service.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.internship.xchat.message_service.entities.Message;

@Repository
public interface MessageRepository extends MongoRepository<Message, String> {
    List<Message> findByConversationIdOrderByTimestampAsc(String conversationId);
    void deleteAllByConversationId(String conversationId);
    void deleteByConversationIdAndId(String conversationId, String id);
}