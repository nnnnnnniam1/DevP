package com.devP.Mapper.Impl;

import com.devP.Mapper.Repository.UserDAOMybatis;
import com.devP.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDAOMybatis userDAO;

//	public void insertUser(UserVO vo) {
//		userDAO.insertUser(vo);
//	}

}
