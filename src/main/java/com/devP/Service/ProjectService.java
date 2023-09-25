package com.devP.Service;

import com.devP.VO.MemberVO;
import com.devP.VO.ProjectGroupVO;
import com.devP.VO.ProjectVO;
import com.devP.VO.TaskVO;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.List;

import org.springframework.ui.Model;

public interface ProjectService {


	ProjectVO getProject(ProjectVO vo);

	String getProjectName(ProjectVO vo);

	int getProjectProgress(ProjectVO vo);

	MemberVO getMyProjectData(MemberVO vo);

	int insertProject(ProjectVO vo, MemberVO vo2, ProjectGroupVO vo3) throws Exception;

	int insertProjectView();
	int getProjectList(Model model);

	int showProjectMemberList(MemberVO vo, Model model);
	int getProjectId(ProjectVO vo);

	int showTaskView(ProjectVO project, MemberVO member, TaskVO task, Model model) throws Exception ;

	List<MemberVO> getProjectMemberList(int projectId);
}
