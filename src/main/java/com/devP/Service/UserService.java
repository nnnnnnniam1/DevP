package com.devP.Service;

import javax.servlet.http.HttpSession;

import com.devP.VO.MemberVO;
import com.devP.VO.UserVO;

public interface UserService {

    //	public void insertUser(UserVO vo);
    int getUser(UserVO vo, String saveId, HttpSession session);


    int getIdByEmail(UserVO vo) throws Exception;

    int findPw(UserVO vo, HttpSession session) throws Exception;

    int checkCode(String inputCode, HttpSession session);

    int updatePw(UserVO vo, HttpSession session);

    UserVO getUserByEmail(UserVO vo);

    UserVO getUserDataEmail(UserVO vo);

    UserVO getUserName(String userId);

    void insertMember(MemberVO vo);

    UserVO getUserById(UserVO vo);



}