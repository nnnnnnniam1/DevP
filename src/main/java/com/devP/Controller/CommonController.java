package com.devP.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class CommonController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String mainView(HttpServletRequest request) {

        HttpSession session = request.getSession();

        session.setAttribute("userId", "sejin");
        String userId = (String)session.getAttribute("userId");
        session.setAttribute("title", logincheck(userId));

        return "main";
    }


    public String logincheck(String id) {
        if(id != null){
            String loginSuccess = id +"님, 반갑습니다.";
            return loginSuccess;
        }
        else{
            return "로그인 후 이용하세요.";
        }
    }
}
