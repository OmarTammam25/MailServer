package com.accursed.mailserver.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Setter
public class DraftMail extends Mail {

    public DraftMail(User from,
                     User to,
                     String subject,
                     String content,
                     Timestamp timestamp,
                     String state,
                     boolean isStarred,
                     int priority,
                     String senderID,
                     String receiverID) {
        super(from, to, subject, content, timestamp, state, isStarred, priority);
    }

    public DraftMail() {
        super();
    }
}
