package com.accursed.mailserver.models;

import com.accursed.mailserver.dtos.UserDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String userName;
    private String email;
    private String password;

    public User() {
    }
    public void get(UserDTO userDTO){
        userName = userDTO.userName;
        email = userDTO.email;
        password = userDTO.password;
    }
}

