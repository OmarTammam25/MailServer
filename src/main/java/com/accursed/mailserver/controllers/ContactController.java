package com.accursed.mailserver.controllers;


import com.accursed.mailserver.dtos.ContactDTO;
import com.accursed.mailserver.services.contactService.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ContactController {
    //TODO CRUD operations for contacts
    @Autowired
    ContactService contactService;

    @PostMapping("/addContact")
    public ContactDTO addContact (@RequestBody ContactDTO contactDTO){
        contactService.addContact(contactDTO);
        return contactDTO;
    }

//    @GetMapping("/getContacts")
//    public List<String> getContacts (@RequestBody ContactDTO contactDTO){
//        contactService.getContacts(contactDTO);
//        return ;
//    }

    @PutMapping("/updateContact")
    public ContactDTO updateContact (@RequestBody ContactDTO contactDTO){
        return contactDTO;
    }

    @DeleteMapping("/deleteContact")
    public ContactDTO deleteContact (@RequestBody ContactDTO contactDTO){
        return contactDTO;
    }
}