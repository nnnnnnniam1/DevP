package com.devP.Service;

import com.devP.VO.*;
import org.springframework.ui.Model;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

public interface LeaderService {

    void getLeaderView(ProjectVO vo, Model model);

    int getMemberList(MemberVO vo, Model model, HttpSession session);

    int insertLeader(MemberVO vo,int projectId, HttpSession session);

    int insertMember(String members,ProjectVO vo, MemberVO vo2, ProjectGroupVO vo3, HttpSession session) throws Exception;

    MemberVO getMember(MemberVO vo);

    void insertMember(MemberVO vo);

    void insertReInvitedMember(MemberVO vo);

    int updateStatusByInvitedVerify(MemberVO vo, String token);

    MemberVO getMemberByToken(MemberVO vo);

    void updateMemberStatusByToken(String userId);

    int updateMemberDatas(ArrayList<MemberVO> memberVOList, Model model);

    void deleteMember(MemberVO vo, String userId, int projectId);

    int getTaskDatas(TaskVO vo, Model model, HttpSession session);

    int insertTask(TaskVO vo);

    int updateTaskDatas(ArrayList<TaskVO> taskVOList, Model model, HttpSession session);

    int deleteProject(DeleteProjectVO vo);

    int updateProjectStatus(int projectId);
}
