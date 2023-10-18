package com.devP.Mapper.Repository;

import com.devP.VO.MemberVO;
import com.devP.VO.ProjectVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LeaderDAOMybatis {

    @Autowired
    private SqlSessionTemplate mybatis;

    public List<MemberVO> getMemberList(MemberVO vo){
        return mybatis.selectList("LeaderDAO.getMemberList", vo);
    }

    public MemberVO getMember(MemberVO vo){ return (MemberVO) mybatis.selectOne("LeaderDAO.getMember",vo); }
    public void insertMember(MemberVO vo){ mybatis.insert("LeaderDAO.insertMember", vo); }
    public void reInvited(MemberVO vo){ mybatis.update("LeaderDAO.updateMemberToken", vo); }
    public MemberVO getMemberByToken(MemberVO vo){return (MemberVO) mybatis.selectOne("LeaderDAO.getMemberByToken", vo); }

    public void updateMemberStatus(MemberVO vo){ mybatis.update("LeaderDAO.updateMemberStatus", vo); }
    public void updateMemberDatas(MemberVO vo){ mybatis.update("LeaderDAO.updateMemberDatas", vo); }
    public void deleteMember(MemberVO vo){ mybatis.update("LeaderDAO.deleteMember",vo); }


}