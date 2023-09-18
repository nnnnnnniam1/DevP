package com.devP.Mapper.Impl;

import com.devP.Mapper.Repository.MemberDAOMybatis;
import com.devP.Service.LeaderService;
import com.devP.Service.MailService;
import com.devP.Service.ProjectService;
import com.devP.Service.UserService;
import com.devP.VO.MemberVO;
import com.devP.VO.ProjectVO;
import com.devP.VO.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service("LeaderService")
public class LeaderServiceImpl implements LeaderService {
	@Autowired
	private MemberDAOMybatis memberDAO;

	@Autowired
	private MailService mailService;

	@Autowired
	private UserService userService;

	@Autowired
	private HttpSession session;

	@Autowired
	private ProjectService projectService;

	@Override
	public void getLeaderView(ProjectVO vo, Model model){
		//session.setAttribute("projectName", projectService.getProjectName(vo));
		model.addAttribute("project", projectService.getProject(vo));
		model.addAttribute("memberList",projectService.getProjectMemberList(vo.getProjectId()));

	}


	@Override
	public int getMemberList(MemberVO vo, Model model){
		vo.setProjectId(1);
		ProjectVO project = new ProjectVO();		//임시
		project.setProjectId(1);					//임시
		session.setAttribute("projectId",project.getProjectId());	//임시
		session.setAttribute("projectName",projectService.getProjectName(project));	//임시
		model.addAttribute("memberList", memberDAO.getMemberList(vo.getProjectId()));
		//System.out.println(memberDAO.getMemberList(vo.getProjectId()).get(0).getRole());
		if (memberDAO.getMemberList(vo.getProjectId()) != null) return 200;
		else return 405;
	}

	@Override
	public int addMember(UserVO user, MemberVO vo, Model model) throws Exception {
		String leader = (String) session.getAttribute("name");
		String project = (String) session.getAttribute("projectName");
		int projectId = (int) session.getAttribute("projectId");

		// 수락 상태를 확인할 token 발행
		String token = UUID.randomUUID().toString();
		System.out.println(token);
		UserVO member = userService.getUserDataEmail(user);
		if(member != null){
			mailService.sendInvitedMail(leader, project, member.getName(), member.getEmail(), token);
			vo.setProjectId(projectId);
			vo.setUserId(member.getId());
			vo.setStatus(token);
			if(findMember(vo) != null){
				reInvited(vo);
			} else {
				insertMember(vo);
			}
		}
		return 200;
	}

	@Override
	public MemberVO findMember(MemberVO vo){ return memberDAO.findMember(vo); }
	@Override
	public void insertMember(MemberVO vo){ memberDAO.insertMember(vo); }

	@Override
	public void reInvited(MemberVO vo){ memberDAO.reInvited(vo); }

	@Override
	public int invitedVerify(MemberVO vo, String token){
		vo.setStatus(token);
		System.out.println(vo.getStatus());
		vo = getMemberByToken(vo);
		if (vo != null){
			System.out.println(vo.getUserId());
			updateMemberStatus(vo);
			System.out.println("변경 성공");
			return 200;
		} else {
			System.out.println("변경 실패");
			return 405;
		}

	}

	@Override
	public MemberVO getMemberByToken(MemberVO vo){ return memberDAO.getMemberByToken(vo); }

	@Override
	public void updateMemberStatus(MemberVO vo){ memberDAO.updateMemberStatus(vo); }

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

}