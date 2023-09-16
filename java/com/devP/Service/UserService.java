package com.devP.Service;

import com.devP.VO.UserVO;

import javax.servlet.http.HttpSession;

public interface UserService {

    //	public void insertUser(UserVO vo);
    int getUser(UserVO vo, String saveId);

    int logout();

    int findId(UserVO vo) throws Exception;

    int findPw(UserVO vo) throws Exception;

    int checkCode(String inputCode);

    int changePw(UserVO vo);

    UserVO getUserByEmail(UserVO vo);

    void updatePw(UserVO vo);

    UserVO getUserDataEmail(UserVO vo);

    UserVO getUserName(String userId);




}