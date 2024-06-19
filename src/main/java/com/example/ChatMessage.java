package com.example;

import jakarta.persistence.*;
import java.sql.Timestamp;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
@Entity
@Table(name = "chat_messages")
public class ChatMessage {

    @Column(name = "reciever")
    private String to;

    @Column(name = "sender")
    private String from;

    @Column(name = "message_body")
    private String messageBody;

    @Column(name = "date")
    private Timestamp date;

    @Id
    @Column(name = "id",length = 36, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public ChatMessage(String to, String from, String messageBody, Timestamp date) {
        this.to = to;
        this.from = from;
        this.messageBody = messageBody;
        this.date = date;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
