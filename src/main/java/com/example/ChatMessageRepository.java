package com.example;


import org.springframework.stereotype.Repository;

@Repository
public class ChatMessageRepository {

    public ChatMessage save(ChatMessage chatMessage){
        return chatMessage;
    }
}
