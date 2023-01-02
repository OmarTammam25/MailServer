package com.accursed.mailserver.services.mailService.searching.Filter;

import com.accursed.mailserver.models.Contact;

import java.util.Set;

public interface ContactCriteria {
    public Set<Contact> meet(Set<Contact> data, String searched);
}
