package com.devP.Service;

import com.devP.VO.ProjectVO;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

public interface ProjectService {
	
	int insertProject(ProjectVO vo);

	int insertProjectView();
	
	void calendarView();
}
