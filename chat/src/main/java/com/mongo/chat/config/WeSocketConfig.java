package com.mongo.chat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * 웹소켓 브로커 STOMP 설정
 * @author hyunj
 *
 */
@Configuration
@EnableWebSocketMessageBroker
public class WeSocketConfig implements WebSocketMessageBrokerConfigurer{

	/**
	 * STOMP 엔드 포인터 설정
	 */
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		/**
		 * 엔드포인트 /ws-hyun 설정 소켓 크로스 도메인 허용 socketJS 사용
		 */
		registry.addEndpoint("/ws-hyun").setAllowedOrigins("*").withSockJS();
	}

	/**
	 * 웹소켓 브로커 (topic), application prefix 설정 (/app)
	 */
	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.enableSimpleBroker("/topic");
		registry.setApplicationDestinationPrefixes("/app");
	}

	
}
