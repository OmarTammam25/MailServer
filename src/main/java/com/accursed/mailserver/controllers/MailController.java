package com.accursed.mailserver.controllers;

import com.accursed.mailserver.dtos.MailDTO;
import com.accursed.mailserver.models.DraftMail;
import com.accursed.mailserver.models.ImmutableMail;
import com.accursed.mailserver.models.Mail;
import com.accursed.mailserver.services.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class MailController {
    @Autowired
    MailService mailService;

    @PostMapping("/addMail")
    public ResponseEntity<Object> addMail(@RequestBody MailDTO mailDTO) {
        ImmutableMail mail = mailService.sendMail(mailDTO);
        mail.getId();
        mail.getMailTo();
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(mail.getId()).toUri();
        return ResponseEntity.created(location).build();
//        return null;
    }

    @PostMapping("/getmail")
    public Mail getMailById(@RequestBody MailDTO mailDTO){
        return mailService.getMailById(mailDTO.id);
    }

    @PostMapping("/addDraft")
    public ResponseEntity<Object> addDraft(@RequestBody MailDTO mailDTO){
        DraftMail mail =  mailService.sendDraft(mailDTO);
        URI location= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(mail.getId()).toUri();
        return ResponseEntity.created(location).build();
//        return null;
    }

    @PutMapping("/updateDraft")
    public void UpdateDraft(@RequestBody MailDTO mailDTO){
        mailService.updateDraft(mailDTO);
    }

    @GetMapping("/searchBySubject")
    public List<Mail> searchBySubject(@RequestBody MailDTO mailDTO){
        return mailService.searchBySubject(mailDTO);
    }
}
