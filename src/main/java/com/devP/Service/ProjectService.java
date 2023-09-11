package com.devP.Service;

import com.devP.VO.ProjectVO;

import javax.servlet.http.HttpSession;

public interface ProjectService {
	
	int insertProject(ProjectVO vo);

	int insertProjectView();

}
