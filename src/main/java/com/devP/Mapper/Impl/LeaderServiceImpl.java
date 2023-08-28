package com.devP.Mapper.Impl;

import com.devP.Mapper.Repository.LeaderDAOMybatis;
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
import java.util.List;
import java.util.UUID;

@Service("LeaderService")
public class LeaderServiceImpl implements LeaderService {
	@Autowired
	private LeaderDAOMybatis leaderDAO;

	@Autowired
	private MailService mailService;

	@Autowired
	private UserService userService;

	@Override
	public List<MemberVO> getMemberList(MemberVO vo){
		return leaderDAO.getMemberList(vo);
	}

	@Override
	public int addMember(UserVO user, MemberVO vo, HttpSession session, Model model) throws Exception {
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
	public MemberVO findMember(MemberVO vo){ return leaderDAO.findMember(vo); }
	@Override
	public void insertMember(MemberVO vo){ leaderDAO.insertMember(vo); }

	@Override
	public void reInvited(MemberVO vo){ leaderDAO.reInvited(vo); }

	@Override
	public int invitedVerify(MemberVO vo, HttpSession session, String token){
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
	public MemberVO getMemberByToken(MemberVO vo){ return leaderDAO.getMemberByToken(vo); }

	@Override
	public void updateMemberStatus(MemberVO vo){ leaderDAO.updateMemberStatus(vo); }

	@Override
	public void updateMemberDatas(MemberVO vo){ leaderDAO.updateMemberDatas(vo); }

	@Override
	public void deleteMember(MemberVO vo){ leaderDAO.deleteMember(vo);}

}