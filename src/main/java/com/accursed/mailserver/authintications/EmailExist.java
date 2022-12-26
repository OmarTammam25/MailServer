package com.accursed.mailserver.authintications;

import com.accursed.mailserver.dtos.UserDTO;
import com.accursed.mailserver.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

@Configurable
public class EmailExist extends baseHandler{
    @Autowired
    UserService userService;
    public EmailExist(Handler nextHandler) {
        super(nextHandler);
    }
    public boolean handle(UserDTO userDTO){

        if(userService.emailExists(userDTO.email)){
            super.handle(userDTO);
        }
        else{
            return false;
        }
        return true;
    }
}
