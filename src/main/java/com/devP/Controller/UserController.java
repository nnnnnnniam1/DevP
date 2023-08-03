package com.devP.Controller;


import com.devP.Service.UserService;
import com.devP.VO.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@SessionAttributes("user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private MailController mailController;


    @RequestMapping(value="/login.do", method= RequestMethod.GET)
    public String loginView(){
        return "login";
    }

    @RequestMapping(value="/login.do", method = RequestMethod.POST)
    public String login(UserVO vo, HttpSession session, HttpServletRequest request){

        UserVO user = userService.getUser(vo);
        if(user != null){
            session.setAttribute("name",user.getName());
            session.setAttribute("id",user.getUserId());
            return "main";
        } else
            return "login";
    }

    @RequestMapping(value="/logout.do", method = RequestMethod.GET)
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:login.do";
    }
    @RequestMapping(value="/searchLogin.do", method = RequestMethod.GET)
    public String searchLoginView(){ return "searchLogin"; }


    @RequestMapping(value = "/searchId.do", method = RequestMethod.POST)
    public String searchId(UserVO vo, HttpServletRequest request) throws Exception {
        String email = request.getParameter("email-id")+"@"+request.getParameter("email");
        vo.setEmail(email);
        System.out.println(email);
        UserVO user = userService.getUserIdByEmail(vo);
        if(user != null){
            System.out.println(user.getUserId());
            mailController.sendId(user.getUserId(),user.getEmail());
        }
        return "searchLogin";
    }
}