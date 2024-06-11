package com.example;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatMessageService {

    @Autowired
    ChatMessageRepository chatMessageRepository;

    public ChatMessage save(ChatMessage message)
    {
        return chatMessageRepository.save(message);

    }
    public List<ChatMessage> findAll(String from, String with)
    {
        return chatMessageRepository.findAll(from, with);
    }

}
