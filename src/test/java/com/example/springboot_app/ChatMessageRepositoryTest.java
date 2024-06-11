package com.example.springboot_app;


import com.example.ChatMessage;
import com.example.ChatMessageRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
                "12-12-2024");

        assertEquals(testMessage,repository.save(testMessage));
    }

    @Test
    public void testFindAll(){
        String to = "jacob";
        String from = "jill";

        List<ChatMessage> messagesReturned = Arrays.asList(
                new ChatMessage("jacob","jill","Hi","12-12-2024"),
                new ChatMessage("jacob","jill","Hi there","13-12-2024")
        );

        when(database.stream()).thenReturn(messagesReturned.stream());


        assertEquals(messagesReturned,repository.findAll(from,to));

    }


}
