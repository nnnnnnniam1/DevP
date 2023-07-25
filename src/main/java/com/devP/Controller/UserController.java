package com.devP.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("user")
public class UserController {

    @RequestMapping(value="/login", method= RequestMethod.GET)
    public String loginView(){
        return "login";
    }
}
