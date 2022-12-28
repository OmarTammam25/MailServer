package com.accursed.mailserver.models;

import com.accursed.mailserver.dtos.UserDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String userName;
    @Column(name = "Email")
    private String email;
    private String password;
    @OneToMany(mappedBy = "mailFrom",orphanRemoval = true)
    private List<Mail> sentMails;
    @OneToMany(mappedBy = "mailTo",orphanRemoval = true)
    private List<Mail> receivedMails;
//    private List<Contact> contacts;

    public User(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public static User getFromDTO(UserDTO userDTO){
        return new User(
                userDTO.userName,
                userDTO.email,
                userDTO.password
        );
    }
    //TODO for testing
    public void removeMail (Mail mail){
        sentMails.remove(mail);
        receivedMails.remove(mail);
    }

}

