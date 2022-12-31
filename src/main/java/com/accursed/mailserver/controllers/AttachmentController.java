package com.accursed.mailserver.controllers;

import com.accursed.mailserver.dtos.AttachmentDTO;
import com.accursed.mailserver.models.Attachment;
import com.accursed.mailserver.models.ImmutableMail;
import com.accursed.mailserver.repositories.MailRepository;
import com.accursed.mailserver.services.AttachmentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
public class AttachmentController {

    @Autowired
    private AttachmentService attachmentService;
    @Autowired
    private MailRepository mailRepository;

    @GetMapping("/attachment/get")
    public Optional<Attachment> getAttachment(@RequestBody AttachmentDTO attachmentDTO){
        return attachmentService.getAttachment(attachmentDTO.id);
    }

//    @PostMapping("/attachment/post")
//    public void postAttachment(@RequestParam("mail") String jsonRequest, @RequestParam("file")MultipartFile[] files){
//        try{
//            ObjectMapper objectMapper = new ObjectMapper();
//            AttachmentDTO attachmentDTO = objectMapper.readValue(jsonRequest, AttachmentDTO.class);
//            for(MultipartFile file : files) {
//                attachmentService.setAttachmentToMail(files, mailRepository.findById(attachmentDTO.draftId))
//            }
//            ImmutableMail mail = attachmentService.setAttachmentToMail(files, mailRepository.findById(attachmentDTO.draftId));
//            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(mail.getId()).toUri();
//            return ResponseEntity.created(location).build();
//        } catch(Exception e) {
//            return ResponseEntity.badRequest().build();
//        }
//    }
}
