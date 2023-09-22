package com.devP.Mapper.Repository;

import com.devP.VO.WbsVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class WbsDAOMybatis {

    @Autowired
    private SqlSessionTemplate mybatis;

    public int getWbs(WbsVO vo) { return  mybatis.selectOne("WbsDAO.getWBS",vo); }
}
