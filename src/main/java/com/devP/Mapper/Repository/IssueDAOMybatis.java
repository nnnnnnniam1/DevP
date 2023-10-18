package com.devP.Mapper.Repository;

import com.devP.VO.IssueVO;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class IssueDAOMybatis{

    @Autowired
    private SqlSessionTemplate mybatis;
    
    public int insertIssue(IssueVO vo){
        return mybatis.insert("IssueDAO.insertIssue", vo);
    }
    
    public List<IssueVO> getIssuelist(int projectId) {
    	return mybatis.selectList("IssueDAO.issueList", projectId);
    }

    public List<IssueVO> getUserIssuelist(String userId) {
        return mybatis.selectList("IssueDAO.userIssueList", userId);
    }
    
    public int deleteIssue(int issueId) {
    	return mybatis.delete("IssueDAO.deleteIssue", issueId);
    }
    public IssueVO getIssue(int issueId) {
    	return mybatis.selectOne("IssueDAO.getIssue", issueId);
    }
    
    public int updateIssueCount(int issueId) {
    	return mybatis.update("IssueDAO.countupIssue", issueId);
    }
    
    public int updateIssueStatus(IssueVO vo) {
    	return mybatis.update("IssueDAO.changeIssueStatus", vo);
    }
    
    public int updateIssue(IssueVO vo) {
    	return mybatis.update("IssueDAO.modifyIssue", vo);
    }
}