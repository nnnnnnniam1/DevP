package com.devP.Mapper.Repository;

import com.devP.VO.MemberVO;
import com.devP.VO.TaskVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class MemberDAOMybatis {

    @Autowired
    private SqlSessionTemplate mybatis;


    //leader - 멤버페이지
    public List<MemberVO> getMemberList(int projectId){ return mybatis.selectList("MemberDAO.getMemberList", projectId); }

    public MemberVO getMember(MemberVO vo){ return (MemberVO) mybatis.selectOne("MemberDAO.findMember",vo); }
    public void insertMember(MemberVO vo){ mybatis.insert("MemberDAO.insertMember", vo); }
    public void updateMemberStatus(MemberVO vo){ mybatis.update("MemberDAO.updateMemberStatus", vo); }
    public MemberVO getMemberByToken(MemberVO vo){return (MemberVO) mybatis.selectOne("MemberDAO.getMemberByToken", vo); }
    public void updateMemberStatusByToken(String userId){ mybatis.update("MemberDAO.updateMemberStatusByToken", userId); }
    public void updateMemberDatas(MemberVO vo){ mybatis.update("MemberDAO.updateMemberDatas", vo); }
    public void deleteMember(MemberVO vo){ mybatis.update("MemberDAO.deleteMember",vo); }

    //업무페이지
    public MemberVO getMyProjectData(MemberVO vo){ return mybatis.selectOne("MemberDAO.getMyProjectData", vo); }

    //project - 멤버페이지
    public List<MemberVO> getProjectMemberList(int vo){ return mybatis.selectList("MemberDAO.getProjectMemberList", vo); }

    public void insertProjectColor(MemberVO vo) { mybatis.update("MemberDAO.setProjectColor",vo);}

    public String getProjectColor(MemberVO vo){return mybatis.selectOne("MemberDAO.getProjectColor",vo); }

    public void updateMemberProgress(TaskVO vo){mybatis.update("MemberDAO.updateProgress", vo);}
    public void updateAllProgress(int projectId) { mybatis.update("MemberDAO.updateAllProgress", projectId); }
}