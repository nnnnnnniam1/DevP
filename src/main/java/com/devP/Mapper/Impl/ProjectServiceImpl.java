package com.devP.Mapper.Impl;

import com.devP.Mapper.Repository.ProjectDAOMybatis;
import com.devP.Service.ProjectService;
import com.devP.VO.ProjectVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        vo.setLeader(leader);
        vo.setProgress(0);

        return projectDAO.insertProject(vo);

    }
}
