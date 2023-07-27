package com.devP.Service;

import com.devP.VO.UserVO;

public interface UserService {

    //	public void insertUser(UserVO vo);
    UserVO getUser(UserVO vo);

    UserVO getUserIdByEmail(UserVO vo);

    UserVO getUserPwByEmail(UserVO vo);


}