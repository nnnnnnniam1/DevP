package com.devP.Service;

import org.springframework.ui.Model;

import com.devP.VO.ChatMessageVO;
import com.devP.VO.ChatVO;

public interface ChatService {

//	public ChatVO checkChatRoom(String chatId);

	public String getChatRoom(String from_id,String to_id, Model model);

	public int insertChatMessage(ChatMessageVO vo);
}
