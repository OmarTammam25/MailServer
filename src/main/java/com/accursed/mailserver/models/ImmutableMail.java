package com.accursed.mailserver.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
public class ImmutableMail extends Mail {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;
    public ImmutableMail(String mailFrom,
                         String mailTo,
                         String subject,
                         String content,
                         Timestamp timestamp,
                         String state,
                         boolean isStarred,
                         int priority,
                         String senderID,
                         String receiverID,
                         User mailFromUser,
                         User mailToUser ) {
        super(mailFrom, mailTo, subject, content, timestamp, state, isStarred, priority, senderID, receiverID, mailFromUser, mailToUser);
    }

    public ImmutableMail() {
        super();
    }
}
