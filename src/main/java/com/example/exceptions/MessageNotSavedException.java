package com.example.exceptions;

import com.example.ChatMessage;

import java.util.Optional;

public class MessageNotSavedException extends RuntimeException{

    ChatMessage msg;

    public MessageNotSavedException(String msg, ChatMessage messageNotSaved){
        super(msg);
        this.msg = messageNotSaved;
    }

    public ChatMessage getMetaData(){
        return msg;
    }



}
