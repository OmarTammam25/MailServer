package com.accursed.mailserver.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;


@MappedSuperclass
@Getter
@Setter
public abstract class Mail {
    private String from;
    private String to;
    private String subject;
    private String content;
//    private Timestamp timestamp;
    private String state;
    private int priority;
    private String senderID;
    private String receiverID;

    public Mail(String from, String to, String subject, String content, /* Timestamp timestamp, */ String state, int priority, String senderID, String receiverID) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.content = content;
//        this.timestamp = timestamp;
        this.state = state;
        this.priority = priority;
        this.senderID = senderID;
        this.receiverID = receiverID;
    }

    public Mail() {

    }

// private String[] attachments;
}
