package com.accursed.mailserver.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id")
    @JsonIgnore
    private User mailFromUser;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id")
    @JsonIgnore
    private User mailToUser;

    public void setMailFromUser(User mailFromUser) {
        this.mailFromUser = mailFromUser;
    }

    public void setMailToUser(User mailToUser) {
        this.mailToUser = mailToUser;
    }

    public Mail(String mailFrom, String mailTo, String subject, String content, Timestamp timestamp, String state, boolean isStarred, int priority, String senderID, String receiverID,User mailFromUser, User mailToUser) {
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
        this.mailFromUser = mailFromUser;
        this.mailToUser = mailToUser;
    }

    public Mail() {

    }

//    public Mail() {
//
//    }

// private String[] attachments;
}
