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
@SessionAttributes("user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private MailController mailController;

    @Autowired
    HttpSession session;


    @RequestMapping(value = "/login.do", method = RequestMethod.GET)
    public String loginView() {
        return "login";
    }

    @RequestMapping(value = "/login.do", method = RequestMethod.POST)
    public String login(UserVO vo, HttpSession session, HttpServletRequest request) {
        String saveId = request.getParameter("saveId");
        int result = userService.getUser(vo, saveId);
        if (result == 200) {
            return "redirect:/";
        } else {
            return "login";
        }
    }
    @RequestMapping(value = "/login/naver/callback.do")
    public String naverLoginCallBack(){
        return "callback";
    }
    @RequestMapping(value="/callback", method=RequestMethod.GET)
    public String callBack(){
        return "callback";
    }

    @RequestMapping(value="naverSave", method=RequestMethod.POST)
    public @ResponseBody String naverSave(@RequestParam("n_age") String n_age, @RequestParam("n_birthday") String n_birthday, @RequestParam("n_email") String n_email, @RequestParam("n_gender") String n_gender, @RequestParam("n_id") String n_id, @RequestParam("n_name") String n_name, @RequestParam("n_nickName") String n_nickName) {
        System.out.println("#############################################");
        System.out.println(n_age);
        System.out.println(n_birthday);
        System.out.println(n_email);
        System.out.println(n_gender);
        System.out.println(n_id);
        System.out.println(n_name);
        System.out.println(n_nickName);
        System.out.println("#############################################");

//        NaverVo naver = new NaverVo();
//        naver.setN_age(n_age);
//        naver.setN_birthday(n_birthday);
//        naver.setN_email(n_email);
//        naver.setN_gender(n_gender);
//        naver.setN_id(n_id);
//        naver.setN_name(n_name);
//        naver.setN_nickName(n_nickName);
//
//
//        System.out.println("zzzzz =" +naver.getN_age());

//        String result = "no";
//
//        if(naver!=null) {
//            result = "ok";
//        }
//
//        return result;
        return "o";

    }


    @RequestMapping(value = "/logout.do", method = RequestMethod.GET)
    public String logout() {

        if (session.getAttribute("checked") == null) {
            session.invalidate();
        }

        return "redirect:/";

    }

    @RequestMapping(value = "/searchLogin.do", method = RequestMethod.GET)
    public String searchLoginView() {
        return "searchLogin";
    }


    @RequestMapping(value = "/searchId.do", method = RequestMethod.POST)
    public String searchId(UserVO vo, HttpServletRequest request) throws Exception {

        int result = userService.getIdByEmail(vo);

        return "searchLogin";
    }

    @RequestMapping(value = "/searchPw.do", method = RequestMethod.POST)
    public String searchPw(UserVO vo, HttpServletRequest request) throws Exception {
        int result = userService.findPw(vo);

        return "searchLogin";
    }

    @RequestMapping(value = "/checkCode.do", method = RequestMethod.POST)
    @ResponseBody
    public String checkCode(HttpServletRequest request, HttpSession session) {
        String inputCode = request.getParameter("input-code");
        int result = userService.checkCode(inputCode);
        if (result == 200) {
            System.out.println("true");
            return "success";
        } else {
            System.out.println("false");
            return "false";
        }

    }

    @RequestMapping(value = "/changePw.do", method = RequestMethod.GET)
    public String changePwView(HttpSession session) {
        session.removeAttribute("authKey");
        return "changePw";
    }

    @RequestMapping(value = "/changePw.do", method = RequestMethod.POST)
    public String changePw(UserVO vo) {
        int result = userService.updatePw(vo);
        return "changePw";
    }

    @RequestMapping(value = "/changePwSuccess.do", method = RequestMethod.GET)
    public String changePwSuccessView() {
        return "changePwSuccess";
    }

}
