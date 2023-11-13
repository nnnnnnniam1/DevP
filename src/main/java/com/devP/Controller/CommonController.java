package com.devP.Controller;

import com.devP.Service.IssueService;
import com.devP.Service.ProjectService;
import com.devP.Service.TaskService;
import com.devP.VO.UserVO;

import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class CommonController {

    @Autowired
    private IssueService issueService;
    @Autowired
    private TaskService taskService;

    @Autowired
    private ProjectService projectService;
//    @Autowired
//    private HttpSession session;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String mainView(HttpServletRequest request, Model model, HttpSession session) {
        String userId = null;
        if(session.getAttribute("user") != null) {
        	UserVO userData = (UserVO) session.getAttribute("user");
        	userId = userData.getId();
        }else {
        	return "login";
        }
    	model.addAttribute("menuId", "");
        session.setAttribute("title", logincheck(userId, request));
//        session.removeAttribute("projectName");
        session.removeAttribute("project");

        if(session.getAttribute("user")!=null) {
            issueService.getUserIssueList(model);
            projectService.getProjectList(model, session);
            if(taskService.getUserTaskList(model, session) == 200) {
                return "main";
            }
            return null;
        }
        else return "login";
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
