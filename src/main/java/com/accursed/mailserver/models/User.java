package com.accursed.mailserver.models;

import com.accursed.mailserver.dtos.UserDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String userName;
    @Column(name = "Email")
    private String email;
    private String password;
    @OneToMany(mappedBy = "mailFromUser")
    private Set<Mail> sentMails = new HashSet<>();
    @OneToMany(mappedBy = "mailToUser")
    private Set<Mail> receivedMails = new HashSet<>();
//    private List<Contact> contacts;

    public User(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }
    public void addSent(Mail mail){
        sentMails.add(mail);
    }
    public void addReceived(Mail mail){
        receivedMails.add(mail);
    }

    public static User getFromDTO(UserDTO userDTO){
        return new User(
                userDTO.userName,
                userDTO.email,
                userDTO.password
        );
    }

}

