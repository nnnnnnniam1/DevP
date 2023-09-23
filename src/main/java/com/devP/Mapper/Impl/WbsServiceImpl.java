package com.devP.Mapper.Impl;

import com.devP.Mapper.Repository.WbsDAOMybatis;
import com.devP.Service.WbsService;
import com.devP.VO.WbsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service("wbsService")
public class WbsServiceImpl implements WbsService {

    @Autowired
    private WbsDAOMybatis wbsDAO;

    @Autowired
    private HttpSession session;

    @Override
    public int getWbsView(){
        session.setAttribute("projectName", "DevP");
        if(session.getAttribute("projectName")!=null) return 200;
        else return 405;
    }

    @Override
    public int getWbs(WbsVO vo) {
        return 0;
    }
}
