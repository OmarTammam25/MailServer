package com.accursed.mailserver.services;

import com.accursed.mailserver.builders.DraftBuilder;
import com.accursed.mailserver.builders.ImmutableMailBuilder;
import com.accursed.mailserver.dtos.MailDTO;
import com.accursed.mailserver.dtos.MailMapper;
import com.accursed.mailserver.models.*;
import com.accursed.mailserver.repositories.AttachmentRepository;
import com.accursed.mailserver.repositories.MailRepository;
import com.accursed.mailserver.repositories.UserRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class MailService {
    @Autowired
    private MailRepository mailRepo;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private UserService userService;
    @Autowired
    private AttachmentService attachmentService;

    @Autowired
    private AttachmentRepository attachmentRepository;

    private MailMapper mailMapper = Mappers.getMapper(MailMapper.class);

    // TODO refactor into director class
    public ImmutableMail sendMail(MailDTO dto, MultipartFile[] files) throws IOException {
        ImmutableMailBuilder mailBuilder = ImmutableMailBuilder.getInstance();
        mailBuilder.reset();
        ImmutableMail mail =
                (ImmutableMail) mailBuilder
                    .setMailFrom(userRepo.findByEmail(dto.from).get(0))
                    .setMailTo(userRepo.findByEmail(dto.to).get(0))
                    .setDate()
                    .setContent(dto.content)
                    .setPriority(dto.priority)
                    .setSubject(dto.subject)
                    .setIsStarred(dto.isStarred)
                    .setState(dto.state)
                    .setSenderEmail(dto.from)
                    .setReceiverEmail(dto.to)
                    .getResult();

        for(MultipartFile file: files){
            Attachment attachment = attachmentService.setAttachmentToMail(file, mail);
            attachmentRepository.save(attachment);
        }
        return mail;
    }


    public DraftMail postDraft(MailDTO dto, MultipartFile[] files) throws IOException {
        DraftBuilder draftBuilder = DraftBuilder.getInstance();
        draftBuilder.reset();
        DraftMail mail =
                (DraftMail) draftBuilder.setDate()
                .setContent(dto.content)
                .setPriority(dto.priority)
                .setSubject(dto.subject)
                .setIsStarred(dto.isStarred)
                .setState(dto.state)
                .setSenderEmail(dto.from)
                .setReceiverEmail(dto.to)
                .getResult();

        for(MultipartFile file: files){
            Attachment attachment = attachmentService.setAttachmentToMail(file, mail);
            attachmentRepository.save(attachment);
        }
        mailRepo.save(mail);
        return mail;
    }

    public void updateDraft(MailDTO dto, MultipartFile[] files) throws IOException {
        Optional<Mail> m = mailRepo.findById(dto.id);
        if(m.isPresent()) {
            for(Attachment i : m.get().getAttachments())
                attachmentRepository.deleteById(i.getId());
            ((DraftMail)m.get()).setAttachments(new HashSet<>());

            mailMapper.updateMailFromDto(dto, (DraftMail) m.get());
            Set<Attachment> attachmentSet = new HashSet<>();
            for(MultipartFile file: files){
                Attachment attachment = attachmentService.setAttachmentToMail(file, m.get());
                attachmentSet.add(attachment);
                attachmentRepository.save(attachment);
            }
            ((DraftMail) m.get()).setAttachments(attachmentSet);
            mailRepo.save(m.get());
        }else
            throw new IOException();
    }

    public ImmutableMail sendDraft(MailDTO dto){
        DraftMail draft = (DraftMail) mailRepo.findById(dto.id).get();
        ImmutableMailBuilder mailBuilder = ImmutableMailBuilder.getInstance();
        mailBuilder.reset();
        ImmutableMail mail =
                (ImmutableMail) mailBuilder.setMailFrom(userRepo.findByEmail(draft.getSenderEmail()).get(0))
                .setMailTo(userRepo.findByEmail(draft.getReceiverEmail()).get(0))
                .setDate()
                .setContent(draft.getContent())
                .setPriority(draft.getPriority())
                .setSubject(draft.getSubject())
                .setIsStarred(draft.getIsStarred())
                .setState(draft.getState())
                .setSenderEmail(draft.getSenderEmail())
                .setReceiverEmail(draft.getReceiverEmail())
                .getResult();

        Set<Attachment> attachmentSet = new HashSet<>();
        for(Attachment i : draft.getAttachments()){
            attachmentSet.add(i);
            i.setMail(mail);
            attachmentRepository.save(i);
        }
        mailRepo.save(mail);
        mailRepo.deleteById(draft.getId());
        return mail;
    }

    public Optional<Mail> getDraft(MailDTO dto){
        return mailRepo.findById(dto.id);
    }

    public void deleteDraft(MailDTO dto){
        mailRepo.deleteById(dto.id);
    }
    //TODO :This for testing you can remove it and do it better
    public Mail getMailById(String id) {
        return mailRepo.findById(id).get();
    }


}
