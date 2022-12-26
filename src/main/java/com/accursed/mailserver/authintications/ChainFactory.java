package com.accursed.mailserver.authintications;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Configurable
public class ChainFactory {
    public Handler getChain(String chain){
        switch (chain){
            case "registration": return new baseHandler(new ValidEmailString(new UniqueEmail(new HasNumber(new HasSpecialChar(new LongEnough(null))))));
            case "login": return new baseHandler(new EmailExist(new CorrectPassword(null)));
            default: return null;
        }
    }
}
