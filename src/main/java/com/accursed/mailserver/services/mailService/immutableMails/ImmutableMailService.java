package com.accursed.mailserver.services.mailService.immutableMails;

import com.accursed.mailserver.database.DataHandler;
import com.accursed.mailserver.dtos.MailDTO;
import com.accursed.mailserver.models.Attachment;
import com.accursed.mailserver.models.Folder;
import com.accursed.mailserver.models.ImmutableMail;
import com.accursed.mailserver.services.attachmentService.AttachmentService;
import com.accursed.mailserver.services.folderService.FolderService;
import com.accursed.mailserver.services.mailService.builders.ImmutableMailBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImmutableMailService {
    @Autowired
    private AttachmentService attachmentService;

    @Autowired
    private DataHandler dataHandler;
    @Autowired
    private FolderService folderService;
    public ImmutableMail createImmutableMail(MailDTO dto, MultipartFile[] files) throws IOException {
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
            dataHandler.saveAttachmentToAttachmentTable(attachment);

        }
        //TODO test mail.getId()
        String userId = dataHandler.getUserByEmail(dto.to).getId();
        Folder inboxFolder = dataHandler.getFolderByUserIdAndFolderName(userId, "inbox");
        Folder sentFolder = dataHandler.getFolderByUserIdAndFolderName(userId, "sent");
        folderService.addMailToFolder(mail.getId(), inboxFolder.getId());
        folderService.addMailToFolder(mail.getId(), sentFolder.getId());

        return mail;
    }
}
