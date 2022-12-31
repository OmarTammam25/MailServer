package com.accursed.mailserver.models;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Set;

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
                     String receiverID,
                     Set<Attachment> attachment,
                     String senderEmail,
                     String receiverEmail) {
        super(from, to, subject, content, timestamp, state, isStarred, priority, attachment, senderEmail, receiverEmail);
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setMailFrom(User mailFrom) {
        this.mailFrom = mailFrom;
    }

    public void setMailTo(User mailTo) {
        this.mailTo = mailTo;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setStarred(boolean starred) {
        isStarred = starred;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setAttachments(Set<Attachment> attachments){
        this.attachments = attachments;
    }

    public void setSenderEmail(String senderEmail){
        this.senderEmail = senderEmail;
    }

    public void setReceiverEmail(String receiverEmail){
        this.receiverEmail = receiverEmail;
    }

    public DraftMail() {
        super();

    }
}
