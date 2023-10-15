package com.devP.Controller;

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
		String from_id = (String) session.getAttribute("id");
		chatService.getChatRoom(from_id, to_id, model);
		return "chat";
	}
	
	@MessageMapping("/sendMessage")	
	public void sendMessage(ChatMessageVO vo) {
		System.out.println("chatId 는 : " + vo.getChatId());
		System.out.println("msg 는 : " + vo.getContent());
		System.out.println("sender 는 : " + vo.getSender());
		System.out.println("receive 는 : " + vo.getReceiver());
		chatService.saveChatMessage(vo);
		simpMessagingTemplate.convertAndSend("/topic/" + vo.getReceiver(), vo.getContent());
		return;
	}
	
}
