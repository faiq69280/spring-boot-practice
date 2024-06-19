package com.example;

import java.util.List;
import java.util.Optional;

import com.example.exceptions.MessageNotFoundException;
import com.example.exceptions.MessageNotSavedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ChatMessageRepository extends CrudRepository<ChatMessage,Long> {

    public ChatMessage save(ChatMessage chatMessage);

    public List<ChatMessage> findAllBySenderIgnoreCaseAndRecieverIgnoreCase(String sender, String reciever);
}
