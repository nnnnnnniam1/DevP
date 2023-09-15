package com.devP.Service;

import com.devP.VO.ProjectVO;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

public interface ProjectService {
	
	int insertProject(ProjectVO vo);

	int insertProjectView();
	int getProjectList(Model model);

}
