package com.accursed.mailserver.controllers;

import com.accursed.mailserver.dtos.FolderDTO;
import com.accursed.mailserver.models.Folder;
import com.accursed.mailserver.models.Mail;
import com.accursed.mailserver.services.FolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/folder")
public class FolderController {
    @Autowired
    private FolderService folderService;
    @PostMapping("/create")
    public void createFolder(@RequestBody FolderDTO folderDTO){
        folderService.createFolder(folderDTO);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteFolder(@PathVariable String id){
        folderService.deleteFolder(id);
    }
    @PutMapping("/rename")
    public void renameFolder(@RequestBody FolderDTO folderDTO){
        folderService.renameFolder(folderDTO);
    }
    @GetMapping("/get/{id}")
    public Folder getFolder(@PathVariable String id){
        return folderService.getById(id);
    }

}
