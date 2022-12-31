package com.accursed.mailserver.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;

import java.sql.Timestamp;
import java.util.Set;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Getter
public abstract class Mail {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    protected String id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id")
    @JsonIgnore
    protected User mailFrom;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id")
    @JsonIgnore
    protected User mailTo;
    @OneToMany(mappedBy = "mail")
    protected Set<Attachment> attachments;
    protected String subject;
    protected String content;
    protected Timestamp timestamp;
    protected String state;
    protected Boolean isStarred;
    protected Integer priority;
    protected String senderEmail;
    protected String receiverEmail;

    public Mail(User mailFrom, User mailTo, String subject, String content,
                Timestamp timestamp, String state, boolean isStarred, int priority,
                Set<Attachment> attachments, String senderEmail, String receiverEmail) {
        this.mailFrom = mailFrom;
        this.mailTo = mailTo;
        this.subject = subject;
        this.content = content;
        this.timestamp = timestamp;
        this.state = state;
        this.isStarred = isStarred;
        this.priority = priority;
        this.attachments = attachments;
        this.senderEmail = senderEmail;
        this.receiverEmail = receiverEmail;
    }

    public Mail() {}

//    public Mail() {
//
//    }

// private String[] attachments;
}
