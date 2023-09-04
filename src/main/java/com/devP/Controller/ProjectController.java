package com.devP.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.devP.Service.ProjectService;

@Controller
@RequestMapping("/project")
public class ProjectController {

	@Autowired
	private ProjectService projectService;
	
	//프로젝트 상세 페이지
	@RequestMapping(value="/detail.do", method= RequestMethod.GET)
    public String showCalendar(Model model, @RequestParam(required = false) Integer year, @RequestParam(required = false) Integer month) {
		
		projectService.showCalendar(model, year, month);
		
        return "projectDetail";
    }
		
		
}
