package com.mongo.chat.domain;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 채팅 도메인
 * @author hyunj
 *
 */
@Document(value = "messageslog")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatDomain {
	
	@Id
	private String id;
	
	private String userName;
	
	private String message;
	
	private Date messageTime;
	
	private String roomId;
}
