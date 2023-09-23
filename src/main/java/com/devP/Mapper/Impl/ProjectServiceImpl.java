package com.devP.Mapper.Impl;

import com.devP.Mapper.Repository.MemberDAOMybatis;
import com.devP.Mapper.Repository.ProjectDAOMybatis;
import com.devP.Service.ProjectService;
import com.devP.VO.MemberVO;
import com.devP.VO.ProjectVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.awt.*;
import java.util.List;

@Service("projectService")
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectDAOMybatis projectDAO;

    @Autowired
    private HttpSession session;
    @Autowired
    private MemberDAOMybatis memberDAO;



    @Override
    public ProjectVO getProject(ProjectVO vo){
        return projectDAO.getProject(vo);
    }



    @Override
    public String getProjectName(ProjectVO vo){
        return projectDAO.getProjectName(vo);
    }

    @Override
    public int getProjectProgress(ProjectVO vo){
        return projectDAO.getProjectProgress(vo);
    }

    @Override
     public MemberVO getMyProjectData(MemberVO vo){return memberDAO.getMyProjectData(vo);}


    @Override
    public int insertProject(ProjectVO vo) {
        String leader = session.getAttribute("id").toString();
        if(leader != "") {
            vo.setLeader(leader);
            vo.setProgress(0);
            projectDAO.insertProject(vo);
            return 200;
        }
        else{
            return 405;
        }
    }

    @Override
    public int insertProjectView() {
        if(session.getAttribute("id") != null) return 200;
        else return 405;

    }
    @Override
    public int showProjectMemberList(MemberVO vo, Model model){
        vo.setProjectId(1); //임시
        //vo.setUserId(session.getId());
        //List<MemberVO> memberList =  memberDAO.getProjectMemberList(vo);
        model.addAttribute("memberList", getProjectMemberList(vo.getProjectId()));
        return 200;

    }

    @Override
    public int showTaskView(ProjectVO project, MemberVO member, Model model){
        project.setProjectId(Integer.parseInt((session.getAttribute("projectId")).toString()));
        member.setProjectId(Integer.parseInt((session.getAttribute("projectId")).toString()));
        member.setUserId((String) session.getAttribute("id"));
        project.setProgress(getProjectProgress(project));   // 프로젝트 진행률

        System.out.println(member.getProjectId() + member.getUserId());



        member = getMyProjectData(member);
//        System.out.println(member.getProjectId() + member.getUserId());
        System.out.println(member.getProgress());
        model.addAttribute("project", project);
        model.addAttribute("member", member);
//        if(project != null && member != null) {return 200;}
//        else {return 405;}
        return  200;
    }

    @Override
    public List<MemberVO> getProjectMemberList(int projectId){
        return memberDAO.getProjectMemberList(projectId);
    }
}
