package com.accursed.mailserver.controllers;

import com.accursed.mailserver.dtos.AttachmentDTO;
import com.accursed.mailserver.models.Attachment;
import com.accursed.mailserver.database.MailRepository;
import com.accursed.mailserver.services.attachmentService.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
