package com.devP.Mapper.Repository;

import com.devP.VO.*;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.List;
@Repository
public class ProjectDAOMybatis {

    @Autowired
    private SqlSessionTemplate mybatis;

    public int insertProject(ProjectVO vo, MemberVO vo2, ProjectGroupVO vo3) {
        return mybatis.insert("ProjectDAO.insertProject", vo);
    }
    public void deleteProject(int projectId){mybatis.update("ProjectDAO.deleteProject", projectId);}

    public void insertDeleteProjectReason(DeleteProjectVO vo){ mybatis.insert("ProjectDAO.insertDeleteProjectReason", vo);}

    public int getProjectId(ProjectVO vo) {
        return mybatis.selectOne("ProjectDAO.getProjectId", vo);
    }

    public ProjectVO getProject(ProjectVO vo) {
        return mybatis.selectOne("ProjectDAO.getProject", vo);
    }

    public String getProjectName(int projectId) {
        return mybatis.selectOne("ProjectDAO.getProjectName", projectId);
    }

    public void updateProgress(int projectId) { mybatis.update("ProjectDAO.updateProgress", projectId); }

    public int getProjectProgress(ProjectVO vo) {
        return mybatis.selectOne("ProjectDAO.getProjectProgress", vo);
    }


    public List<ProjectListVO> getProjectList(String userId) {
        return mybatis.selectList("ProjectDAO.getProjectList", userId);
    }
    public  List<ProjectListVO> getCompleteProjectList(String userId) { return mybatis.selectList("ProjectDAO.getCompleteProjectList",userId); }

    public  List<ProjectListVO> getDeleteProjectList(String userId) { return mybatis.selectList("ProjectDAO.getDeleteProjectList",userId); }


    public List<ProjectListVO> getOnGoingProjectList(String userId) {
        return mybatis.selectList("ProjectDAO.getOnGoingProjectList", userId);
    }

    public List<String> getMemberNames(int projectId){return mybatis.selectList("ProjectDAO.getMemberNames", projectId);}


    public void completeProject(int projectId) { mybatis.update("ProjectDAO.completeProject", projectId); }

    public void updateReviewStatus(MemberVO vo){mybatis.insert("ProjectDAO.updateReviewStatus", vo);}

    public void insertReview(ReviewVO vo) {
    	mybatis.insert("ProjectDAO.insertReview", vo);
    }
    
    public void updateReview(ReviewVO vo) {mybatis.update("ProjectDAO.updateReview", vo);}

    public ReviewVO getReview(MemberVO vo){
        return mybatis.selectOne("ProjectDAO.getReview", vo);}
    public List<ReviewVO> getMyReview(MemberVO vo){
    	return mybatis.selectList("ProjectDAO.getMyReview", vo);
    }
}
