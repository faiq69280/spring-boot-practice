package com.example;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import java.sql.Timestamp;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
@Entity
@Table(name = "chat_messages"/*, uniqueConstraints = {
        @UniqueConstraint(columnNames = {"reciever","sender"})
}*/)
public class ChatMessage {

    @ManyToOne
    @JoinColumn(name = "reciever", referencedColumnName = "id")
    private User reciever;


    @ManyToOne
    @JoinColumn(name = "sender" , referencedColumnName = "id")
    private User sender;

    @Column(name = "message_body")
    private String messageBody;

    @Column(name = "date")
    private Timestamp date;

    @Id
    @Column(name = "id",length = 36, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;




    public ChatMessage(User to,User from, String messageBody, Timestamp date) {
        this.reciever = to;
        this.sender = from;
        this.messageBody = messageBody;
        this.date = date;
    }

    public ChatMessage(){

    }

    public User getTo() {
        return reciever;
    }

    public void setTo(User to) {
        this.reciever = to;
    }

    public User getFrom() {
        return sender;
    }

    public void setFrom(User from) {
        this.sender = from;
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

    @Override
    public String toString() {
        return "{" +
                "reciever:" + reciever.getName() +
                ", sender:" + sender.getName() +
                ", messageBody:'" + messageBody + '\'' +
                ", date:" + date +
                ", id:" + id +
                '}';
    }
}
