package com.devP.Service;

import com.devP.VO.MemberVO;
import com.devP.VO.ProjectVO;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.List;

import org.springframework.ui.Model;

public interface ProjectService {


	ProjectVO getProject(ProjectVO vo);

	String getProjectName(ProjectVO vo);

	int getProjectProgress(ProjectVO vo);
	
	int insertProject(ProjectVO vo);

	int insertProjectView();

	int showProjectMemberList(MemberVO vo, Model model);

	List<MemberVO> getProjectMemberList(int projectId);
}
