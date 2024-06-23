package com.example;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.Instant;
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
    public ResponseEntity<ChatMessageResponseModel> addMessage(@RequestBody ChatMessagePayload chatMessageRequest){


             return new ResponseEntity<>(chatMessageService.save(chatMessageRequest)
                     , HttpStatus.OK);


    }

    @GetMapping
    public ResponseEntity<List<ChatMessageResponseModel>> getMessages(@RequestParam Map<String,String> queryParams){
             return new ResponseEntity<>(chatMessageService.findAll(queryParams.get("sender"), queryParams.get("reciever")), HttpStatus.OK);
    }

}
