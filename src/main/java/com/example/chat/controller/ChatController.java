package com.example.chat.controller;

import com.example.chat.dto.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {
    private final SimpMessagingTemplate messagingTemplate;

    public ChatController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    // 클라이언트는 /app/chat.send 로 보냄
    @MessageMapping("/chat.send")
    public void send(@Payload ChatMessage message) {
        if (message.getTimestamp() == 0) {
            message.setTimestamp(System.currentTimeMillis());
        }

        // 방별 토픽으로 브로드캐스트
        String topic = "/topic/room." + message.getRoomId();
        messagingTemplate.convertAndSend(topic, message);
    }
}
