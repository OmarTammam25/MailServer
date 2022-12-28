package com.accursed.mailserver.dtos;

import com.accursed.mailserver.models.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.ManyToOne;

import java.sql.Timestamp;

public class MailDTO {
    public String from;
    public String to;
    public String subject;
    public String content;
    public Timestamp timestamp;
    public String state;
    public boolean isStarred;
    public int priority;
    public String senderID;
    public String receiverID;

    public User mailFromUser;
    public User mailToUser;
}
