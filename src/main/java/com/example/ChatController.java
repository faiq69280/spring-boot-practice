package com.example;


import com.example.exceptions.ResourceNotFoundException;
import com.example.exceptions.ResourceNotFoundResponse;
import com.example.exceptions.SaveFailureException;
import com.example.exceptions.SaveFailureResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@RequestMapping("/chat")
@RestController
public class ChatController {

     @Autowired
     ChatMessageService chatMessageService;

     @Autowired
    Logger logger;

     @PostMapping("/message")
    public ResponseEntity<?> addMessage(@RequestBody ChatMessagePayload chatMessageRequest){
            String sender = SecurityContextHolder.getContext().getAuthentication().getName();
            chatMessageRequest.setSender(sender);
            return new ResponseEntity<>(chatMessageService.save(chatMessageRequest)
                    , HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getMessages(@RequestParam Map<String,String> queryParams){
             String sender = SecurityContextHolder.getContext().getAuthentication().getName();
             return new ResponseEntity<>(chatMessageService.findAll(sender, queryParams.get("reciever")), HttpStatus.OK);
    }

}
