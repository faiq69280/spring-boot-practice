package com.example.springboot_app;


import com.example.ChatMessage;
import com.example.ChatMessageRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.sql.Timestamp;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


@DataJpaTest
@RunWith(SpringRunner.class)
public class ChatMessageRepositoryTest {

    @Autowired
    ChatMessageRepository repository;



    Timestamp timestamp = Timestamp.from(Instant.now());

    @Test
    public void testSave(){
        ChatMessage chatMessage = new ChatMessage("faiq","jack",
                "Something",timestamp);
        assertEquals(chatMessage,repository.save(chatMessage));
    }

    @Test
    public void testFindAll() {
        String to = "jacob";
        String from = "jill";

        List<ChatMessage> messagesReturned = Arrays.asList(
                new ChatMessage("jacob","jill","Hi",timestamp),
                new ChatMessage("jacob","jill","Hi there",timestamp)
        );

        repository.saveAll(messagesReturned);

        ChatMessage chatMessage = new ChatMessage("faiq","jack",
                "Something",timestamp);

        repository.save(chatMessage);



        assertEquals(messagesReturned, repository.findAllBySenderIgnoreCaseAndRecieverIgnoreCase(from,to));


    }


}
