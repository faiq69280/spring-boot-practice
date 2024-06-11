package com.example;

import java.time.LocalDate;

public record ChatMessage(String to, String from, String messageBody,String date) {
}
