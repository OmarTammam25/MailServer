package com.accursed.mailserver.services.contactService;

import com.accursed.mailserver.dtos.ContactDTO;

import com.accursed.mailserver.dtos.MailMapper;
import com.accursed.mailserver.models.Contact;

import com.accursed.mailserver.models.User;
import com.accursed.mailserver.database.ContactRepository;
import com.accursed.mailserver.database.UserRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class ContactService {

    @Autowired
    private ContactRepository contactRepo;
    @Autowired
    private UserRepository userRepo;

    private MailMapper mailMapper = Mappers.getMapper(MailMapper.class);

    public void addContact(ContactDTO contactDTO){
        Contact contact = Contact.getInstance(contactDTO);
        contact.setUser(userRepo.findById(contactDTO.userId).get());
        contactRepo.save(contact);
        contactDTO.id = contact.getId();
    }

    public Set<Contact> getContacts(ContactDTO contactDTO){
        User user = userRepo.findById(contactDTO.userId).get();
        return user.getContacts();
    }

    public void updateContact(ContactDTO contactDTO) {
        Optional<Contact> m = contactRepo.findById(contactDTO.id);
        if(m.isPresent()) {
            mailMapper.updateContactFromDto(contactDTO, (Contact) m.get());
            contactRepo.save(m.get());
        }
    }

    public void deleteContact(ContactDTO contactDTO){
        contactRepo.deleteById(contactDTO.id);
    }

}
