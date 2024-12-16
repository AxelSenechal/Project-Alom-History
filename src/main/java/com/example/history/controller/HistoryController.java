package com.example.history.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.history.model.Message;
import com.example.history.repository.MessageRepository;
import com.example.history.service.MessageService;

@RestController
@RequestMapping("/api/history")
public class HistoryController {

    @Autowired
    private MessageRepository messageRepository;
    
    @Autowired
    private MessageService messageService;

    @GetMapping("/messages/token")
    public List<Message> getMessagesForToken(@RequestParam String token) {
        return messageService.findMessagesForToken(token);
    }

    @GetMapping("/messages/{receiverId}")
    public List<Message> getMessages(@PathVariable String receiverId) {
        return messageRepository.findByReceiverId(receiverId);
    }

    @GetMapping("/messages/conversation")
    public List<Message> getConversation(@RequestParam String senderId, @RequestParam String receiverId) {
        return messageRepository.findBySenderIdAndReceiverId(senderId, receiverId);
    }

    @GetMapping("/messages/conversation/bidirectional")
    public List<Message> getBidirectionalConversation(@RequestParam String user1, @RequestParam String user2) {
        return messageRepository.findConversationBetweenUsers(user1, user2);
    }

    // Nouveau endpoint pour sauvegarder un message
    @PostMapping("/messages/store")
    public ResponseEntity<Message> storeMessage(@RequestBody Message message) {
        message.setTimestamp(LocalDateTime.now()); // Définir automatiquement le timestamp
        Message savedMessage = messageRepository.save(message);
        return ResponseEntity.ok(savedMessage);
    }
}
