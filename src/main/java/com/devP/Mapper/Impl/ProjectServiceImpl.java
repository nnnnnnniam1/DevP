package com.devP.Mapper.Impl;

import com.devP.Mapper.Repository.MemberDAOMybatis;
import com.devP.Mapper.Repository.ProjectDAOMybatis;
import com.devP.Service.LeaderService;
import com.devP.Service.MailService;
import com.devP.Service.ProjectService;
import com.devP.VO.MemberVO;
import com.devP.VO.ProjectGroupVO;
import com.devP.VO.ProjectVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

@Service("projectService")
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectDAOMybatis projectDAO;

    @Autowired
    private MemberDAOMybatis memberDAO;

    @Autowired
    private HttpSession session;

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
            String userId = session.getAttribute("id").toString();
            //model.addAttribute("projectList", projectDAO.getProjectList(userId));
            return 200;
        }else {
            return 405;
        }
    }

    @Override
    public int showProjectMemberList(MemberVO vo, Model model) {
        return 0;
    }

    @Override
    public int getProjectId(ProjectVO vo){
        return projectDAO.getProjectId(vo);
    }

}
