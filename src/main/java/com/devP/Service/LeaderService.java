package com.devP.Service;

import com.devP.VO.MemberVO;
import com.devP.VO.ProjectVO;
import com.devP.VO.UserVO;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public interface LeaderService {

    void getLeaderView(ProjectVO vo, Model model);

    int getMemberList(MemberVO vo, Model model);

    int addMember(UserVO user, MemberVO vo, Model model) throws Exception;

    MemberVO findMember(MemberVO vo);

    void insertMember(MemberVO vo);

    void reInvited(MemberVO vo);

    int invitedVerify(MemberVO vo, String token);

    MemberVO getMemberByToken(MemberVO vo);

    void updateMemberStatus(MemberVO vo);

    int updateMemberDatas(ArrayList<MemberVO> memberVOList, Model model);

    void deleteMember(MemberVO vo, String userId, int projectId);

}
