package com.accursed.mailserver.services;

import com.accursed.mailserver.models.ImmutableMail;
import com.accursed.mailserver.repositories.MailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;

@RestController
public class MailService {
    @Autowired
    private MailRepository mailRepo;

    public void sendMail(){
        ImmutableMail obj = new ImmutableMail("asd", "damn", "I am sad", "Hey man im sad", "Immutable", 4, "asds", "d123d");
        mailRepo.save(obj);
    }
}
