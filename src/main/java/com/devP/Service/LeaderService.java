package com.devP.Service;

import com.devP.VO.MemberVO;

import java.util.List;

public interface LeaderService {

    List<MemberVO> getMemberList(MemberVO vo);

    void insertMember(MemberVO vo);
}
