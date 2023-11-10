package com.devP.Controller;


import com.devP.Service.UserService;
import com.devP.VO.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;

@Controller
//@SessionAttributes("user")
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private MailController mailController;

    @RequestMapping(value="/login/view.do", method= RequestMethod.GET)
    public String loginView(){
        return "login";
    }

    @RequestMapping(value = "/login.do", method = RequestMethod.POST)
    public String login(UserVO vo, HttpSession session, HttpServletRequest request) {
        String saveId = request.getParameter("saveId");
        int result = userService.getUser(vo, saveId, session);
        if (result == 200) {
            return "redirect:/";
        } else {
            return "login";
        }
    }

    @RequestMapping(value = "/logout.do", method = RequestMethod.GET)
    public String logout(HttpSession session) {
    	System.out.println("초기화 전 세션 유저 값" + session.getAttribute("user"));
        if (session.getAttribute("checked") == null) {
            session.invalidate();
        }

        return "redirect:/";
    }


    @RequestMapping(value="/login/search/view.do", method = RequestMethod.GET)
    public String searchLoginView(){ return "searchLogin"; }


    @RequestMapping(value = "/id/search.do", method = RequestMethod.POST)
    public String searchId(UserVO vo, HttpServletRequest request) throws Exception {

        int result = userService.getIdByEmail(vo);

        return "searchLogin";
    }

    @RequestMapping(value = "/pw/search.do", method = RequestMethod.POST)
    public String searchPw(UserVO vo, HttpServletRequest request, HttpSession session) throws Exception {
        int result = userService.findPw(vo, session);

        return "searchLogin";
    }

    @RequestMapping(value = "/code/check.do", method = RequestMethod.POST)
    @ResponseBody
    public String checkCode(HttpServletRequest request, HttpSession session) {
        String inputCode = request.getParameter("input-code");
        int result = userService.checkCode(inputCode, session);
        if (result == 200) {
            System.out.println("true");
            return "success";
        } else {
            System.out.println("false");
            return "false";
        }

    }

    @RequestMapping(value = "/pw/change/view.do", method = RequestMethod.GET)
    public String changePwView(HttpSession session){
        session.removeAttribute("authKey");
        return "changePw";
    }


    @RequestMapping(value = "/pw/change.do", method = RequestMethod.POST)
    public String changePw(UserVO vo, HttpSession session){
        int result = userService.updatePw(vo, session);
        return "changePw";
    }
    @RequestMapping(value = "/pw/change/success/view.do", method = RequestMethod.GET)
    public String changePwSuccessView(){
        return "changePwSuccess";
    }

}
