package com.accursed.mailserver.services.mailService.searching.Filter;

import com.accursed.mailserver.models.Mail;

import java.util.Set;

public interface MailCriteria {
    public Set<Mail> meet(Set<Mail> mails, String searched);
}
