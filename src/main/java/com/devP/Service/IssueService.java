package com.devP.Service;


import java.util.List;

import com.devP.VO.IssueVO;

public interface IssueService {
	
	int insertIssue(IssueVO vo);
	
	List<IssueVO> getIssuelist(int projectId);
	
	int deleteIssue(int issueId);
	
	IssueVO getIssue(int issueId);
	
	int countupIssue(int issueId);
}
