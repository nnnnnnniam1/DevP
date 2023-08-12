package com.devP.Mapper.Impl;

import com.devP.Mapper.Repository.LeaderDAOMybatis;
import com.devP.Service.LeaderService;
import com.devP.Service.ProjectService;
import com.devP.VO.MemberVO;
import com.devP.VO.ProjectVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("LeaderService")
public class LeaderServiceImpl implements LeaderService {
	@Autowired
	private LeaderDAOMybatis leaderDAO;

	@Override
	public List<MemberVO> getMemberList(MemberVO vo){
		return leaderDAO.getMemberList(vo);
	}

	@Override
	public void insertMember(MemberVO vo){ leaderDAO.insertMember(vo); }

	@Override
	public MemberVO getMemberByToken(String token){ return leaderDAO.getMemberByToken(token); }

	@Override
	public void updateMemberStatus(MemberVO vo){ leaderDAO.updateMemberStatus(vo); }
}