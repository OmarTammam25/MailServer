package com.accursed.mailserver.models;
import com.accursed.mailserver.models.User;
import com.accursed.mailserver.dtos.ContactDTO;
import com.accursed.mailserver.dtos.UserDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id")
    @JsonIgnore
    private User user;

    @Column(name = "Name")
    private String name;

    @Column(name = "Accounts")
    private String accounts;

    public Contact(String name, String accounts){
        this.name = name;
        this.accounts = accounts;
    }

    public static Contact getInstance(ContactDTO contactDTO){
        return new Contact(contactDTO.name, contactDTO.accounts);
    }
}