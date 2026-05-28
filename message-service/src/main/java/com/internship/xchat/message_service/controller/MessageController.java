package com.internship.xchat.message_service.controller;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import com.internship.xchat.message_service.dto.ConversationDTO;
import com.internship.xchat.message_service.dto.MessageDTO;
import com.internship.xchat.message_service.service.ConversationService;
import com.internship.xchat.message_service.service.MessageService;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/messages")
public class MessageController {
    private final SimpMessageSendingOperations messagingTemplate;
    private final ConversationService conversationService;
    private final MessageService messageService;

    @MessageMapping("/chat.sendMessage/{conversationId}")
    public void sendMessage(@Payload MessageDTO chatMessage,
                            @DestinationVariable("conversationId") String conversationId,
                            SimpMessageHeaderAccessor headerAccessor) throws NotFoundException {

//        Long userId = (Long) headerAccessor.getSessionAttributes().get("id");
//
//        if (!userId.equals(chatMessage.getSenderId())) {
//            throw new RuntimeException("Unauthorized: userId does not match token");
//        }
        ConversationDTO conversation = conversationService.getConversationById(conversationId);
        if (conversation != null && conversation.getParticipants().contains(chatMessage.getSenderId())) {
            MessageDTO savedMessage = messageService.saveMessage(chatMessage);
            messagingTemplate.convertAndSend("/conversation/" + conversationId, savedMessage);
        } else {
            throw new HttpClientErrorException(HttpStatusCode.valueOf(401), "Unauthorized");
        }
    }

    @GetMapping("/{conversationId}/messages")
    public List<MessageDTO> getMessages(
            @PathVariable("conversationId") String conversationId) throws NotFoundException {

        Authentication userAuthentication = SecurityContextHolder.getContext().getAuthentication();
        Claims userClaims = (Claims) userAuthentication.getPrincipal();
        Long userId = userClaims.get("id", Long.class);


        List<MessageDTO> messages = messageService.getMessagesForConversation(conversationId);
        return messages == null ? Collections.emptyList() : messages;

    }

    @DeleteMapping("/{convId}")
    public void deleteConversation(@PathVariable("convId") String convId) throws NotFoundException {
        Authentication userAuthentication = SecurityContextHolder.getContext().getAuthentication();
        Claims userClaims = (Claims) userAuthentication.getPrincipal();
        Long userId = userClaims.get("id", Long.class);

        this.messageService.deleteAllMessagesByConversationId(convId);
    }

    @DeleteMapping("/{convId}/{msgId}")
    public void deleteById(@PathVariable("convId") String convId, @PathVariable("msgId") String messageId )
    {
        this.messageService.deleteByConversationIdAndId(convId, messageId);
    }

}