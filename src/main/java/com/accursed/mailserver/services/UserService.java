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

    public void addNewUser(UserDTO userDTO){
        User user = new User();
        user.get(userDTO);
        userRepo.save(user);
    }
    public Optional<User> getById(String id){
        return userRepo.findById(id);
    }

}
