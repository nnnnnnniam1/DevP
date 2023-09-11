package com.devP.Mapper.Impl;

import com.devP.Mapper.Repository.MemberDAOMybatis;
import com.devP.Mapper.Repository.ProjectDAOMybatis;
import com.devP.Service.ProjectService;
import com.devP.VO.MemberVO;
import com.devP.VO.ProjectVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service("projectService")
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectDAOMybatis projectDAO;

    @Autowired
    private MemberDAOMybatis memberDAO;

    @Autowired
    private HttpSession session;



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
        vo.setUserId(session.getId());
        //List<MemberVO> memberList =  memberDAO.getProjectMemberList(vo);
        model.addAttribute("memberList", memberDAO.getProjectMemberList(vo));
        System.out.println(memberDAO.getProjectMemberList(vo));
        return 200;

    }
}
