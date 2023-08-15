package com.devP.Service;


import java.util.List;

import com.devP.VO.IssueVO;

public interface IssueService {
	
	int getIssuePage();
	
	int insertIssue(IssueVO vo);
	
	List<IssueVO> getIssuelist(int projectId);
}
