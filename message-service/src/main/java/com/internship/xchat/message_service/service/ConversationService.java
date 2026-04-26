package com.internship.xchat.message_service.service;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import com.internship.xchat.message_service.dto.ConversationDTO;
import com.internship.xchat.message_service.dto.MessageDTO;
import com.internship.xchat.message_service.enums.ConversationType;

import java.util.List;

public interface ConversationService {
    
    public ConversationDTO getConversationById(String conversationId) throws NotFoundException;
    public ConversationDTO getConversationByParticipantsAndType(List<Long> participants, ConversationType type);
    public ConversationDTO saveConversation(ConversationDTO conversationDTO);
    public ConversationDTO updateConversation(String id, ConversationDTO conversationDTO) throws NotFoundException;
    public List<MessageDTO> getMessagesForConversation(String conversationId);
}