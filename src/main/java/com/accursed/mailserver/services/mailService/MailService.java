package com.accursed.mailserver.services.mailService;

import com.accursed.mailserver.services.mailService.creation.builders.DraftBuilder;
import com.accursed.mailserver.services.mailService.creation.builders.ImmutableMailBuilder;
import com.accursed.mailserver.dtos.MailDTO;
import com.accursed.mailserver.dtos.MailMapper;
import com.accursed.mailserver.models.*;
import com.accursed.mailserver.models.*;
import com.accursed.mailserver.database.*;
import com.accursed.mailserver.services.attachmentService.AttachmentService;
import com.accursed.mailserver.services.folderService.FolderService;
import com.accursed.mailserver.services.userService.UserService;
import com.accursed.mailserver.services.mailService.searching.SearchService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class MailService {
    @Autowired
    private MailRepository mailRepo;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private FolderRepository folderRepo;
    @Autowired
    private UserService userService;
    @Autowired
    private SearchService searchService;
    @Autowired
    DataHandler dataGetter;
    @Autowired
    private FolderService folderService;
    @Autowired
    private AttachmentService attachmentService;
    @Autowired
    private AttachmentRepository attachmentRepository;

    private MailMapper mailMapper = Mappers.getMapper(MailMapper.class);


    public ImmutableMail sendMail(MailDTO dto, MultipartFile[] files) throws IOException {
        ImmutableMailBuilder mailBuilder = ImmutableMailBuilder.getInstance();
        mailBuilder.reset();
        ImmutableMail mail =
                (ImmutableMail) mailBuilder
                    .setMailFrom(dto.from)
                    .setMailTo(dto.to)
                    .setDate()
                    .setContent(dto.content)
                    .setPriority(dto.priority)
                    .setSubject(dto.subject)
                    .setIsStarred(dto.isStarred)
                    .setState(dto.state)
                    .getResult();

        for(MultipartFile file: files){
            Attachment attachment = attachmentService.setAttachmentToMail(file, mail);
            attachmentRepository.save(attachment);
        }
        //TODO test mail.getId()
        addToFolder(mail.getId(),folderRepo.findByUserIdAndFolderName(userRepo.findByEmail(dto.to).get(0).getId(),"inbox").getId());
        addToFolder(mail.getId(),folderRepo.findByUserIdAndFolderName(userRepo.findByEmail(dto.from).get(0).getId(),"sent").getId());
        return mail;
    }

    // TODO add to draft folder
    public DraftMail postDraft(MailDTO dto, MultipartFile[] files) throws IOException {
        DraftBuilder draftBuilder = DraftBuilder.getInstance();
        draftBuilder.reset();
        DraftMail mail =
                (DraftMail) draftBuilder.setDate()
                .setMailTo(dto.to)
                .setMailFrom(dto.from)
                .setContent(dto.content)
                .setPriority(dto.priority)
                .setSubject(dto.subject)
                .setIsStarred(dto.isStarred)
                .setState(dto.state)
                .getResult();
        for(MultipartFile file: files){
            Attachment attachment = attachmentService.setAttachmentToMail(file, mail);
            attachmentRepository.save(attachment);
        }
        mailRepo.save(mail);
        addToFolder(mail.getId(),folderRepo.findByUserIdAndFolderName(userRepo.findByEmail(dto.from).get(0).getId(),"draft").getId());
        return mail;
    }

    public void updateDraft(MailDTO dto, MultipartFile[] files) throws IOException {
        Optional<Mail> m = mailRepo.findById(dto.mailId);
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
            addToFolder(m.get().getId(),folderRepo.findByUserIdAndFolderName(userRepo.findByEmail(dto.from).get(0).getId(),"draft").getId());
        }else
            throw new IOException();
    }

    public ImmutableMail sendDraft(MailDTO dto){
        DraftMail draft = (DraftMail) mailRepo.findById(dto.mailId).get();
        ImmutableMailBuilder mailBuilder = ImmutableMailBuilder.getInstance();
        mailBuilder.reset();
        ImmutableMail mail =
                (ImmutableMail) mailBuilder
                .setMailTo(draft.getMailTo())
                .setMailFrom(draft.getMailFrom())
                .setDate()
                .setContent(draft.getContent())
                .setPriority(draft.getPriority())
                .setSubject(draft.getSubject())
                .setIsStarred(draft.getIsStarred())
                .setState(draft.getState())
                .getResult();

        Set<Attachment> attachmentSet = new HashSet<>();
        for(Attachment i : draft.getAttachments()){
            attachmentSet.add(i);
            i.setMail(mail);
            attachmentRepository.save(i);
        }
        mailRepo.save(mail);
        addToFolder(mail.getId(),folderRepo.findByUserIdAndFolderName(userRepo.findByEmail(mail.getMailTo()).get(0).getId(),"inbox").getId());
        addToFolder(mail.getId(),folderRepo.findByUserIdAndFolderName(userRepo.findByEmail(mail.getMailFrom()).get(0).getId(),"sent").getId());
        //TODO change it to delete the mail from the folder
        addToFolder(mail.getId(),folderRepo.findByUserIdAndFolderName(userRepo.findByEmail(mail.getMailFrom()).get(0).getId(),"draft").getId());
//        draft.setFolders(new HashSet<>());
        deleteFromFolder(draft.getId(),folderRepo.findByUserIdAndFolderName(userRepo.findByEmail(mail.getMailFrom()).get(0).getId(),"draft").getId());
        mailRepo.deleteById(draft.getId());
        return mail;
    }

    public List<Mail> searchBySubject(MailDTO mailDTO){
        List<Mail> mails = dataGetter.getMails(mailDTO.userId);
        return searchService.searchBySubject(mails, mailDTO.subject);
    }

    public Optional<Mail> getDraft(MailDTO dto){
        return mailRepo.findById(dto.mailId);
    }

    public void deleteDraft(MailDTO dto){
        mailRepo.deleteById(dto.mailId);
    }

    public Mail getMail(String id) {
        return mailRepo.findById(id).get();
    }

    public void addToFolder(String mailId, String folderId) {
        Mail mail = getMail(mailId);
        Folder folder = folderService.getById(folderId);
        folder.addMail(mail);
        folderService.update(folder);
    }
    public void deleteFromFolder(String mailId, String folderId){
        Mail mail = getMail(mailId);
        Folder folder = folderService.getById(folderId);
        folder.deleteMail(mail);
        folderService.update(folder);
    }

    public void deleteMail(String mailId, String folderId, String userId) {
        addToFolder(mailId, folderRepo.findByUserIdAndFolderName(userId,"trash").getId());
        deleteFromFolder(mailId, folderId);
    }
}
