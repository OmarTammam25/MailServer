package com.accursed.mailserver.services;

import com.accursed.mailserver.dtos.FolderDTO;
import com.accursed.mailserver.dtos.MailMapper;
import com.accursed.mailserver.models.Folder;
import com.accursed.mailserver.models.Mail;
import com.accursed.mailserver.models.User;
import com.accursed.mailserver.repositories.FolderRepository;
import com.accursed.mailserver.repositories.MailRepository;
import com.accursed.mailserver.repositories.UserRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class FolderService {
    @Autowired
    private FolderRepository folderRepo;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private MailRepository mailRepo;
    private MailMapper folderMapper = Mappers.getMapper(MailMapper.class);
    public void addFolder(FolderDTO folderDTO) {
        Folder folder = folderMapper.updateFolderFromDto(folderDTO, new Folder());
        User user = userRepo.findById(folderDTO.userId).get();
        folder.setUser(user);
        folderRepo.save(folder);
    }

    public void addMailToFolder(FolderDTO folderDTO) {
        Folder folder = folderRepo.findByFolderName(folderDTO.folderName);
        Mail mail = mailRepo.findById(folderDTO.mailId).get();
        folder.addMail(mail);
        mail.addFolder(folder);
        folderRepo.save(folder);
    }
    public Set<Mail> getFolderMails(FolderDTO folderDTO){
        Folder folder = folderRepo.findByFolderName(folderDTO.folderName);
        return folder.getMails();
    }
}
