package com.internship.xchat.message_service.mapper;

import org.springframework.stereotype.Component;

import com.internship.xchat.message_service.dto.MessageDTO;
import com.internship.xchat.message_service.entities.Message;

@Component
public class MessageMapper {
    public MessageDTO toDTO(Message message) {
        MessageDTO messageDTO = new MessageDTO();
        if(message.getId() != null) {
            messageDTO.setId(message.getId().toString());
        }
        if(message.getContent() != null) {
            messageDTO.setContent(message.getContent());
        }
        if(message.getContentType() != null) {
            messageDTO.setContentType(message.getContentType());
        }
        if(message.getConversationId() != null) {
            messageDTO.setConversationId(message.getConversationId());
        }
        if(message.getReadBy() != null) {
            messageDTO.setReadBy(message.getReadBy());
        }
        if(message.getReceivedBy() != null) {
            messageDTO.setReceivedBy(message.getReceivedBy());
        }
        if(message.getSenderId() != null) {
            messageDTO.setSenderId(message.getSenderId());
        }
        if(message.getTimestamp() != null) {
            messageDTO.setTimestamp(message.getTimestamp());
        }
        return messageDTO;
    }

    public Message toEntity(MessageDTO messageDTO) {
        Message message = new Message();
        if(messageDTO.getContent() != null) {
            message.setContent(messageDTO.getContent());
        }
        if(messageDTO.getContentType() != null) {
            message.setContentType(messageDTO.getContentType());
        }
        if(messageDTO.getConversationId() != null) {
            message.setConversationId(messageDTO.getConversationId());
        }
        if(messageDTO.getSenderId() != null) {
            message.setSenderId(messageDTO.getSenderId());
        }
        return message;
    }
}
