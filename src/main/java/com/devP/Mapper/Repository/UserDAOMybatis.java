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

    public UserVO getUserByEmail(UserVO vo){ return (UserVO) mybatis.selectOne("UserDAO.getUserByEmail", vo); }

    public void updatePw(UserVO vo){ mybatis.update("UserDAO.updatePw", vo); }

    public UserVO getUserDataEmail(UserVO vo){ return (UserVO) mybatis.selectOne("UserDAO.getUserDataByEmail", vo); };

    public UserVO getUserName(String userId) {return (UserVO) mybatis.selectOne("UserDAO.getUserName", userId);}

    public UserVO getUserById(UserVO vo) { return (UserVO) mybatis.selectOne("UserDAO.getUserById", vo); }



}