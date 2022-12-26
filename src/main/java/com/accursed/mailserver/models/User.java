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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String email;
    private String password;

    public User() {
    }
    public void get(UserDTO userDTO){
        name = userDTO.name;
        email = userDTO.email;
        password = userDTO.password;
    }
}

