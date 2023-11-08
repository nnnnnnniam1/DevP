package com.devP.Mapper.Impl;

import com.devP.Controller.MailController;
import com.devP.Mapper.Repository.MemberDAOMybatis;
import com.devP.Mapper.Repository.UserDAOMybatis;
import com.devP.Service.MailService;
import com.devP.Service.UserService;
import com.devP.VO.MemberVO;
import com.devP.VO.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private MailService mailService;

	@Autowired
	private UserDAOMybatis userDAO;

	@Autowired
	private MemberDAOMybatis memberDAO;

	public int getUser(UserVO vo, String saveId, HttpSession session){
		UserVO user = userDAO.getUser(vo);
		if(user != null) {
//			session.setAttribute("name", user.getName());
//			session.setAttribute("id", user.getId());
			session.setAttribute("user", user);
//            session.setAttribute("user", user);
			if ("on".equals(saveId)) {
				session.setAttribute("checked", "checked");
			} else {
				session.removeAttribute("checked");
			}
			return 200;
		} else
			return 405;
	}


	@Override
	public int getIdByEmail(UserVO vo) throws Exception {
		UserVO user = getUserByEmail(vo);
		if(user != null){
			mailService.sendId(user.getId(), user.getEmail());
			return 200;
		} else {
			return 405;
		}
	}

	@Override
	public int findPw(UserVO vo, HttpSession session) throws Exception {
		UserVO user = getUserByEmail(vo);
		if(user != null){
			String authKey = mailService.sendCode(user.getEmail());

			session.setAttribute("userId", user.getId());
			session.setAttribute("authKey", authKey);
			return 200;
		} else {
			return 405;
		}
	}

	@Override
	public int checkCode(String inputCode, HttpSession session){
		String authKey = (String) session.getAttribute("authKey");
		if(authKey.equals(inputCode)){
			return 200;
		} else { return 405; }
	}

	@Override
	public int updatePw(UserVO vo, HttpSession session){
		vo.setId((String)session.getAttribute("userId"));
		userDAO.updatePw(vo);

		return 200;
	}

	@Override
	public UserVO getUserByEmail(UserVO vo) {
		return userDAO.getUserByEmail(vo);
	}


	@Override
	public UserVO getUserDataEmail(UserVO vo){ return userDAO.getUserDataEmail(vo); }

	@Override
	public UserVO getUserName(String userId){ return userDAO.getUserName(userId);}
	@Override
	public void insertMember(MemberVO vo){ memberDAO.insertMember(vo); }

	@Override
	public UserVO getUserById(UserVO vo){ return userDAO.getUserById(vo); }
}