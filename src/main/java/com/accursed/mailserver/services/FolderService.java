package com.accursed.mailserver.services;

import com.accursed.mailserver.dtos.FolderDTO;
import com.accursed.mailserver.dtos.MailMapper;
import com.accursed.mailserver.models.Folder;
import com.accursed.mailserver.models.User;
import com.accursed.mailserver.repositories.FolderRepository;
import com.accursed.mailserver.repositories.UserRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class FolderService {
    @Autowired
    private FolderRepository folderRepo;
    @Autowired
    private UserRepository userRepo;

    private MailMapper folderMapper = Mappers.getMapper(MailMapper.class);

//    public void createFolder(FolderDTO folderDTO) {
//        Folder newFolder = folderMapper.updateFolderFromDto(folderDTO,new Folder());
//        User user = userRepo.findById(folderDTO.userId).get();
//        newFolder.setUser(user);
//        folderRepo.save(newFolder);
//    }
    public void createFolder(String userId,String folderName) {
        Folder folder = new Folder(userId,folderName,null,null);
        User user = userRepo.findById(userId).get();
        folder.setUser(user);
        folderRepo.save(folder);
    }

    public void deleteFolder(String id) {
        Folder folder = getById(id);
        folderRepo.delete(folder);
    }

    public void renameFolder(FolderDTO folderDTO) {
        Folder folder = getById(folderDTO.folderId);
        folder.setFolderName(folderDTO.folderName);
        folderRepo.save(folder);
    }

    public Folder getById(String id) {
        return folderRepo.findById(id).get();
    }
    public void update(Folder folder){
        folderRepo.save(folder);
    }

    public void initializeUserFolders(String userId){
        createFolder(userId, "sent");
        createFolder(userId, "inbox");
        createFolder(userId, "draft");
        createFolder(userId, "trash");
    }



//    public void addMailToFolder(FolderDTO folderDTO) {
//        Folder folder = folderRepo.findByFolderName(folderDTO.folderName);
//        Mail mail = mailRepo.findById(folderDTO.mailId).get();
//        folder.addMail(mail);
//        mail.addFolder(folder);
//        folderRepo.save(folder);
//    }

}
