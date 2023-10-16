package com.devP.Mapper.Repository;




import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.devP.VO.ChatMessageVO;
import com.devP.VO.ChatVO;
import com.devP.VO.CommentVO;

@Repository
public class ChatMessageDAOMybatis{

    @Autowired
    private SqlSessionTemplate mybatis;

	public int saveChatMessage(ChatMessageVO vo) {
		return mybatis.insert("ChatMessageDAO.saveChatMessage", vo);
	}
	
	public List<ChatMessageVO> getChatHistory(String chatId, int projectId) {
		Map<String, Object> parameters = new HashMap<>();
	    parameters.put("chatId", chatId);
	    parameters.put("projectId", projectId);
		return mybatis.selectList("ChatMessageDAO.getChatHistory", parameters);
	}
}