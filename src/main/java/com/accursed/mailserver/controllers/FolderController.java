package com.accursed.mailserver.controllers;

import com.accursed.mailserver.dtos.FolderDTO;
import com.accursed.mailserver.models.Mail;
import com.accursed.mailserver.services.FolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class FolderController {
    @Autowired
    private FolderService folderService;

    @PostMapping("/addfolder")
    public void addFolder(@RequestBody FolderDTO folderDTO){
        folderService.addFolder(folderDTO);
    }
    @PostMapping("/addmailtofolder")
    public void addMailToFolder(@RequestBody FolderDTO folderDTO){
        folderService.addMailToFolder(folderDTO);
    }
    @GetMapping("/getmailsfromfolder")
    public Set<Mail> getMailsFromFolder(@RequestBody FolderDTO folderDTO){
        Set<Mail> set = folderService.getFolderMails(folderDTO);
        return set;
    }

}
