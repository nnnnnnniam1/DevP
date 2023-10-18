package com.devP.Mapper.Impl;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.devP.Mapper.Repository.ChatDAOMybatis;
import com.devP.Mapper.Repository.ChatMessageDAOMybatis;
import com.devP.Mapper.Repository.CommentDAOMybatis;
import com.devP.Mapper.Repository.MemberDAOMybatis;
import com.devP.Mapper.Repository.UserDAOMybatis;
import com.devP.Service.ChatService;
import com.devP.VO.ChatMessageVO;
import com.devP.VO.ChatVO;
import com.devP.VO.CommentVO;
import com.devP.VO.MemberVO;

@Service("chatService")
public class ChatServiceImpl implements ChatService {

	@Autowired
	private HttpSession session;
	@Autowired
	private ChatDAOMybatis ChatDAO;

	@Autowired
	private MemberDAOMybatis MemberDAO;

	@Autowired
	private UserDAOMybatis UserDAO;

	@Autowired
	private ChatMessageDAOMybatis ChatMessageDAO;

	@Override
	public String getChatRoom(String from_id, String to_id, Model model) {
		String chatId = insertRoomId(from_id, to_id);
		int projectId = (int) session.getAttribute("projectId");
		MemberVO member = new MemberVO();
		member.setProjectId(projectId);
		member.setUserId(to_id);
		if (MemberDAO.findMember(member) == null) {
			System.out.println("멤버 없음");
			return "main";
		}
		model.addAttribute("receiver", UserDAO.getUserName(to_id));
		model.addAttribute("chatRoomId", chatId);
		try {
			System.out.println("채팅 방 생성");
			ChatVO vo = new ChatVO();
			vo.setProjectId(projectId);
			vo.setChatId(chatId);
			vo.setFrom_id(from_id);
			vo.setTo_id(to_id);
			ChatDAO.insertChatRoom(vo);
			System.out.println("채팅 방 생성 완료");
		} catch (Exception e) {
			System.out.println("채팅 방 생성 에러" + e);
			List<ChatMessageVO> messageHistory = ChatMessageDAO.getChatHistory(chatId, projectId);
			model.addAttribute("messageHistoryList", messageHistory);
		}
		return "chat";
	}

	private String insertRoomId(String userId1, String userId2) {
		// 사용자 아이디를 정렬하여 채팅 방 아이디 생성
		List<String> sortedUserIds = Arrays.asList(userId1, userId2);
		sortedUserIds.sort(String::compareTo);
		return String.join("_", sortedUserIds);
	}

//	@Override
//	public ChatVO checkChatRoom(String chatId) {
//		return ChatDAO.checkChatRoom(chatId);
//	}

	@Override
	public int insertChatMessage(ChatMessageVO vo) {
		return ChatMessageDAO.insertChatMessage(vo);
	}
}
