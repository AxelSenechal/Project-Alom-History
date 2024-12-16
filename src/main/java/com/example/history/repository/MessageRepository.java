package com.example.history.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.history.model.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByReceiverId(String receiverId);
    List<Message> findBySenderIdAndReceiverId(String senderId, String receiverId);
@Query("SELECT m FROM Message m WHERE (m.senderId = :user1 AND m.receiverId = :user2) OR (m.senderId = :user2 AND m.receiverId = :user1)")
List<Message> findConversationBetweenUsers(@Param("user1") String user1, @Param("user2") String user2);

}
