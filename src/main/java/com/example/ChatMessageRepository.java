package com.example;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class ChatMessageRepository {

    @Autowired
    @Qualifier("ArrayList")
    List<ChatMessage> database;


    public ChatMessage save(ChatMessage chatMessage){
        database.add(chatMessage);
        return chatMessage;
    }

    public List<ChatMessage> findAll(String from,String with){
        return database.stream().filter(msg ->
                                msg.to().equalsIgnoreCase(with)
                                && msg.from().equalsIgnoreCase(from)
                        ).toList();
    }




}
