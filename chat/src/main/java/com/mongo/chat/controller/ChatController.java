package com.mongo.chat.controller;

import java.util.Calendar;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.mongo.chat.domain.ChatDomain;
import com.mongo.chat.repository.ChatRepository;

/**
 * 채팅 컨트롤러
 * 
 * @author hyunj
 *
 */
@RestController
@CrossOrigin
public class ChatController {

	private final ChatRepository repo;
	private final SimpMessageSendingOperations template;

	public ChatController(ChatRepository repo, SimpMessageSendingOperations template) {
		this.repo = repo;
		this.template = template;
	}

	@GetMapping( value = "/messages/{roomId}" )
	public ResponseEntity<?> getMessageList(@PathVariable String roomId){
		return new ResponseEntity<>(repo.findByRoomId(roomId) ,HttpStatus.OK);
	}
	
	@MessageMapping( value = "/send" )
	public void saveMessage(@Payload ChatDomain payload) {
		Date messageTime = Calendar.getInstance().getTime();
		
		ChatDomain domain = ChatDomain.builder()
			.userName(payload.getUserName())
			.message(payload.getMessage())
			.messageTime(messageTime)
			.roomId(payload.getRoomId())
			.build();
		
		repo.save(domain);
		template.convertAndSend("/topic/chat/" + domain.getRoomId(), domain);
	}
	
}
