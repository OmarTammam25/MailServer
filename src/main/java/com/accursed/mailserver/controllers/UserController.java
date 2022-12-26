package com.accursed.mailserver.controllers;

import com.accursed.mailserver.authintications.ChainFactory;
import com.accursed.mailserver.authintications.Handler;
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

    @PostMapping("/register")
    public void register(@RequestBody UserDTO userDTO) throws Exception {
        ChainFactory chainFactory = new ChainFactory();
        Handler handler = chainFactory.getChain("registration");
        boolean test = handler.handle(userDTO);
        userService.register(userDTO);
    }
    @PostMapping("/login")
    public long login(@RequestBody UserDTO userDTO) throws Exception {
        return userService.login(userDTO);
    }
}
