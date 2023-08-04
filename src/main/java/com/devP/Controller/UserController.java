package com.devP.Controller;


import com.devP.Service.UserService;
import com.devP.VO.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

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



    @RequestMapping(value="/login.do", method= RequestMethod.GET)
    public String loginView(){
        return "login";
    }

    @RequestMapping(value="/login.do", method = RequestMethod.POST)
    public String login(UserVO vo, HttpSession session, HttpServletRequest request){

        UserVO user = userService.getUser(vo);
        if(user != null){
            String saveId = request.getParameter("saveId");
            System.out.println(saveId);
//            session.setAttribute("name",user.getName());
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
    @RequestMapping(value = "/searchPw.do", method = RequestMethod.POST)
    public String searchPw(UserVO vo, HttpServletRequest request,HttpSession session, ModelAndView model) throws Exception {
        String email = request.getParameter("email-id")+"@"+request.getParameter("email");
        vo.setEmail(email);
        System.out.println(email);
        UserVO user = userService.getUserPwByEmail(vo);
        if (user != null) {
            String authKey = mailController.sendCode(user.getEmail());
//            request.setAttribute(authKey, authKey);

            session.setAttribute("userId",user.getId());    // 비밀번호 재설정시 필요
            session.setAttribute("authKey",authKey);
        }
        return "searchLogin";
    }
    @RequestMapping(value = "/checkCode.do", method = RequestMethod.POST)
    public String checkCode(HttpServletRequest request,HttpSession session, Model model){
        String authKey = (String)session.getAttribute("authKey");
        String inputCode = request.getParameter("input-code");
        System.out.println(authKey+" "+inputCode);
        if(authKey.equals(inputCode)){
            model.addAttribute("success","success");
            System.out.println("true");
        } else {
            model.addAttribute("success","false");
            System.out.println("false");

        }
        System.out.println();
        return "searchLogin";
    }
    @RequestMapping(value = "/changePw.do", method = RequestMethod.GET)
    public String changePwView(HttpSession session){
        session.removeAttribute("authKey");
        return "changePw";
    }

    @RequestMapping(value = "/changePw.do", method = RequestMethod.POST)
    public String changePw(UserVO vo, HttpSession session, HttpServletRequest request){
        vo.setId((String)session.getAttribute("userId"));
        System.out.println(vo.getId()+""+vo.getPassword());
        userService.updatePw(vo);
        return "changePw";
    }
    @RequestMapping(value = "/changePwSuccess.do", method = RequestMethod.GET)
    public String changePwSuccessView(){
        return "changePwSuccess";
    }


}