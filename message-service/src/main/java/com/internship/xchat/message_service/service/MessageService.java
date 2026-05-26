package com.internship.xchat.message_service.service;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.internship.xchat.message_service.dto.MessageDTO;

import java.util.List;

public interface MessageService {
    public MessageDTO saveMessage(MessageDTO messageDTO);
    public MessageDTO getMessageById(String id) throws NotFoundException;
    public List<MessageDTO> getMessagesForConversation(String conversationId);
    public void deleteAllMessagesByConversationId(String conversationId);
    public void deleteByConversationIdAndId(String conversationId, String messageId);

}
