package com.devP.Mapper.Impl;

import com.devP.Mapper.Repository.ProjectDAOMybatis;
import com.devP.Service.ProjectService;
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
    public int getProjectList(int userId, Model model){
        if(session.getAttribute("id") != null) {
            model.addAttribute("projectList", projectDAO.getProjectList(userId));
            return 200;
        }else{
            return 405;
        }
    }
}
