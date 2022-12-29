package com.accursed.mailserver.repositories;

import com.accursed.mailserver.models.Mail;
import com.accursed.mailserver.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHandler {

    @Autowired
    private ContactRepository contactRepo;
    @Autowired
    private MailRepository mailRepo;
    @Autowired
    private UserRepository userRepo;

    public List<Mail> getMails(String userID){
        List<Mail> inbox = new ArrayList<>();
        inbox.addAll(userRepo.findById(userID).get().getSentMails());
        inbox.addAll(userRepo.findById(userID).get().getReceivedMails());
        return inbox;
    }
}
