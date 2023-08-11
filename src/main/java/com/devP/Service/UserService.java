package com.devP.Service;

import com.devP.VO.UserVO;

public interface UserService {

    //	public void insertUser(UserVO vo);
    UserVO getUser(UserVO vo);

    UserVO getUserByEmail(UserVO vo);

    void updatePw(UserVO vo);

    UserVO getUserDataEmail(UserVO vo);

    UserVO getUserName(String userId);




}