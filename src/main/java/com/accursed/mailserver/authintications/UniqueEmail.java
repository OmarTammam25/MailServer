package com.accursed.mailserver.authintications;

import com.accursed.mailserver.dtos.UserDTO;
import com.accursed.mailserver.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

@Configurable
public class UniqueEmail extends baseHandler{

    private UserService userService;
    public UniqueEmail(Handler nextHandler) {
        super(nextHandler);
    }
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public boolean handle(UserDTO userDTO){
        if(!userService.emailExists(userDTO.email)){
            super.handle(userDTO);
        }
        else{
            return false;
        }
        return true;
    }
}
