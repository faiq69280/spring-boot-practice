package com.example;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping("/chat")
@RestController
public class ChatController {

     @Autowired
     ChatMessageService chatMessageService;

     @PostMapping("/message")
    public ResponseEntity<ChatMessage> addMessage(@RequestBody ChatMessage chatMessage){
          return new ResponseEntity<>(chatMessageService.save(chatMessage)
                  ,HttpStatusCode.valueOf(200));
    }

    @GetMapping("/{with}")
    public ResponseEntity<List<ChatMessage>> getMessages(@PathVariable String with){
         return new ResponseEntity<>(chatMessageService.findAll("jill",with),HttpStatusCode.valueOf(200));
    }

}
