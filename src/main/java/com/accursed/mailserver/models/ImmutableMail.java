package com.accursed.mailserver.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
public class ImmutableMail extends Mail{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    public ImmutableMail(String from,
                         String to,
                         String subject,
                         String content,
//                         Timestamp timestamp,
                         String state,
                         int priority,
                         String senderID,
                         String receiverID) {
        super(from, to, subject, content, /*timestamp,*/ state, priority, senderID, receiverID);
    }

    public ImmutableMail() {

    }
}
