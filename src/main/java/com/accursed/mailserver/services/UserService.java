package com.accursed.mailserver.services;

import com.accursed.mailserver.authintications.ChainFactory;
import com.accursed.mailserver.authintications.Handler;
import com.accursed.mailserver.dtos.UserDTO;
import com.accursed.mailserver.models.User;
import com.accursed.mailserver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserService {
    @Autowired
    private UserRepository userRepo;
//    @Autowired
//    private ChainFactory chainFactory;
    public void register(UserDTO userDTO) throws Exception {
//        Handler handler = chainFactory.getChain("registration");
//        boolean test = handler.handle(userDTO);
//        if(test){
            userRepo.save(User.getFromDTO(userDTO));
//        }
//        else {
//            throw new Exception("registration failed");
//        }

    }
    public long login(UserDTO userDTO) throws Exception {
//        if(chainFactory.getChain("login").handle(userDTO)){
            return userRepo.findByuserName(userDTO.userName).getId();
//        }
//        else{
//            throw new Exception("login failed");
//        }

    }
    public boolean emailExists(String email){
        return userRepo.findByemail(email)!= null;
    }
    public User getByEmail(String email){
        return userRepo.findByemail(email);
    }
}
