package com.example.chat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // React(3000)에서 접근 가능하게 CORS 허용
        registry.addEndpoint("/ws")
                .setAllowedOriginPatterns("http://localhost:3000")
                // SockJS를 쓰면 브라우저/환경에 따라 폴백 가능
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 클라이언트가 메시지 발행할 때 prefix
        registry.setApplicationDestinationPrefixes("/app");

        // 서버 -> 클라이언트 브로드캐스트 토픽 prefix
        registry.enableSimpleBroker("/topic");
    }
}
