package com.example;


import com.example.exceptions.ResourceNotFoundException;
import com.example.exceptions.SaveFailureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static java.util.List.of;

@Service
public class ChatMessageService {

    @Autowired
    ChatMessageRepository chatMessageRepository;

    @Autowired
    UserRepository userRepository;

    public ChatMessageResponseModel save(ChatMessagePayload messagePayload) throws SaveFailureException
    {
        ChatMessage chatMessage = new ChatMessage();

        userRepository.findByName(messagePayload.sender()).ifPresentOrElse(
                (user)->{
                    chatMessage.setFrom(user);
                },
                ()-> {
                    throw new SaveFailureException("Couldn't find the sender",
                        (ChatMessagePayload)messagePayload);
                }
        );

        userRepository.findByName(messagePayload.reciever()).ifPresentOrElse(
                (user)->{
                    chatMessage.setTo(user);
                },
                ()->{
                    throw new SaveFailureException("Couldn't find the reciever",
                            (ChatMessagePayload)messagePayload);
                }
        );

        chatMessage.setDate(Timestamp.from(Instant.now()));
        chatMessage.setMessageBody(messagePayload.message());

        ChatMessage messageSaved = Optional.ofNullable(chatMessageRepository.save(chatMessage)).orElseGet(()->{
                            throw new SaveFailureException(
                                       "Returned NULL while saving",
                                        chatMessage);
                        });

        return new ChatMessageResponseModel(messageSaved.getFrom().getName(),
                messageSaved.getTo().getName(),
                messageSaved.getMessageBody(),
                messageSaved.getDate());
    }
    public List<ChatMessageResponseModel> findAll(String from, String with) throws ResourceNotFoundException
    {



        Long idSender = userRepository.findByName(from).orElseGet(()->{
             throw new ResourceNotFoundException("sender : %s doesn't exist"
                     .formatted(from));
         }).getId();

        Long idReciever = userRepository.findByName(with).orElseGet(()->{
            throw new ResourceNotFoundException("reciever : %s doesn't exist"
                    .formatted(with));
        }).getId();

         List<ChatMessage> messages = chatMessageRepository.findAllBySenderIdAndRecieverId(idSender,idReciever);

         if(messages.isEmpty())
             throw new ResourceNotFoundException("No message for given recipient found");

         return messages.stream().map((msg)-> new ChatMessageResponseModel(
                 msg.getFrom().getName(),
                 msg.getTo().getName(),
                 msg.getMessageBody(),
                 msg.getDate()
         )).toList();
    }

}
