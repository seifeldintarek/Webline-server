package com.internship.xchat.message_service.ws;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.internship.xchat.message_service.dto.MessageDTO;
import com.internship.xchat.message_service.dto.WsMessagePayload;
import com.internship.xchat.message_service.entities.Message;
import com.internship.xchat.message_service.enums.MessageType;
import com.internship.xchat.message_service.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
@RequiredArgsConstructor
public class MessageWebSocketHandler extends TextWebSocketHandler {

    private final MessageService messageService;
    private final ObjectMapper objectMapper;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage raw)
            throws Exception {

        WsMessagePayload payload = objectMapper.readValue(
                raw.getPayload(), WsMessagePayload.class
        );

        MessageDTO dto = new MessageDTO();
        dto.setConversationId(payload.getConversationId());
        dto.setSenderId(payload.getSenderId());
        dto.setContentType(payload.getType());


        if (payload.getType() == MessageType.TEXT)
            dto.setContent(payload.getTextContent());
        else
            dto.setAttachment(payload.getAttachment());


        MessageDTO saved = messageService.saveMessage(dto);

        session.sendMessage(new TextMessage(
                objectMapper.writeValueAsString(saved)
        ));
    }
}