package com.devP.Service;

import com.devP.VO.*;
import org.springframework.ui.Model;
import java.util.ArrayList;

public interface LeaderService {

    void getLeaderView(ProjectVO vo, Model model);

    int getMemberList(MemberVO vo, Model model);

    int insertLeader(MemberVO vo,int projectId);

    int insertMember(String members,ProjectVO vo, MemberVO vo2, ProjectGroupVO vo3) throws Exception;

    MemberVO getMember(MemberVO vo);

    void insertMember(MemberVO vo);

    void insertReInvitedMember(MemberVO vo);

    int insertInvitedVerify(MemberVO vo, String token);

    MemberVO getMemberByToken(MemberVO vo);

    void updateMemberStatusByToken(MemberVO vo);

    int updateMemberDatas(ArrayList<MemberVO> memberVOList, Model model);

    void deleteMember(MemberVO vo, String userId, int projectId);

    int getTaskDatas(TaskVO vo, Model model);

    int insertTask(TaskVO vo);

    int updateTaskDatas(ArrayList<TaskVO> taskVOList, Model model);

    int deleteProject(DeleteProjectVO vo);

    int updateProjectStatus(int projectId);
}
