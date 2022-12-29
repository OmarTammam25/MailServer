package com.accursed.mailserver.services.mailService.searching.Filter;

import com.accursed.mailserver.models.Mail;

import java.util.List;

public class ReceiverCriteria implements MailCriteria{
    @Override
    public List<Mail> meet(List<Mail> data, String Reci) {
        return null;
    }
}
