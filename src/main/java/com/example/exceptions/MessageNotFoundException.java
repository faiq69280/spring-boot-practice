package com.example.exceptions;

public class MessageNotFoundException extends Exception{

    public MessageNotFoundException(String message){
        super(message);
    }

}