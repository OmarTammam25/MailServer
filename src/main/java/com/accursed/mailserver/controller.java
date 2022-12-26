package com.accursed.mailserver;

import com.accursed.mailserver.dtos.MailDTO;
import com.accursed.mailserver.dtos.UserDTO;
import com.accursed.mailserver.models.User;
import com.accursed.mailserver.services.MailService;
import com.accursed.mailserver.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class controller {
    @Autowired
    UserService userService;
    @Autowired
    MailService mailService;
    @PostMapping("/adduser")
    public void addUser(@RequestBody UserDTO userDTO){
        userService.addNewUser(userDTO);
    }
    @GetMapping("/getuser/{id}")
    public Optional<User> getuser(@PathVariable Long id){
        return userService.getByName(id);
    }

    @PostMapping("/addMail")
    public void addMail(){
        mailService.sendMail();
    }
}
