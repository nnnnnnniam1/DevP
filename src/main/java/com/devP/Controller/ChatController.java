package com.devP.Controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.converter.SimpleMessageConverter;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.devP.Service.ChatService;
import com.devP.Service.CommentService;
import com.devP.VO.ChatMessageVO;
import com.devP.VO.ChatVO;
import com.devP.VO.UserVO;

@Controller
@RequestMapping("/chat")
public class ChatController {
	
	@Autowired
    private SimpMessagingTemplate simpMessagingTemplate;


	@Autowired
	private ChatService chatService;
	
	@Autowired
	private HttpSession session;
	
	// 채팅 화면
	@RequestMapping(value = "/getChatView.do", method = RequestMethod.GET)
	public String chatView(@RequestParam("userId")String to_id, Model model) {
		model.addAttribute("menuId", "memberMenu");
		UserVO userData = (UserVO) session.getAttribute("user");
		String from_id = userData.getId();
		return chatService.getChatRoom(from_id, to_id, model);
	}
	
	@MessageMapping("/sendChatMessage")	
	public void sendChatMessage(ChatMessageVO vo) {
		Map<String, Object> messageData = new HashMap<>();
		messageData.put("sender", vo.getSender());
		messageData.put("receiver", vo.getReceiver());
		messageData.put("content", vo.getContent());
		chatService.insertChatMessage(vo);
		simpMessagingTemplate.convertAndSend("/topic/" + vo.getReceiver(), messageData);
		return;
	}
	
}
