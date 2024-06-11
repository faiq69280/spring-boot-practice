package com.example;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatMessageService {

    @Autowired
    ChatMessageRepository chatMessageRepository;

    public ChatMessage save(ChatMessage message){
        return chatMessageRepository.save(message);
    }

}
