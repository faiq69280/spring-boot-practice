package com.example;


import com.example.exceptions.MessageNotFoundException;
import com.example.exceptions.MessageNotSavedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.List.of;

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
         List<ChatMessage> messages = chatMessageRepository.findAllBySenderIgnoreCaseAndRecieverIgnoreCase(from,with);

         if(messages.isEmpty())
             throw new MessageNotFoundException("No message for given recipient found");

         return messages;
    }

}
