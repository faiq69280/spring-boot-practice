package com.example.springboot_app;

import com.example.ChatMessage;
import com.example.ChatMessageRepository;
import com.example.ChatMessageService;
import com.example.exceptions.MessageNotFoundException;
import com.example.exceptions.MessageNotSavedException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.time.Instant;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class ChatMessageServiceTest {

    @Mock
    ChatMessageRepository repository;


    @MockBean
    @InjectMocks
    ChatMessageService service;

    @Test
    public void testSaveMessage(){

        ChatMessage testMessage = new ChatMessage("jacob",
                "jill",
                "Hi",
                Timestamp.from(Instant.now()));


        try {
            when(repository.save(testMessage))
                    .thenReturn(testMessage);
            
            assertEquals(testMessage, service.save(testMessage));
        }
        catch(Exception ex){
            assertThat(ex)
                    .isInstanceOf(MessageNotSavedException.class);
        }
    }

    @Test
    public void testFindAll(){
        String to = "jacob";
        String from = "jill";

        Timestamp timeStamp =  Timestamp.from(Instant.now());

        List<ChatMessage> messagesReturned = Arrays.asList(
                new ChatMessage("jacob","jill","Hi",timeStamp),
                new ChatMessage("jacob","jill","Hi there",timeStamp)
        );
        try {
            when(repository.findAllBySenderIgnoreCaseAndRecieverIgnoreCase(from, to)).thenReturn(messagesReturned);

            assertEquals(messagesReturned, service.findAll(from, to));
        }
        catch(Exception e){
            assertThat(e)
                    .isInstanceOf(MessageNotFoundException.class)
                    .hasMessage("Message not found");
        }

    }

}
