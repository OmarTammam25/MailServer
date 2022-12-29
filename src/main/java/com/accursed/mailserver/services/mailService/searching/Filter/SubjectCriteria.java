package com.accursed.mailserver.services.mailService.searching.Filter;

import com.accursed.mailserver.models.Mail;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

public class SubjectCriteria implements MailCriteria {

    @Override
    public List<Mail> meet(List<Mail> data, String subject) {
        return data.stream().filter(mail ->mail.getSubject().contains(subject)).collect(toList());
    }
}
