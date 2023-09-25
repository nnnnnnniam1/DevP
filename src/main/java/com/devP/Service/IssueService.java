package com.devP.Service;



import org.springframework.ui.Model;

import com.devP.VO.IssueVO;

public interface IssueService {
	
	int insertIssue(IssueVO vo);
	
	int getIssuelist(int projectId, Model model);

	int getUserIssueList(Model model);
	
	int deleteIssue(IssueVO issue);
	
	int getIssue(int issueId, Model model);
	
	int solveIssue(IssueVO vo);
	
	int modifyIssue(IssueVO vo);
}
