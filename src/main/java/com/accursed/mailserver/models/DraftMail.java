//package com.accursed.mailserver.models;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//
//import java.sql.Timestamp;
//
//@Entity
//public class DraftMail extends Mail {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;
//
//    public DraftMail(String from,
//                     String to,
//                     String subject,
//                     String content,
//                     String state,
//                     int priority,
//                     String senderID,
//                     String receiverID) {
//        super(from, to, subject, content, state, priority, senderID, receiverID);
//    }
//
//    public DraftMail() {
//        super();
//    }
//}
