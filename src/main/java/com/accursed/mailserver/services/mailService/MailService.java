package com.accursed.mailserver.services.mailService;

import com.accursed.mailserver.services.mailService.drafts.DraftService;
import com.accursed.mailserver.services.mailService.immutableMails.ImmutableMailService;
import com.accursed.mailserver.dtos.*;
import com.accursed.mailserver.models.*;
import com.accursed.mailserver.database.*;
import com.accursed.mailserver.services.folderService.FolderService;
import com.accursed.mailserver.services.mailService.searching.SearchService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class MailService {


    @Autowired
    private SearchService searchService;
    @Autowired
    DataHandler dataHandler;
    @Autowired
    private FolderService folderService;
    @Autowired
    private ImmutableMailService immutableMailService;
    @Autowired
    private DraftService draftService;

    private MailMapper mailMapper = Mappers.getMapper(MailMapper.class);


    public ImmutableMail sendMail(MailDTO dto, MultipartFile[] files) throws IOException {
        return immutableMailService.createImmutableMail(dto, files);
    }

    // TODO add to draft folder
    public DraftMail postDraft(MailDTO dto, MultipartFile[] files) throws IOException {
        return draftService.postDraft(dto, files);
    }

    public void updateDraft(MailDTO dto, MultipartFile[] files) throws IOException {
        draftService.updateDraft(dto, files);

    }

    public ImmutableMail sendDraft(MailDTO dto){
        return draftService.sendDraft(dto);
    }

//    public List<Mail> searchBySubject(MailDTO mailDTO){
//        List<Mail> mails = dataHandler.getMails(mailDTO.userId);
//        return searchService.searchBySubject(mails, mailDTO.subject);
//    }

    public Optional<Mail> getDraft(MailDTO dto){
        return draftService.getDraft(dto.mailId);
    }

    public void deleteDraft(MailDTO dto){
        draftService.deleteDraft(dto.mailId);
    }

    public Mail getMail(String mailId) {
        return dataHandler.getMailByMailId(mailId);
    }

    public void deleteMailFromFolderAndPutIntoTrash(String mailId, String folderId, String userId) {
        Folder userTrashFolder = dataHandler.getFolderByUserIdAndFolderName(userId, "trash");
        Folder folder = dataHandler.getFolderByFolderId(folderId);
        folderService.addMailToFolder(mailId, userTrashFolder.getId());
        folderService.deleteMailFromFolder(mailId, folder.getId());
    }
}
