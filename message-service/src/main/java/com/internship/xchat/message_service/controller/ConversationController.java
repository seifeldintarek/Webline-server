package com.internship.xchat.message_service.controller;

import java.util.List;

import com.internship.xchat.common_lib.exception.ResourceNotFoundException;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.internship.xchat.message_service.dto.ConversationDTO;
import com.internship.xchat.message_service.enums.ConversationType;
import com.internship.xchat.message_service.service.ConversationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequiredArgsConstructor
@RequestMapping("/conversation")
public class ConversationController {
    private final ConversationService conversationService;

    @GetMapping("/{id}")
    public ConversationDTO getConversationById(@PathVariable("id") String id) throws NotFoundException {
        return this.conversationService.getConversationById(id);
    }

    @GetMapping("")
    public ConversationDTO getConversationByParticipantsAndType(
            @RequestParam("participant_ids") List<Long> participants,
            @RequestParam("conversation_type") ConversationType type) {
        return this.conversationService.getConversationByParticipantsAndType(participants, type);
    }

    @PostMapping("")
    public ConversationDTO createConversation(@RequestBody ConversationDTO conversationDTO) {
        return this.conversationService.saveConversation(conversationDTO);
    }
    
    @PutMapping("/{id}")
    public ConversationDTO updateConversation(@PathVariable("id") String id, @RequestBody ConversationDTO conversationDTO) throws NotFoundException {        
        return this.conversationService.updateConversation(id, conversationDTO);
    }
}
