package com.accursed.mailserver.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Getter
public abstract class Mail {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String Id;
    private String mailFrom;
    private String mailTo;
    private String subject;
    private String content;
    private Timestamp timestamp;
    private String state;
    private boolean isStarred;
    private int priority;
    private String senderID;
    private String receiverID;

    public Mail(String mailFrom, String mailTo, String subject, String content, Timestamp timestamp, String state, boolean isStarred, int priority, String senderID, String receiverID) {
        this.mailFrom = mailFrom;
        this.mailTo = mailTo;
        this.subject = subject;
        this.content = content;
        this.timestamp = timestamp;
        this.state = state;
        this.isStarred = isStarred;
        this.priority = priority;
        this.senderID = senderID;
        this.receiverID = receiverID;
    }

    public Mail() {

    }

//    public Mail() {
//
//    }

// private String[] attachments;
}
