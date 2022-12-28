package com.accursed.mailserver.models;

import com.accursed.mailserver.dtos.ContactDTO;
import com.accursed.mailserver.dtos.UserDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "contacts")
@Getter
@Setter
@NoArgsConstructor
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "Name")
    private String name;

    @Column(name = "Account")
    private String account;


    public Contact(String name, String accounts){
        this.name = name;
        this.account = accounts;
    }

    public static Contact getInstance(ContactDTO contactDTO){
        return new Contact(contactDTO.name, contactDTO.email);
    }
}