package com.accursed.mailserver.models;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Mail {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    protected String id;
    protected String mailFrom;
    protected String mailTo;
    protected String subject;
    protected String content;
    protected Timestamp timestamp;
    protected String state;
    protected boolean isStarred;
    protected int priority;
    protected String senderID;
    protected String receiverID;

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

    public String getId() {
        return id;
    }

    public String getMailFrom() {
        return mailFrom;
    }

    public String getMailTo() {
        return mailTo;
    }

    public String getSubject() {
        return subject;
    }

    public String getContent() {
        return content;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public String getState() {
        return state;
    }

    public boolean isStarred() {
        return isStarred;
    }

    public int getPriority() {
        return priority;
    }

    public String getSenderID() {
        return senderID;
    }

    public String getReceiverID() {
        return receiverID;
    }

    public Mail() {

    }

//    public Mail() {
//
//    }

// private String[] attachments;
}
