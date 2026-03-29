package com.example.chat.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "chat_message")
public class ChatMessageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 50)
    private String roomId;

    @Column(nullable = false, length = 50)
    private String sender;

    @Column(nullable = false, length = 1000)
    private String content;

    @Column(nullable = false)
    private Long timestamp;

    public void setId(Long id) {
        this.id = id;
    }
    protected ChatMessageEntity() {
    }
    public ChatMessageEntity(String roomId, String sender, String content, Long timestamp) {
        this.roomId = roomId;
        this.sender = sender;
        this.content = content;
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public String getRoomId() {
        return roomId;
    }

    public String getSender() {
        return sender;
    }

    public String getContent() {
        return content;
    }

    public Long getTimestamp() {
        return timestamp;
    }
}
