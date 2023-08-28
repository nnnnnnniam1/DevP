package com.devP.Mapper.Impl;

import com.devP.Controller.MailController;
import com.devP.Mapper.Repository.UserDAOMybatis;
import com.devP.Service.UserService;
import com.devP.VO.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {


	@Autowired
	private UserDAOMybatis userDAO;

	public UserVO getUser(UserVO vo){
		return userDAO.getUser(vo);
	}

	@Override
	public UserVO getUserByEmail(UserVO vo) {
		return userDAO.getUserByEmail(vo);
	}

	@Override
	public void updatePw(UserVO vo) { userDAO.updatePw(vo); }

	@Override
	public UserVO getUserDataEmail(UserVO vo){ return userDAO.getUserDataEmail(vo); }

	@Override
	public UserVO getUserName(String userId){ return userDAO.getUserName(userId);}

}