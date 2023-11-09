package com.devP.Service;

import com.devP.VO.*;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

public interface ProjectService {

	Map<String, String> getMemberMap(List<String> members);

	ProjectVO getProject(ProjectVO vo);

	String getProjectName(int projectId);

	int getProjectProgress(ProjectVO vo);

	MemberVO getMyProjectData(MemberVO vo);

	int insertProject(ProjectVO vo, MemberVO vo2, ProjectGroupVO vo3, HttpSession session) throws Exception;

	int insertProjectView();
	int getProjectList(Model model);

	int getProjectMemberList(MemberVO vo, Model model);
	int getProjectId(ProjectVO vo);

	int getMyTaskView(ProjectVO project, MemberVO member, TaskVO task, Model model) throws Exception ;

	List<MemberVO> getProjectMemberList(int projectId);

	List<String> getMemberNames(int projectId);

    int getCompleteProjectList(Model model);

	int getProjectDetail(ProjectVO vo, MemberVO member, Model model);

	int insertProjectColor(MemberVO vo);

	String getProjectColor(MemberVO vo);

	int updateReviewStatus(MemberVO vo);

	int updateReview(ReviewVO vo);

	ReviewVO getReview(ReviewVO vo);

}
