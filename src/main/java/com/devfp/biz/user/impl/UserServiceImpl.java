package com.devfp.biz.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devfp.biz.user.UserService;
import com.devfp.biz.user.UserVO;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDAOMybatis userDAO;

//	public void insertUser(UserVO vo) {
//		userDAO.insertUser(vo);
//	}

}
