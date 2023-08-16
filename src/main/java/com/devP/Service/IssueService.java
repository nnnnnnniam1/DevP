package com.devP.Service;


import java.util.List;

import org.springframework.ui.Model;

import com.devP.VO.IssueVO;

public interface IssueService {
	
	int insertIssue(IssueVO vo);
	
	int getIssuelist(int projectId, Model model);
	
	int deleteIssue(IssueVO issue);
	
	int getIssue(int issueId, Model model);
	
	int countupIssue(int issueId);
	
	int changeIssueStatus(IssueVO vo);
}
