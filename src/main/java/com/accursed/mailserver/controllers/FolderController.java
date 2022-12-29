package com.accursed.mailserver.controllers;

import com.accursed.mailserver.dtos.FolderDTO;
import com.accursed.mailserver.models.Folder;
import com.accursed.mailserver.models.Mail;
import com.accursed.mailserver.services.FolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController("folder")
public class FolderController {
    @Autowired
    private FolderService folderService;
    @PostMapping("/create")
    public void createFolder(@RequestBody FolderDTO folderDTO){
        folderService.createFolder(folderDTO);
    }
    @DeleteMapping("/delete")
    public void deleteFolder(@RequestBody FolderDTO folderDTO){
        folderService.deleteFolder(folderDTO);
    }
    @PutMapping("/rename")
    public void renameFolder(@RequestBody FolderDTO folderDTO){
        folderService.renameFolder(folderDTO);
    }
    @GetMapping("/get")
    public Folder getFolder(@RequestBody FolderDTO folderDTO){
        return folderService.getById(folderDTO);
    }

//
//    @PostMapping("/addfolder")
//    public void addFolder(@RequestBody FolderDTO folderDTO){
//        folderService.addFolder(folderDTO);
//    }
//    @PostMapping("/addmailtofolder")
//    public void addMailToFolder(@RequestBody FolderDTO folderDTO){
//        folderService.addMailToFolder(folderDTO);
//    }
//    @GetMapping("/getmailsfromfolder")
//    public Set<Mail> getMailsFromFolder(@RequestBody FolderDTO folderDTO){
//        Set<Mail> set = folderService.getFolderMails(folderDTO);
//        return set;
//    }

}
