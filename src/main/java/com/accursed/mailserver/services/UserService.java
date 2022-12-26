package com.accursed.mailserver.services;


import com.accursed.mailserver.dtos.UserDTO;
import com.accursed.mailserver.models.User;
import com.accursed.mailserver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserService {
    @Autowired
    private UserRepository userRepo;

    public void register(UserDTO userDTO) throws Exception {
            userRepo.save(User.getFromDTO(userDTO));
    }
    public long login(UserDTO userDTO) throws Exception {
            return userRepo.findByEmail(userDTO.email).get(0).getId();
    }
    public boolean emailExists(String email){
        List<User> users = userRepo.findByEmail(email);
        return !users.isEmpty();
    }
    public List<User> getByEmail(String email){
        return userRepo.findByEmail(email);
    }
}
