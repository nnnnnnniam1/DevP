package com.devP.Service;

import com.devP.VO.MemberVO;
import com.devP.VO.ProjectGroupVO;
import com.devP.VO.ProjectVO;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

public interface ProjectService {
	
//	int insertProject(ProjectVO vo);

	int insertProject(ProjectVO vo, MemberVO vo2, ProjectGroupVO vo3) throws Exception;

	int insertProjectView();
	int getProjectList(Model model);

	int showProjectMemberList(MemberVO vo, Model model);
	int getProjectId(ProjectVO vo);

}
