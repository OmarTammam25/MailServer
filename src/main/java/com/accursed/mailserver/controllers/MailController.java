package com.accursed.mailserver.controllers;

import com.accursed.mailserver.database.DataHandler;
import com.accursed.mailserver.dtos.MailDTO;
import com.accursed.mailserver.models.ImmutableMail;
import com.accursed.mailserver.models.Mail;
import com.accursed.mailserver.services.folderService.FolderService;
import com.accursed.mailserver.services.mailService.MailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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

    @Autowired
    private DataHandler dataHandler;

    @PostMapping("/send")
    public ResponseEntity<Object> sendMail(@RequestParam("mail") String jsonRequest, @RequestParam(value = "file", required = false)MultipartFile[] files) {
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            MailDTO mailDTO = objectMapper.readValue(jsonRequest, MailDTO.class);
            ImmutableMail mail = mailService.sendMail(mailDTO, files);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(mail.getId()).toUri();
            return ResponseEntity.created(location).build();
        } catch(Exception e) {
          return ResponseEntity.badRequest().build();
        }
    }

    //mail id
    @GetMapping("/get_mail/{id}")
    public Mail getMail(@PathVariable String id){
        return dataHandler.getMailByMailId(id);
    }

    @PutMapping("/add_to_folder")
    public void addToFolder(@RequestBody MailDTO mailDTO){
        folderService.addMailToFolder(mailDTO.mailId, mailDTO.folderId);
    }

    @GetMapping("/get_mails/{id}")
    public Set<Mail> getMailsOfFolder(@PathVariable String id){
        return dataHandler.getFolderByFolderId(id).getMails();
//        return folderService.getById(id).getMails();
    }

    @DeleteMapping("/delete")
    public void deleteMail(@RequestBody MailDTO mailDTO){
        mailService.deleteMailFromFolderAndPutIntoTrash(mailDTO.mailId, mailDTO.folderId, mailDTO.userId);
    }

    @GetMapping("/searchBySubject")
    public Set<Mail> searchBySubject(@RequestBody MailDTO mailDTO){
        return mailService.searchBySubject(mailDTO);
    }

//    @DeleteMapping("/cleartrash")
//    public void clearTrash(){
//        mailService.scheduledTrashDelete();
//    }


//    @PostMapping("/attach")
//    public ResponseEntity<Object> attachFile(@RequestParam("file")MultipartFile file) {
////        String message = "";
////        try {
////            mailService.setAttachments(file);
////            message = "File uploaded successfully: " + file.getOriginalFilename();
////            return ResponseEntity.status(HttpStatus.OK).body(message);
////        }catch (Exception e) {
////            message = "Could not upload file: " + file.getOriginalFilename() + "!";
////            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
////        }
//        return null;
//    }
//    }

//    @PutMapping("/updateDraft")
//    public void UpdateDraft(@RequestBody MailDTO mailDTO){
//        mailService.updateDraft(mailDTO);
//    }


}