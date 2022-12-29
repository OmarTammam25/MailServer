package com.accursed.mailserver.services.mailService.searching;

import com.accursed.mailserver.models.Mail;
import com.accursed.mailserver.repositories.DataHandler;
import com.accursed.mailserver.services.mailService.searching.Filter.MailCriteria;
import com.accursed.mailserver.services.mailService.searching.Filter.SubjectCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SearchService {
    MailCriteria subjectCriteria = new SubjectCriteria();

    public List<Mail> searchBySubject(List<Mail> data, String searched){
        return subjectCriteria.meet(data, searched);
    }
}
