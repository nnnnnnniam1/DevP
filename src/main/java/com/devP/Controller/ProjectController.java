package com.devP.Controller;

import com.devP.Service.IssueService;
import com.devP.Service.MailService;
import com.devP.Service.ProjectService;
import com.devP.VO.ProjectVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static java.lang.System.out;

@Controller
@SessionAttributes("project")
@RequestMapping("project")
public class ProjectController {
	

		@Autowired
		private IssueService issueService;
		
        @Autowired
        private ProjectService projectService;

        @Autowired
        private MailController mailController;

        @Autowired
        private HttpSession session;

        //프로젝트 추가 화면
        @RequestMapping(value = "/insert.do", method = RequestMethod.GET)
        public String insertProjectView() {
                if(projectService.insertProjectView() == 200) {
                        return "insertProject";
                }else if(projectService.insertProjectView() == 405){
                        return "redirect:/login.do";
                }else{
                        return "redirect:/";
                }
        }
        
        //프로젝트 상세
        @RequestMapping(value="/detail.do", method= RequestMethod.GET)
  	    public String projectView(@RequestParam int projectId, Model model){
  			issueService.getIssuelist(projectId, model);
  	        return "projectDetail";
      	}

        @RequestMapping(value = "/insert.do", method = RequestMethod.POST)
        public String insertProject(@ModelAttribute ProjectVO vo){
                if(projectService.insertProject(vo) == 200) return "projectList";
                else if(projectService.insertProject(vo) == 405) return "redirect: /project/insertProject.do";
                return null;
        }

        //프로젝트 목록
        @RequestMapping(value = "/list.do", method = RequestMethod.GET)
        public String projectList(Model model){
                projectService.getProjectList(model);
                return "projectList";
        }
}
