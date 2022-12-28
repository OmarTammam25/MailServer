package com.accursed.mailserver.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id")
    @JsonIgnore
    private User mailFrom;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id")
    @JsonIgnore
    private User mailTo;
    private String subject;
    private String content;
    private Timestamp timestamp;
    private String state;
    private boolean isStarred;
    private int priority;

    public Mail(User mailFrom, User mailTo, String subject, String content, Timestamp timestamp, String state, boolean isStarred, int priority) {
        this.mailFrom = mailFrom;
        this.mailTo = mailTo;
        this.subject = subject;
        this.content = content;
        this.timestamp = timestamp;
        this.state = state;
        this.isStarred = isStarred;
        this.priority = priority;
    }

    public Mail() {

    }

//    public Mail() {
//
//    }

// private String[] attachments;
}
