package com.devP.Service;


import java.util.List;

import org.springframework.ui.Model;

import com.devP.VO.IssueVO;

public interface IssueService {
	
	int insertIssue(IssueVO vo);
	
	int getIssuelist(int projectId, Model model);
	
	int deleteIssue(int issueId);
}
