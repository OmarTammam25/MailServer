package com.accursed.mailserver.authintications;

import com.accursed.mailserver.dtos.UserDTO;
import org.springframework.beans.factory.annotation.Configurable;

@Configurable
public class HasSpecialChar extends baseHandler{
    public HasSpecialChar(Handler nextHandler) {
        super(nextHandler);
    }
    public boolean handle(UserDTO userDTO){
        boolean hasSpecialChar = false;
        for(char i: userDTO.password.toCharArray()){
            hasSpecialChar = Character.isSpaceChar(i);
        }
        if(hasSpecialChar){
            super.handle(userDTO);
        }
        else{
            return false;
        }
        return true;
    }
}
