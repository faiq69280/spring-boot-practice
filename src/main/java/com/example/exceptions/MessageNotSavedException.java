package com.example.exceptions;

public class MessageNotSavedException extends Exception{
    public MessageNotSavedException(String msg){
        super(msg);
    }
}
