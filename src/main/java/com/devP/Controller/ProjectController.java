package com.devP.Controller;

import com.devP.Service.ProjectService;
import com.devP.VO.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @RequestMapping(value="/projectDetail/member.do", method = RequestMethod.GET)
    public String manageMemberView(MemberVO vo, HttpSession session, Model model){
        int result = projectService.showProjectMemberList(vo, model);
        return "member";
    }
}
