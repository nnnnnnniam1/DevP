package com.devP.Mapper.Repository;




import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.devP.VO.CommentVO;

@Repository
public class CommentDAOMybatis{

    @Autowired
    private SqlSessionTemplate mybatis;
    
    public int registerComment(CommentVO vo){
        return mybatis.insert("CommentDAO.registerComment", vo);
    }

	public List<CommentVO> getComment(int issueId) {
		return mybatis.selectList("CommentDAO.getComment", issueId);
	}
}