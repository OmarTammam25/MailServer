package com.accursed.mailserver.controllers;


import com.accursed.mailserver.dtos.ContactDTO;
import com.accursed.mailserver.models.Contact;
import com.accursed.mailserver.services.contactService.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/contact")
public class ContactController {
    //TODO CRUD operations for contact
    @Autowired
    ContactService contactService;

    @PostMapping("/add")
    public ContactDTO addContact (@RequestBody ContactDTO contactDTO){
         contactService.addContact(contactDTO);
        return contactDTO;
    }

    @GetMapping("/get")
    public Set<Contact> getContacts(@RequestBody ContactDTO contactDTO){
        return contactService.getContacts(contactDTO);
    }


    @PutMapping("/update")
    public ContactDTO updateContact (@RequestBody ContactDTO contactDTO){
        contactService.updateContact(contactDTO);
        return contactDTO;
    }

    @DeleteMapping("/delete")
    public ContactDTO deleteContact (@RequestBody ContactDTO contactDTO){
        contactService.deleteContact(contactDTO);
        return contactDTO;
    }
}