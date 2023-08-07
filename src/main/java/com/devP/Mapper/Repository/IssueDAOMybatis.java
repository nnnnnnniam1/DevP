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

//	public void insertUser(UserVO vo) {
//		mybatis.insert("UserDAO.insertUser", vo);
//	}

    public int insertIssue(IssueVO vo){
        return mybatis.insert("IssueDAO.insertIssue", vo);
    }
    
    public List<IssueVO> getIssuelist(int projectId) {
    	return mybatis.selectList("IssueDAO.issueList", projectId);
    }
    
    public int deleteIssue(int issueId) {
    	return mybatis.delete("IssueDAO.deleteIssue", issueId);
    }

//    public UserVO getUserIdByEmail(UserVO vo){ return (UserVO) mybatis.selectOne("UserDAO.getUserIdByEmail", vo); }
//
//    public UserVO getUserPwByEmail(UserVO vo){ return (UserVO) mybatis.selectOne("UserDAO.getUserPwByEmail", vo); }
}