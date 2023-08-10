package com.devP.Mapper.Repository;



import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.devP.VO.ReplyVO;

@Repository
public class ReplyDAOMybatis{

    @Autowired
    private SqlSessionTemplate mybatis;
    
    public int registerReply(ReplyVO vo){
        return mybatis.insert("ReplyDAO.registerReply", vo);
    }
}