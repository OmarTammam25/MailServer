package com.accursed.mailserver.services.contactService;

import com.accursed.mailserver.dtos.ContactDTO;
import com.accursed.mailserver.models.Contact;
import com.accursed.mailserver.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ContactService {

    @Autowired
    private ContactRepository contactRepo;

    public void addContact(ContactDTO contactDTO){
        contactRepo.save(Contact.getInstance(contactDTO));
    }

//    public List<Contact> getContacts(ContactDTO contactDTO){
//
//    }
}
