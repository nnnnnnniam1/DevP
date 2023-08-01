package com.devP.Controller;


import com.devP.Service.UserService;
import com.devP.VO.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private MailController mailController;



    @RequestMapping(value="/login.do", method= RequestMethod.GET)
    public String loginView(Model model){
        return "login";
    }

    @RequestMapping(value="/login.do", method = RequestMethod.POST)
    public String login(UserVO vo, HttpSession session, HttpServletRequest request){

        UserVO user = userService.getUser(vo);
        if(user != null){
            String saveId = request.getParameter("saveId");
            System.out.println(saveId);
            session.setAttribute("name",user.getName());
            session.setAttribute("id",user.getId());
//            session.setAttribute("user", user);

            if("on".equals(saveId)){
                session.setAttribute("checked","checked");
            } else {
                session.removeAttribute("checked");
            }

            return "home";
        } else {
            return "login";
        }
    }

    @RequestMapping(value="/logout.do", method = RequestMethod.GET)
    public String logout(HttpSession session){
        if(session.getAttribute("checked") != null){
            System.out.println(session.getAttribute("id")+" "+session.getAttribute("checked"));
            System.out.println("아이디 저장임");
        } else {
            session.invalidate();
            System.out.println("아이디 저장 안됨");
        }
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
            System.out.println(user.getId());
            mailController.sendId(user.getId(),user.getEmail());
        }
        return "searchLogin";
    }
}