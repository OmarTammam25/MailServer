package com.accursed.mailserver.services;

import com.accursed.mailserver.dtos.UserDTO;
import com.accursed.mailserver.models.User;
import com.accursed.mailserver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UserService {
    @Autowired
    private UserRepository userRepo;
    public void register(UserDTO userDTO){

    }
    public long login(UserDTO userDTO){

    }
}
