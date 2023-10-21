package com.devP.Mapper.Repository;




import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.devP.VO.ChatVO;
import com.devP.VO.CommentVO;

@Repository
public class ChatDAOMybatis{
    @Autowired
    private SqlSessionTemplate mybatis;

    public int insertChatRoom(ChatVO vo){
        return mybatis.insert("ChatDAO.newChatRoom", vo);
    }
//    public ChatVO checkChatRoom(String chatId) {
//    	return mybatis.selectOne("ChatDAO.checkChatRoom", chatId);
//    }
}