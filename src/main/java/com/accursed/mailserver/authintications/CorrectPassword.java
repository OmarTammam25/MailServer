package com.accursed.mailserver.authintications;

import com.accursed.mailserver.dtos.UserDTO;
import com.accursed.mailserver.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

@Configurable
public class CorrectPassword extends baseHandler{
    @Autowired
    private UserService userService;

    public CorrectPassword(Handler nextHandler) {
        super(nextHandler);
    }
    public boolean handle(UserDTO userDTO){
        if(userService.getByEmail(userDTO.email).getPassword().equals(userDTO.password)){
            super.handle(userDTO);
        }
        else{
            return false;
        }
        return true;
    }
}
