package com.example;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChatMessagePayload {

    private String sender;

    private String reciever;
    private String message;

    public ChatMessagePayload(String sender, String reciever, String message) {
        this.sender = sender;
        this.reciever = reciever;
        this.message = message;
    }

    public String sender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String reciever() {
        return reciever;
    }

    public void setReciever(String reciever) {
        this.reciever = reciever;
    }

    public String message() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
