package com.example.chat.service;

import com.example.chat.dto.ChatMessage;
import com.example.chat.entity.ChatMessageEntity;
import com.example.chat.repository.ChatMessageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ChatService {
    private final ChatMessageRepository chatMessageRepository;


    public ChatService(ChatMessageRepository chatMessageRepository) {
        this.chatMessageRepository = chatMessageRepository;
    }

    public ChatMessage save(ChatMessage message) {
        long timestamp = message.getTimestamp() == 0
                ? System.currentTimeMillis()
                : message.getTimestamp();

        message.setTimestamp(timestamp);

        ChatMessageEntity entity = new ChatMessageEntity(
                message.getRoomId(),
                message.getSender(),
                message.getContent(),
                timestamp
        );

        chatMessageRepository.save(entity);
        return message;
    }

    @Transactional(readOnly = true)
    public List<ChatMessage> getMessages(String roomId) {
        return chatMessageRepository.findByRoomIdOOrderByTimestampAsc(roomId)
                .stream()
                .map(entity -> new ChatMessage(
                        entity.getRoomId(),
                        entity.getSender(),
                        entity.getContent(),
                        entity.getTimestamp()
                ))
                .toList();
    }
}
