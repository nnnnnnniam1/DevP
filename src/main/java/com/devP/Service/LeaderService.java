package com.devP.Service;

import com.devP.VO.MemberVO;
import com.devP.VO.UserVO;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface LeaderService {

    List<MemberVO> getMemberList(MemberVO vo);

    int addMember(UserVO user, MemberVO vo, HttpSession session, Model model) throws Exception;

    MemberVO findMember(MemberVO vo);

    void insertMember(MemberVO vo);

    void reInvited(MemberVO vo);

    int invitedVerify(MemberVO vo, HttpSession session, String token);

    MemberVO getMemberByToken(MemberVO vo);

    void updateMemberStatus(MemberVO vo);

    void updateMemberDatas(MemberVO vo);

    void deleteMember(MemberVO vo);

}
