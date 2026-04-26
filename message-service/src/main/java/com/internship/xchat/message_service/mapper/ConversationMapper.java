package com.internship.xchat.message_service.mapper;

import org.springframework.stereotype.Component;

import com.internship.xchat.message_service.dto.ConversationDTO;
import com.internship.xchat.message_service.entities.Conversation;

@Component
public class ConversationMapper {
    public ConversationDTO toDTO(Conversation conversation){
        ConversationDTO conversationDTO = new ConversationDTO();
        if(conversation.getId() != null) {
            conversationDTO.setId(conversation.getId());
        }
        if(conversation.getGroupId() != null) {
            conversationDTO.setGroupId(conversation.getGroupId());
        }
        if(conversation.getIsBlocked() != null) {
            conversationDTO.setIsBlocked(conversation.getIsBlocked());
        }
        if(conversation.getName() != null) {
            conversationDTO.setName(conversation.getName());
        }
        if(conversation.getParticipants() != null) {
            conversationDTO.setParticipants(conversation.getParticipants());
        }
        if(conversation.getType() != null) {
            conversationDTO.setType(conversation.getType());
        }
        if(conversation.getCreatedAt() != null) {
            conversationDTO.setCreatedAt(conversation.getCreatedAt());
        }
        if(conversation.getUpdatedAt() != null) {
            conversationDTO.setUpdatedAt(conversation.getUpdatedAt());
        }
        if(conversation.getLastModifiedBy() != null) {
            conversationDTO.setLastModifiedBy(conversation.getLastModifiedBy());
        }
        return conversationDTO;
    }

    public Conversation toEntity(ConversationDTO conversationDTO) {
        Conversation conversation = new Conversation();
        if(conversationDTO.getId() != null) {
            conversation.setId(conversationDTO.getId());
        }
        if(conversationDTO.getGroupId() != null) {
            conversation.setGroupId(conversationDTO.getGroupId());
        }
        if(conversationDTO.getIsBlocked() != null) {
            conversation.setIsBlocked(conversationDTO.getIsBlocked());
        }
        if(conversationDTO.getName() != null) {
            conversation.setName(conversationDTO.getName());
        }
        if(conversationDTO.getParticipants() != null) {
            conversation.setParticipants(conversationDTO.getParticipants());
        }
        if(conversationDTO.getType() != null) {
            conversation.setType(conversationDTO.getType());
        }
        return conversation;
    }
}
