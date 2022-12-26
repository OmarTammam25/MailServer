package com.accursed.mailserver.models;

import com.accursed.mailserver.dtos.UserDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private String email;
    private String password;
//    private List<Mail> sentMails;
//    private List<Mail> receivedMails;
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

}

