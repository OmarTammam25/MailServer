package com.accursed.mailserver.database;

import com.accursed.mailserver.models.Mail;
import com.accursed.mailserver.services.folderService.FolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataHandler {

    @Autowired
    private ContactRepository contactRepo;
    @Autowired
    private MailRepository mailRepo;
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private FolderRepository folderRepo;

    @Autowired
    private FolderService folderService;

    public List<Mail> getMails(String userID){
        return (List<Mail>) folderService.getById(userID).getMails();
    }
}
