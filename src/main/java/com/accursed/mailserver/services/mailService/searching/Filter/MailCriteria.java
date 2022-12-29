package com.accursed.mailserver.services.mailService.searching.Filter;

import com.accursed.mailserver.models.Mail;

import java.util.List;
import java.util.Optional;

public interface MailCriteria {
    public List<Mail> meet(List<Mail> data, String searched);
}
