package com.devP.Mapper.Repository;

import com.devP.VO.MemberVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberDAOMybatis {

    @Autowired
    private SqlSessionTemplate mybatis;

    //leader - 멤버페이지
    public List<MemberVO> getMemberList(MemberVO vo){
        return mybatis.selectList("MemberDAO.getMemberList", vo);
    }

    public MemberVO findMember(MemberVO vo){ return (MemberVO) mybatis.selectOne("MemberDAO.findMember",vo); }
    public void insertMember(MemberVO vo){ mybatis.insert("MemberDAO.insertMember", vo); }
    public void reInvited(MemberVO vo){ mybatis.update("MemberDAO.updateMemberToken", vo); }
    public MemberVO getMemberByToken(MemberVO vo){return (MemberVO) mybatis.selectOne("MemberDAO.getMemberByToken", vo); }

    public void updateMemberStatus(MemberVO vo){ mybatis.update("MemberDAO.updateMemberStatus", vo); }
    public void updateMemberDatas(MemberVO vo){ mybatis.update("MemberDAO.updateMemberDatas", vo); }
    public void deleteMember(MemberVO vo){ mybatis.update("MemberDAO.deleteMember",vo); }



    //project - 멤버페이지
    public List<MemberVO> getProjectMemberList(MemberVO vo){ return mybatis.selectList("MemberDAO.getProjectMemberList", vo); }
}