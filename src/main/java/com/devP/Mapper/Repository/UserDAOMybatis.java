package com.devP.Mapper.Repository;

import com.devP.VO.UserVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOMybatis{

    @Autowired
    private SqlSessionTemplate mybatis;

//	public void insertUser(UserVO vo) {
//		mybatis.insert("UserDAO.insertUser", vo);
//	}

    public UserVO getUser(UserVO vo){
        return (UserVO) mybatis.selectOne("UserDAO.getUser", vo);
    }

    public UserVO getUserIdByEmail(UserVO vo){ return (UserVO) mybatis.selectOne("UserDAO.getUserIdByEmail", vo); }

    public UserVO getUserPwByEmail(UserVO vo){ return (UserVO) mybatis.selectOne("UserDAO.getUserPwByEmail", vo); }
}