package com.accursed.mailserver.database;

import com.accursed.mailserver.models.Attachment;
import com.accursed.mailserver.models.Folder;
import com.accursed.mailserver.models.Mail;
import com.accursed.mailserver.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
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
    private AttachmentRepository attachmentRepo;

    @Autowired
    private FolderRepository folderRepo;

//    @Autowired
//    private FolderService folderService;

    //TODO function getMails doesn't work well
//    public List<Mail> getMails(String userID){
//        return (List<Mail>) folderService.getById(userID).getMails();
//    }

    public void saveAttachmentToAttachmentTable(Attachment attachment) {
        attachmentRepo.save(attachment);
    }

    public Mail getMailByMailId(String mailId) {
        return mailRepo.findById(mailId).get();
    }

    public Folder getFolderByFolderId(String folderId){
        return folderRepo.findById(folderId).get();
    }

    public void updateFolder(Folder folder){
        folderRepo.save(folder);
    }

    public User getUserByEmail(String email){
        return userRepo.findByEmail(email).get(0);
    }

    public Folder getFolderByUserIdAndFolderName(String userId, String folderName){
        return folderRepo.findByUserIdAndFolderName(userId, folderName);
    }

    public void saveMailToMailTable(Mail mail){
        mailRepo.save(mail);
    }

    public void deleteAttachmentFromTableByID(String attachmentId){
        attachmentRepo.deleteById(attachmentId);
    }

    public void deleteMailFromTableByID(String mailId){
        mailRepo.deleteById(mailId);
    }

    public List<Folder> getFoldersByName(String name){
        return folderRepo.findByFolderName(name);
    }

    public void deleteMailFromFolder(String mailId, String folderId){
        Mail mail = mailRepo.findById(mailId).get();
        Folder folder = folderRepo.findById(folderId).get();
        folder.deleteMail(mail);
        updateFolder(folder);
    }
    public Optional<User> getUserByUserId (String userId){
        return userRepo.findById(userId);
    }


}
