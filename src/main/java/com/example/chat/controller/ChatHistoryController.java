package com.example.chat.controller;

import com.example.chat.dto.ChatMessage;
import com.example.chat.service.ChatService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin(origins = "http://localhost:3000")
public class ChatHistoryController {
    private final ChatService chatService;

    public ChatHistoryController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/rooms/{roomId}/messages")
    public List<ChatMessage> getMessages(@PathVariable("roomId") String roomId) {
        return chatService.getMessages(roomId);
    }
}
