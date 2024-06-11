package com.example;


import com.example.exceptions.MessageNotFoundException;
import com.example.exceptions.MessageNotSavedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;

@RequestMapping("/chat")
@RestController
public class ChatController {

     @Autowired
     ChatMessageService chatMessageService;

     @Autowired
    Logger logger;

     @PostMapping("/message")
    public ResponseEntity<ChatMessage> addMessage(@RequestBody ChatMessage chatMessage){
         try {
             return new ResponseEntity<>(chatMessageService.save(chatMessage)
                     , HttpStatus.OK);
         }catch(MessageNotSavedException ex){
             logger.log(Level.INFO,ex.getMessage());
             return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
         }
    }

    @GetMapping("/{with}")
    public ResponseEntity<List<ChatMessage>> getMessages(@PathVariable String with){

         try {
             return new ResponseEntity<>(chatMessageService.findAll("jill", with), HttpStatus.OK);
         }
         catch(MessageNotFoundException ex){
             logger.log(Level.INFO,ex.getMessage());
             return new ResponseEntity<>(HttpStatus.NO_CONTENT);
         }
    }

}
