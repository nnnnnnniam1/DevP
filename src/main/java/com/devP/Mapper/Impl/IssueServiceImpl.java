package com.devP.Mapper.Impl;

import com.devP.Mapper.Repository.IssueDAOMybatis;
import com.devP.Service.IssueService;
import com.devP.VO.IssueVO;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service("issueService")
public class IssueServiceImpl implements IssueService {
	@Autowired
	private IssueDAOMybatis issueDAO;
	@Autowired
	private HttpSession session;
	
	@Override
	public int getIssuePage() {
		return 0;
	}
	
	@Override
	public int insertIssue(IssueVO vo){
		return issueDAO.insertIssue(vo);
	}
	
	@Override
	public List<IssueVO> getIssuelist(int projectId) {
		return issueDAO.getIssuelist(projectId);
	}
	/*	
	 * @Override public UserVO getUserIdByEmail(UserVO vo) { return
	 * userDAO.getUserIdByEmail(vo); }
	 * 
	 * @Override public UserVO getUserPwByEmail(UserVO vo) { return
	 * userDAO.getUserPwByEmail(vo); }
	 */

	

}