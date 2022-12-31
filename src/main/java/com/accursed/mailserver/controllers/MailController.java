package com.accursed.mailserver.controllers;

import com.accursed.mailserver.dtos.MailDTO;
import com.accursed.mailserver.models.DraftMail;
import com.accursed.mailserver.models.ImmutableMail;
import com.accursed.mailserver.models.Mail;
import com.accursed.mailserver.services.MailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
public class MailController {
    @Autowired
    MailService mailService;

    @PostMapping("/addMail")
    public ResponseEntity<Object> addMail(@RequestParam("mail") String jsonRequest, @RequestParam("file")MultipartFile[] files) {
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

    @PostMapping("/getmail")
    public Mail getMailById(@RequestBody MailDTO mailDTO){
        return mailService.getMailById(mailDTO.id);
    }

    @PostMapping("/draft/post")
    public ResponseEntity<Object> postDraft(@RequestParam("mail") String jsonRequest, @RequestParam("file")MultipartFile[] files){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            MailDTO mailDTO = objectMapper.readValue(jsonRequest, MailDTO.class);
            DraftMail mail =  mailService.postDraft(mailDTO, files);
            URI location= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(mail.getId()).toUri();
            return ResponseEntity.created(location).build();
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * gets draft from database, converts it to an immutable mail, and then deletes draft from database
     */
    @PostMapping("/draft/send")
    public ResponseEntity<Object> sendDraft(@RequestBody MailDTO mailDTO){
        try {
            ImmutableMail mail =  mailService.sendDraft(mailDTO);
            URI location= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(mail.getId()).toUri();
            return ResponseEntity.created(location).build();
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/draft/update")
    public ResponseEntity<Object> UpdateDraft(@RequestParam("mail") String jsonRequest, @RequestParam("file")MultipartFile[] files){
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            MailDTO mailDTO = objectMapper.readValue(jsonRequest, MailDTO.class);

            mailService.updateDraft(mailDTO, files);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/draft/get")
    public ResponseEntity<Object> getDraft(@RequestBody MailDTO mailDTO) {
        Optional<Mail> mail = mailService.getDraft(mailDTO);
        if (mail.isPresent())
            return ResponseEntity.ok(mail);
        else
            return ResponseEntity.badRequest().body("Couldn't find draft with this id");
    }

    @DeleteMapping("/draft/delete")
    public void deleteDraft(@RequestBody MailDTO mailDTO) {
        mailService.deleteDraft(mailDTO);
    }
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
}
