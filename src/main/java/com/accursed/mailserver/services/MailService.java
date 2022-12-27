package com.accursed.mailserver.services;

import com.accursed.mailserver.builders.DraftBuilder;
import com.accursed.mailserver.builders.ImmutableMailBuilder;
import com.accursed.mailserver.builders.MailBuilder;
import com.accursed.mailserver.dtos.MailDTO;
import com.accursed.mailserver.models.DraftMail;
import com.accursed.mailserver.models.ImmutableMail;
import com.accursed.mailserver.repositories.MailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Time;
import java.sql.Timestamp;

@Service
public class MailService {
    @Autowired
    private MailRepository mailRepo;

    // TODO refactor into director class
    public ImmutableMail sendMail(MailDTO dto){
        ImmutableMailBuilder mailBuilder = ImmutableMailBuilder.getInstance();
        mailBuilder.reset();
        mailBuilder.setMailFrom(dto.from);
        mailBuilder.setDate();
        mailBuilder.setContent(dto.content);
        mailBuilder.setMailTo(dto.to);
        mailBuilder.setPriority(dto.priority);
        mailBuilder.setSubject(dto.subject);
        mailBuilder.setIsStarred(dto.isStarred);
        mailBuilder.setState(dto.state);
        mailBuilder.setSenderID("sendIdTest");
        mailBuilder.setReceiverID("receiverIdTest");
        ImmutableMail mail = mailBuilder.getResult();
        mailRepo.save(mail);
        return mail;
    }

    public DraftMail sendDraft(MailDTO dto){
        DraftBuilder draftBuilder = DraftBuilder.getInstance();
        draftBuilder.reset();
        draftBuilder.setMailFrom(dto.from);
        draftBuilder.setDate();
        draftBuilder.setContent(dto.content);
        draftBuilder.setMailTo(dto.to);
        draftBuilder.setPriority(dto.priority);
        draftBuilder.setSubject(dto.subject);
        draftBuilder.setIsStarred(dto.isStarred);
        draftBuilder.setState(dto.state);
        draftBuilder.setSenderID("sendIdTest");
        draftBuilder.setReceiverID("receiverIdTest");
        DraftMail mail = draftBuilder.getResult();
        mailRepo.save(mail);
        return mail;
    }

}
