package com.devP.Service;


import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;

import com.devP.VO.IssueVO;

public interface IssueService {
	
	int insertIssue(IssueVO vo);
	
	int getIssuelist(int projectId, Model model);
	
	int deleteIssue(IssueVO issue);
	
	int getIssue(int issueId, Model model);
	
	int solveIssue(IssueVO vo);
}
