package com.example.springboot_app;


import com.example.ChatMessage;
import com.example.ChatMessageRepository;
import com.example.exceptions.MessageNotFoundException;
import com.example.exceptions.MessageNotSavedException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.sql.Timestamp;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ChatMessageRepositoryTest {

    @Mock(name = "ArrayList")
    List<ChatMessage> database;

    @InjectMocks
    ChatMessageRepository repository;

    @Test
    public void testSave(){

        ChatMessage testMessage = new ChatMessage("jacob",
                "jill",
                "Hi",
                Timestamp.from(Instant.now()));
        try {
            assertEquals(testMessage, repository.save(testMessage));
        }
        catch(Exception ex){
            assertThat(ex)
                    .isInstanceOf(MessageNotSavedException.class);
        }
    }

    @Test
    public void testFindAll() {
        String to = "jacob";
        String from = "jill";

        Timestamp timeStamp = Timestamp.from(Instant.now());

        List<ChatMessage> messagesReturned = Arrays.asList(
                new ChatMessage("jacob","jill","Hi",timeStamp),
                new ChatMessage("jacob","jill","Hi there",timeStamp)
        );

        when(database.stream()).thenReturn(messagesReturned.stream());

        try {
            assertEquals(messagesReturned, repository.findAll(from, to));
        }catch(Exception e){
            assertThat(e)
                    .isInstanceOf(MessageNotFoundException.class)
                    .hasMessage("Message not found");
        }
    }


}
