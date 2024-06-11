package com.example;


import com.example.exceptions.MessageNotFoundException;
import com.example.exceptions.MessageNotSavedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatMessageService {

    @Autowired
    ChatMessageRepository chatMessageRepository;

    public ChatMessage save(ChatMessage message) throws MessageNotSavedException
    {
        return chatMessageRepository.save(message);

    }
    public List<ChatMessage> findAll(String from, String with) throws MessageNotFoundException
    {
        return chatMessageRepository.findAll(from, with);
    }

}
