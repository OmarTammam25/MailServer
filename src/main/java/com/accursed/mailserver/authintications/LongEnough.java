package com.accursed.mailserver.authintications;

import com.accursed.mailserver.dtos.UserDTO;
import org.springframework.beans.factory.annotation.Configurable;

@Configurable
public class LongEnough extends baseHandler{
    public LongEnough(Handler nextHandler) {
        super(nextHandler);
    }
    public boolean handle(UserDTO userDTO){
        if(userDTO.password.length()>=8){
            super.handle(userDTO);
        }
        else{
            return false;
        }
        return true;
    }
}
