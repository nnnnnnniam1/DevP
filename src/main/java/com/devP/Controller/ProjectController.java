package com.devP.Controller;

import com.devP.Service.IssueService;
import com.devP.Service.MailService;
import com.devP.Service.ProjectService;
import com.devP.VO.MemberVO;
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
            session.setAttribute("projectId", projectId);
            System.out.println(Integer.parseInt((session.getAttribute("projectId")).toString()));
  			issueService.getIssuelist(projectId, model);
  	        return "projectDetail";
      	}
        
        @RequestMapping(value = "/insert.do", method = RequestMethod.POST)
        public String insertProject(@ModelAttribute ProjectVO vo){
                if(projectService.insertProject(vo) == 200) return "projectList";
                else if(projectService.insertProject(vo) == 405) return "redirect: /project/insertProject.do";
                return null;
        }
        @RequestMapping(value="/member.do", method = RequestMethod.GET)
        public String manageMemberView(MemberVO vo, HttpSession session, Model model) {
                int result = projectService.showProjectMemberList(vo, model);
                return "member";
        }

        @RequestMapping(value="/myTask.do", method = RequestMethod.GET)
        public String myTaskView(ProjectVO project, MemberVO member, Model model) throws Exception {
                int result = projectService.showTaskView(project, member,model);
                if(result == 200) return "task";
                else return "redirect:/login.do";
        }


        @RequestMapping(value="/gant.do", method = RequestMethod.GET)
        public String gantChartView(){
                return "gantChart";
        }
}
