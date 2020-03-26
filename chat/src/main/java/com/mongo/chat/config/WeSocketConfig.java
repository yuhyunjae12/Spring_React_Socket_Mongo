package com.mongo.chat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * ������ ���Ŀ STOMP ����
 * @author hyunj
 *
 */
@Configuration
@EnableWebSocketMessageBroker
public class WeSocketConfig implements WebSocketMessageBrokerConfigurer{

	/**
	 * STOMP ���� ������ ����
	 */
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		/**
		 * ��������Ʈ /ws-hyun ���� ���� ũ�ν� ������ ��� socketJS ���
		 */
		registry.addEndpoint("/ws-hyun").setAllowedOrigins("*").withSockJS();
	}

	/**
	 * ������ ���Ŀ (topic), application prefix ���� (/app)
	 */
	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.enableSimpleBroker("/topic");
		registry.setApplicationDestinationPrefixes("/app");
	}

	
}
