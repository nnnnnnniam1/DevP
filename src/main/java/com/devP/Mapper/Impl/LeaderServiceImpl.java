package com.devP.Mapper.Impl;

import com.devP.Mapper.Repository.MemberDAOMybatis;
import com.devP.Mapper.Repository.ProjectDAOMybatis;
import com.devP.Mapper.Repository.TaskDAOMybatis;
import com.devP.Service.*;
import com.devP.VO.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

import java.util.*;

@Service("LeaderService")
public class LeaderServiceImpl implements LeaderService {
	@Autowired
	private MemberDAOMybatis memberDAO;

	@Autowired
	private ProjectDAOMybatis projectDAO;
	@Autowired
	private TaskDAOMybatis taskDAO;

	@Autowired
	private MailService mailService;
	@Autowired
	private ProjectService projectService;

	@Autowired
	private TaskService taskService;

	@Autowired
	private UserService userService;

	@Autowired
	private HttpSession session;



	@Override
	public void getLeaderView(ProjectVO vo, Model model){
		//session.setAttribute("projectName", projectService.getProjectName(vo));
		model.addAttribute("project", projectService.getProject(vo));
		model.addAttribute("memberList",projectService.getProjectMemberList(vo.getProjectId()));

	}


	@Override
	public int getMemberList(MemberVO vo, Model model){
		vo.setProjectId(Integer.parseInt(session.getAttribute("projectId").toString()));
		ProjectVO project = new ProjectVO();		//임시
		project.setProjectId(vo.getProjectId());					//임시
		session.setAttribute("projectId",project.getProjectId());	//임시
		session.setAttribute("projectName",projectService.getProjectName(project.getProjectId()));	//임시
		model.addAttribute("memberList", memberDAO.getMemberList(vo.getProjectId()));

		if (memberDAO.getMemberList(vo.getProjectId()) != null) return 200;
		else return 405;
	}

	@Override
	public int insertLeader(MemberVO vo2, int projectId){
		String leader = session.getAttribute("id").toString();
		vo2.setStatus("1");
		vo2.setLeader(leader);
		vo2.setUserId(leader);
		vo2.setProjectId(projectId);
		userService.insertMember(vo2);

		return 200;
	}
	@Override
	public int insertMember(String members, ProjectVO vo, MemberVO vo2, ProjectGroupVO vo3) throws Exception {
		String leader = (String) session.getAttribute("id");
		String project = vo.getProjectName();

		String[] memberArr = members.split(",");
		System.out.println(memberArr[0]);

		for(int i=0; i<memberArr.length; i++) {
			UserVO vo4= new UserVO();
			vo4.setEmail(memberArr[i]);
			String token = UUID.randomUUID().toString();
			System.out.println("토근발행 = " + token);

			UserVO member = userService.getUserDataEmail(vo4);
			mailService.sendInvitedMail(leader, project, member.getName(), member.getEmail(), token);

			vo2.setProjectId(vo3.getProjectId());
			vo2.setUserId(member.getId());
			vo2.setStatus(token);

			try{
				if (getMember(vo2) != null) {
					insertReInvitedMember(vo2);
				} else {
//					userService.insertMember(vo2);
					insertMember(vo2);
				}
			}catch (BadSqlGrammarException e){
			}catch (DuplicateKeyException e){
			}
		}
		return 200;
	}


	@Override
	public MemberVO getMember(MemberVO vo){ return memberDAO.getMember(vo); }
	@Override
	public void insertMember(MemberVO vo){ memberDAO.insertMember(vo); }

	@Override
	public void insertReInvitedMember(MemberVO vo){ memberDAO.updateMemberStatus(vo); }

	@Override
	public int updateStatusByInvitedVerify(MemberVO vo, String token){
		vo.setStatus(token);
		System.out.println(vo.getStatus());
		vo = getMemberByToken(vo);
		if (vo != null){
			updateMemberStatusByToken(vo);
			return 200;
		} else {
			return 405;
		}

	}

	@Override
	public MemberVO getMemberByToken(MemberVO vo){ return memberDAO.getMemberByToken(vo); }

	@Override
	public void updateMemberStatusByToken(MemberVO vo){ memberDAO.updateMemberStatusByToken(vo); }

	@Override
	public int updateMemberDatas(ArrayList<MemberVO> memberVOList, Model model){

		for(MemberVO vo : memberVOList){
			memberDAO.updateMemberDatas(vo);
		}

		return 200;

	}

	@Override
	public void deleteMember(MemberVO vo, String userId, int projectId){
		vo.setUserId(userId);
		vo.setProjectId(projectId);
		memberDAO.deleteMember(vo);
	}

	@Override
	public int getTaskDatas(TaskVO vo, Model model){
		List<String> memberList = projectDAO.getMemberNames(vo.getProjectId());
		vo.setProjectId(Integer.parseInt(session.getAttribute("projectId").toString()));
		model.addAttribute("taskList",taskService.getProjectTaskList(Integer.parseInt(session.getAttribute("projectId").toString())));
		model.addAttribute("projectName",projectService.getProjectName(Integer.parseInt(session.getAttribute("projectId").toString())));	//임시

		model.addAttribute("memberMap",projectService.getMemberMap(memberList));


		return 200;
	}

	@Override
	public int insertTask(TaskVO vo){
		taskService.insertTask(vo);
		return 200;
	}
	@Override
	public int updateTaskDatas(ArrayList<TaskVO> TaskVOList, Model model){

		for(TaskVO vo : TaskVOList){
			taskService.updateTaskLeader(vo);
		};
		int projectId = Integer.parseInt(session.getAttribute("projectId").toString());
		memberDAO.updateAllProgress(projectId);
		projectDAO.updateProgress(projectId);
		return 200;

	}

	@Override
	public int deleteProject(DeleteProjectVO vo){
		projectDAO.deleteProject(vo.getProjectId());
		projectDAO.insertDeleteProjectReason(vo);
		return 200;
	}
	@Override
	public int updateProjectStatus(int projectId){
		projectDAO.completeProject(projectId);
		return 200;
	}

}