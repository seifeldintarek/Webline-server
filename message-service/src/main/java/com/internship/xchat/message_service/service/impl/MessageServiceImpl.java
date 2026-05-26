package com.internship.xchat.message_service.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.internship.xchat.common_lib.exception.ResourceNotFoundException;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.internship.xchat.message_service.dto.MessageDTO;
import com.internship.xchat.message_service.entities.Message;
import com.internship.xchat.message_service.mapper.MessageMapper;
import com.internship.xchat.message_service.repository.MessageRepository;
import com.internship.xchat.message_service.service.MessageService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final MessageMapper messageMapper;

    @Override
    public MessageDTO saveMessage(MessageDTO messageDTO) {
        Message savedMessage = this.messageRepository.save(this.messageMapper.toEntity(messageDTO));
        return this.messageMapper.toDTO(savedMessage);
    }

    @Override
    public MessageDTO getMessageById(String id) throws NotFoundException {
        Optional<Message> message = this.messageRepository.findById(id);
        return this.messageMapper.toDTO(message.orElseThrow(() -> new NotFoundException()));
    }

    @Override
    public List<MessageDTO> getMessagesForConversation(String conversationId) {
        List<Message> messages = this.messageRepository.findByConversationIdOrderByTimestampAsc(conversationId);
        return messages.stream().map(this.messageMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public void deleteAllMessagesByConversationId(String conversationId) {
        try {
            this.messageRepository.deleteAllByConversationId(conversationId);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Messages");
        }
    }

    @Override
    public void deleteByConversationIdAndId(String conversationId, String messageId) {
        this.messageRepository.deleteByConversationIdAndId(conversationId, messageId);
    }
}
