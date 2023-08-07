package com.devP.Mapper.Impl;

import com.devP.Mapper.Repository.IssueDAOMybatis;
import com.devP.Service.IssueService;
import com.devP.VO.IssueVO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("issueService")
public class IssueServiceImpl implements IssueService {
	@Autowired
	private IssueDAOMybatis issueDAO;
	
	@Override
	public int insertIssue(IssueVO vo){
		return issueDAO.insertIssue(vo);
	}
	
	@Override
	public List<IssueVO> getIssuelist(int projectId) {
		return issueDAO.getIssuelist(projectId);
	}

	@Override
	public int deleteIssue(int issueId) {
		return issueDAO.deleteIssue(issueId);
	}

	@Override
	public IssueVO getIssue(int issueId) {
		return issueDAO.getIssue(issueId);
	}

	@Override
	public int countupIssue(int issueId) {
		return issueDAO.countupIssue(issueId);
	}

	@Override
	public int changeIssueStatus(IssueVO vo) {
		return issueDAO.changeIssueStatus(vo);
	}
}