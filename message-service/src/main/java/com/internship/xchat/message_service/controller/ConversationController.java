package com.internship.xchat.message_service.controller;

import java.util.List;

import com.internship.xchat.common_lib.exception.ResourceNotFoundException;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import com.internship.xchat.message_service.dto.ConversationDTO;
import com.internship.xchat.message_service.enums.ConversationType;
import com.internship.xchat.message_service.service.ConversationService;


@RestController
@RequiredArgsConstructor
@RequestMapping("/conversation")
public class ConversationController {
    private final ConversationService conversationService;

    @GetMapping("/{id}")
    public ConversationDTO getConversationById(@PathVariable("id") String id) throws NotFoundException {
        return this.conversationService.getConversationById(id);
    }

    @GetMapping("/group/{groupId}")
    public ConversationDTO getConversationByGroupId(@PathVariable("groupId") Long groupId) {
        return this.conversationService.getConversationByGroupId(groupId);
    }

    @GetMapping("")
    public ConversationDTO getConversationByParticipantsAndType(
            @RequestParam("participant_ids") List<Long> participants,
            @RequestParam("conversation_type") ConversationType type) {
        return this.conversationService.getConversationByParticipantsAndType(participants, type);
    }

    @PostMapping("")
    public ConversationDTO createConversation(@RequestBody ConversationDTO conversationDTO) {
        System.out.println("Received: " + conversationDTO);
        return this.conversationService.saveConversation(conversationDTO);
    }
    
    @PutMapping("/{id}")
    public ConversationDTO updateConversation(@PathVariable("id") String id, @RequestBody ConversationDTO conversationDTO) throws NotFoundException {        
        return this.conversationService.updateConversation(id, conversationDTO);
    }

    @DeleteMapping("/{convId}")
    public void deleteConversation(@PathVariable("convId") String convId) throws NotFoundException {
        this.conversationService.deleteById(convId);
    }
}
