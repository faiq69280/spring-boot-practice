package com.example;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ChatMessageRepository extends CrudRepository<ChatMessage,Long> {

    public ChatMessage save(ChatMessage chatMessage);

    public List<ChatMessage> findAllBySenderIdAndRecieverId(Long sender, Long reciever);
}
