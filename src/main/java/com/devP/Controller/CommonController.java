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

        String userId = (String)session.getAttribute("id");
        session.setAttribute("title", logincheck(userId, request));

        return "main";
    }


    public String logincheck(String id, HttpServletRequest request) {

        HttpSession session = request.getSession();

        if(id != null){
            String loginSuccess = id +"님, 반갑습니다.";
            session.setAttribute("login", "로그아웃");
            return loginSuccess;
        }
        else{
            session.setAttribute("login", "로그인");
            return "로그인 후 이용하세요.";
        }
    }
}
