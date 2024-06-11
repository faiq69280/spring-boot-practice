package com.example;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/chat")
public class ChatController {

     @Autowired
     ChatMessageService chatMessageService;

     @PostMapping("/message")
    public ResponseEntity<ChatMessage> addMessage(@RequestBody ChatMessage chatMessage){
          return new ResponseEntity<>(chatMessageService.save(chatMessage)
                  ,HttpStatusCode.valueOf(200));
    }
}
