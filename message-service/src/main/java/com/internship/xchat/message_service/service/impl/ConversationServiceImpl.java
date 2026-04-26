package com.internship.xchat.message_service.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.internship.xchat.common_lib.exception.ResourceNotFoundException;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.internship.xchat.message_service.dto.ConversationDTO;
import com.internship.xchat.message_service.dto.MessageDTO;
import com.internship.xchat.message_service.entities.Conversation;
import com.internship.xchat.message_service.entities.Message;
import com.internship.xchat.message_service.enums.ConversationType;
import com.internship.xchat.message_service.mapper.ConversationMapper;
import com.internship.xchat.message_service.mapper.MessageMapper;
import com.internship.xchat.message_service.repository.ConversationRepository;
import com.internship.xchat.message_service.repository.MessageRepository;
import com.internship.xchat.message_service.service.ConversationService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ConversationServiceImpl implements ConversationService {
    private final ConversationRepository conversationRepository;
    private final MessageRepository messageRepository;
    private final ConversationMapper conversationMapper;
    private final MessageMapper messageMapper;

    public ConversationDTO saveConversation(ConversationDTO conversationDTO) {
        Conversation savedConversation = conversationRepository.save(this.conversationMapper.toEntity(conversationDTO));

        return this.conversationMapper.toDTO(savedConversation);
    }

    public ConversationDTO getConversationById(String conversationId) throws NotFoundException {
        Conversation conversation = conversationRepository.findById(conversationId)
                .orElseThrow(() -> new NotFoundException());

        return this.conversationMapper.toDTO(conversation);
    }

    public List<MessageDTO> getMessagesForConversation(String conversationId) {
        List<Message> messages = messageRepository.findByConversationIdOrderByTimestampAsc(conversationId);
        return messages.stream().map(this.messageMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public ConversationDTO getConversationByParticipantsAndType(List<Long> participants, ConversationType type) {
        Conversation conversation = this.conversationRepository
                .findPrivateConversationByParticipants(type.getType().toUpperCase(), participants, participants.size()).orElse(null);
        if(conversation == null) {
            throw new ResourceNotFoundException("Conversation");
        }
        return this.conversationMapper.toDTO(conversation);
    }

    @Override
    public ConversationDTO updateConversation(String id, ConversationDTO conversationDTO) throws NotFoundException {
        Conversation existingConversation = conversationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException());
        conversationDTO.setId(existingConversation.getId());
        Conversation conversation = this.conversationMapper.toEntity(conversationDTO);
        Conversation updatedConversation  = this.conversationRepository.save(conversation);
        return this.conversationMapper.toDTO(updatedConversation);
    }
}
