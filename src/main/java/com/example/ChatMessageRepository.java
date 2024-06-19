package com.example;

import java.util.List;

import com.example.exceptions.MessageNotFoundException;
import com.example.exceptions.MessageNotSavedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public class ChatMessageRepository {

    @Autowired
    @Qualifier("ArrayList")
    List<ChatMessage> database;


    public ChatMessage save(ChatMessage chatMessage) throws MessageNotSavedException {

        if(database.contains(chatMessage))
              throw new MessageNotSavedException("Message already exists", chatMessage);

        database.add(chatMessage);
        return chatMessage;
    }

    public List<ChatMessage> findAll(String from,String with) throws MessageNotFoundException{
        List<ChatMessage> result = database.stream().filter(msg ->
                msg.getTo().equalsIgnoreCase(with)
                        && msg.getFrom().equalsIgnoreCase(from)
        ).toList();

        if(result.isEmpty())
            throw new MessageNotFoundException("Message not found");
        return result;
    }




}
