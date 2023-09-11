package com.devP.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.devP.Service.IssueService;

@Controller
@RequestMapping("/project")
public class ProjectController {
	
	@Autowired
	private IssueService issueService;
	
	//이슈 등록 페이지
		@RequestMapping(value="/detail.do", method= RequestMethod.GET)
	    public String projectView(@RequestParam int projectId, Model model){
			issueService.getIssuelist(projectId, model);
	        return "projectDetail";
	    }
}
