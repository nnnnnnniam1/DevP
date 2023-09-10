package com.devP.Controller;

import com.devP.Service.MailService;
import com.devP.Service.ProjectService;
import com.devP.VO.ProjectVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;

@Controller
@SessionAttributes("project")
public class ProjectController {

        @Autowired
        private ProjectService projectService;

        @Autowired
        private MailController mailController;

        @RequestMapping(value = "/insertProject.do", method = RequestMethod.GET)
        public String insertProjectView() {return "insertProject";}

        @RequestMapping(value = "/insertProject.do", method = RequestMethod.POST)
        public String insertProject(@ModelAttribute ProjectVO vo){
                projectService.insertProject(vo);
                return "projectList";
        }
}
