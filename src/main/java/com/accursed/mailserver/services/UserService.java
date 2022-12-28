package com.accursed.mailserver.services;


import com.accursed.mailserver.dtos.UserDTO;
import com.accursed.mailserver.models.User;
import com.accursed.mailserver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class UserService {
    @Autowired
    private UserRepository userRepo;

    public void register(UserDTO userDTO) throws Exception {
            userRepo.save(User.getFromDTO(userDTO));
    }
    public UserDTO login(UserDTO userDTO) throws Exception {
            userDTO.id = userRepo.findByEmail(userDTO.email).get(0).getId();
            return userDTO;
    }
    public boolean emailExists(String email){
        List<User> users = userRepo.findByEmail(email);
        return !users.isEmpty();
    }
    public List<User> getByEmail(String email){
        return userRepo.findByEmail(email);
    }
    public User getById(long id){
        return (User) userRepo.findById(id).get();
    }
}
