package com.example.chat.controller;

import com.example.chat.dto.ChatMessage;
import com.example.chat.service.ChatService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {
    private final SimpMessagingTemplate messagingTemplate;
    private final ChatService chatService;

    public ChatController(SimpMessagingTemplate messagingTemplate, ChatService chatService) {
        this.messagingTemplate = messagingTemplate;
        this.chatService = chatService;
    }

    // 클라이언트는 /app/chat.send 로 보냄
    @MessageMapping("/chat.send")
    public void send(@Payload ChatMessage message) {
        if (message.getTimestamp() == 0) {
            message.setTimestamp(System.currentTimeMillis());
        }

        // roomId에 맞게 채팅 저장하기
        ChatMessage savedMessage = chatService.save(message);

        // 방별 토픽으로 브로드캐스트
        String topic = "/topic/room." + savedMessage.getRoomId();
        messagingTemplate.convertAndSend(topic, savedMessage);
    }
}
