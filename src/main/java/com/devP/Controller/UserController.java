package com.devP.Controller;


import com.devP.Service.UserService;
import com.devP.VO.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@SessionAttributes("user")
public class UserController {
    @Autowired
    private UserService userService;



    @RequestMapping(value="/login.do", method= RequestMethod.GET)
    public String loginView(){
        return "login";
    }

    @RequestMapping(value="/login.do", method = RequestMethod.POST)
    public String login(UserVO vo, HttpSession session, HttpServletRequest request){

        UserVO user = userService.getUser(vo);
        if(user != null){
            session.setAttribute("name",user.getName());
            session.setAttribute("id",user.getId());
            System.out.println((String)request.getParameter("saveId"));
            return "main";
        } else
        return "login";
    }

    @RequestMapping(value="/logout.do", method = RequestMethod.GET)
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:login.do";
    }
}
