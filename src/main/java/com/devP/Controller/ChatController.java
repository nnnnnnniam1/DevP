package com.devP.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.devP.VO.ChatVO;

@Controller
@RequestMapping("/chat")
public class ChatController {

	// 채팅 화면
	@RequestMapping(value = "/getChatView.do", method = RequestMethod.GET)
	public String chatView(ChatVO vo) {
		return "chat";
	}
}
