package com.example;

import java.sql.Timestamp;

public record ChatMessageResponseModel(String sender,
                                       String reciever,
                                       String messageBody,
                                       Timestamp time) {
}
