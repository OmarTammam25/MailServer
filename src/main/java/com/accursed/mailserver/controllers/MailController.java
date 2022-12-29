package com.accursed.mailserver.controllers;

import com.accursed.mailserver.dtos.MailDTO;
import com.accursed.mailserver.models.DraftMail;
import com.accursed.mailserver.models.ImmutableMail;
import com.accursed.mailserver.models.Mail;
import com.accursed.mailserver.services.FolderService;
import com.accursed.mailserver.services.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Set;

@RestController
@RequestMapping("/mail")
public class MailController {
    @Autowired
    MailService mailService;
    @Autowired
    FolderService folderService;

    @PostMapping("/send")
    public ResponseEntity<Object> sendMail(@RequestBody MailDTO mailDTO) {
        ImmutableMail mail = mailService.sendMail(mailDTO);
        mail.getId();
        mail.getMailTo();
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(mail.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
    @PostMapping("save_draft")
    public ResponseEntity<Object> addDraft(@RequestBody MailDTO mailDTO){
        DraftMail mail =  mailService.sendDraft(mailDTO);
        URI location= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(mail.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
    @PutMapping("/update_draft")
    public void UpdateDraft(@RequestBody MailDTO mailDTO){
        mailService.updateDraft(mailDTO);
    }

    @PutMapping("/add_to_folder")
    public void addToFolder(@RequestBody MailDTO mailDTO){
        mailService.addToFolder(mailDTO);
    }
    //mail id
    @GetMapping("/get_mail/{id}")
    public Mail getMail(@PathVariable String id){
        return mailService.getMail(id);
    }
    //folder id
    @GetMapping("/get_mails/{id}")
    public Set<Mail> getMailsOfFolder(@PathVariable String id){
        return folderService.getById(id).getMails();
    }
}
