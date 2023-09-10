package com.devP.Mapper.Impl;


import com.devP.Mapper.Repository.MemberDAOMybatis;
import com.devP.Service.ProjectService;
import com.devP.VO.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service("projectService")
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private HttpSession session;

    @Autowired
    private MemberDAOMybatis memberDAO;

    @Override
    public int showProjectMemberList(ModelAndView mav){
        MemberVO vo = null;
//        vo.setProjectId(Integer.parseInt((String) session.getAttribute("projectId")));
        vo.setProjectId(3);
        List<MemberVO> memberList =  memberDAO.getProjectMemberList(vo);
        mav.addObject(memberList);
        return 200;
    }

}
