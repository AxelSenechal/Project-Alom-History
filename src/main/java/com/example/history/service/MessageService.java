package com.example.history.service;

import com.example.history.model.Message;
import com.example.history.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private TokenService tokenService;

    public List<Message> findMessagesForToken(String token) {
        // Récupérer le username associé au token
        String username = tokenService.getUsernameFromToken(token);

        // Utiliser le username pour récupérer les messages
        return messageRepository.findByReceiverId(username);
    }
}
