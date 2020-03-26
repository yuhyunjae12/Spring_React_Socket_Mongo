package com.mongo.chat.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mongo.chat.domain.ChatDomain;
import java.lang.String;

/**
 * �޼��� �������丮
 * 
 * @author hyunj
 *
 */
public interface ChatRepository extends MongoRepository<ChatDomain, Long> {
	List<ChatDomain> findByRoomId(String roomid);
}
