package com.internship.xchat.message_service.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.internship.xchat.message_service.entities.Conversation;

@Repository
public interface ConversationRepository extends MongoRepository<Conversation, String> {

    @Query("{ 'type' : ?0, 'participants' : { $all: ?1 }, $expr: { $eq: [{ $size: '$participants' }, ?2] } }")
    Optional<Conversation> findPrivateConversationByParticipants(String type, List<Long> participants, int size);

    Optional<Conversation> findByGroupId(Long groupId);

}