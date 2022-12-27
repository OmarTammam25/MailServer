package com.accursed.mailserver.services;

import com.accursed.mailserver.builders.DraftBuilder;
import com.accursed.mailserver.builders.ImmutableMailBuilder;
import com.accursed.mailserver.builders.MailBuilder;
import com.accursed.mailserver.dtos.MailDTO;
import com.accursed.mailserver.dtos.MailMapper;
import com.accursed.mailserver.models.DraftMail;
import com.accursed.mailserver.models.ImmutableMail;
import com.accursed.mailserver.models.Mail;
import com.accursed.mailserver.models.User;
import com.accursed.mailserver.repositories.MailRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Optional;

@Service
public class MailService {
    @Autowired
    private MailRepository mailRepo;
    @Autowired
    private UserService userService;

    private MailMapper mailMapper = Mappers.getMapper(MailMapper.class);

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
        mail.getMailTo();
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
        // TODO request sender and receiver id from database and put them here
        draftBuilder.setSenderID("sendIdTest");
        draftBuilder.setReceiverID("receiverIdTest");
        DraftMail mail = draftBuilder.getResult();
        mailRepo.save(mail);
        return mail;
    }

    // TODO test this when you can find by id
    public void updateDraft(MailDTO dto) {
        Optional<Mail> m = mailRepo.findById(dto.id);
        if(m.isPresent()) {
            mailMapper.updateMailFromDto(dto, (DraftMail) m.get());
            mailRepo.save(m.get());
        }
    }

}
