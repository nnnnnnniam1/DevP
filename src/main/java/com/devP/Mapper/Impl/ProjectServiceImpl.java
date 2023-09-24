package com.devP.Mapper.Impl;

import com.devP.Mapper.Repository.MemberDAOMybatis;
import com.devP.Mapper.Repository.ProjectDAOMybatis;
import com.devP.Service.LeaderService;
import com.devP.Service.MailService;
import com.devP.Service.ProjectService;
import com.devP.VO.MemberVO;
import com.devP.VO.ProjectGroupVO;
import com.devP.VO.ProjectListVO;
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
    @Autowired
    private MailService mailService;

    @Autowired
    private LeaderService leaderService;

    @Override
    public int insertProject(ProjectVO vo, MemberVO vo2, ProjectGroupVO vo3) throws Exception {
        if(session.getAttribute("id") != "") {
            String leader = session.getAttribute("id").toString();
            vo.setLeader(leader);
            vo.setProgress(0);
            if(vo.getEmail()!="") {
                String members = vo.getEmail();
                projectDAO.insertProject(vo, vo2, vo3);
                //session.removeAttribute("projectName");
                //session.setAttribute("projectName", vo.getProjectName());
                vo3.setProjectId(getProjectId(vo));
                leaderService.addMember(members, vo, vo2, vo3);
                return 200;
            }
            return 0;
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
    public int getProjectList(Model model){
        if(session.getAttribute("id") != null) {
            ProjectListVO vo = new ProjectListVO();
            String userId = session.getAttribute("id").toString();
            vo.setUserId(userId);
//            List<ProjectListVO> vo2 = projectDAO.getProjectList(vo);
//            System.out.println(vo2.getProjectName());
            model.addAttribute("projectList", projectDAO.getProjectList(vo));
            return 200;
        }else {
            return 405;
        }
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

    public int showProjectMemberList(MemberVO vo, Model model) {
        return 0;
    }

    @Override
    public int getProjectId(ProjectVO vo){
        return projectDAO.getProjectId(vo);
    }

    @Override
    public List<MemberVO> getProjectMemberList(int projectId) {
        return memberDAO.getProjectMemberList(projectId);
    }

}
