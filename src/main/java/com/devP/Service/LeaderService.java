package com.devP.Service;

import com.devP.VO.MemberVO;

import java.util.List;

public interface LeaderService {

    List<MemberVO> getMemberList(MemberVO vo);

    MemberVO findMember(MemberVO vo);

    void insertMember(MemberVO vo);

    void reInvited(MemberVO vo);

    MemberVO getMemberByToken(String token);

    void updateMemberStatus(MemberVO vo);

    void deleteMember(MemberVO vo);
}
