package com.devP.Service;

import com.devP.VO.MemberVO;
import com.devP.VO.UserVO;

public interface UserService {

    //	public void insertUser(UserVO vo);
    int getUser(UserVO vo, String saveId);


    int getIdByEmail(UserVO vo) throws Exception;

    int findPw(UserVO vo) throws Exception;

    int checkCode(String inputCode);

    int updatePw(UserVO vo);

    UserVO getUserByEmail(UserVO vo);

    UserVO getUserDataEmail(UserVO vo);

    UserVO getUserName(String userId);

    void insertMember(MemberVO vo);

    UserVO getUserById(UserVO vo);



}