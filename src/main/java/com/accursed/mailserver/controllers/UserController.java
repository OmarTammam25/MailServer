package com.accursed.mailserver.controllers;

import com.accursed.mailserver.authintications.ChainFactory;
import com.accursed.mailserver.dtos.UserDTO;
import com.accursed.mailserver.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    ChainFactory chainFactory;
    @PostMapping("/register")
    public String register(@RequestBody UserDTO userDTO) throws Exception {
         if(chainFactory.getChain("registration").handle(userDTO)) {
             userService.register(userDTO);
             return "registered";
         }
        return null;
    }
    @PostMapping("/login")
    public long login(@RequestBody UserDTO userDTO) throws Exception {
        if(chainFactory.getChain("login").handle(userDTO)) {
            userService.login(userDTO);
            return userService.login(userDTO);
        }
        return -1;

    }
}
