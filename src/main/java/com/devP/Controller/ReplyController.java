package com.devP.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.devP.Service.ReplyService;
import com.devP.VO.ReplyVO;

@Controller
@RequestMapping("/reply")
public class ReplyController {
	
	private String url = "/reply";
	
	@Autowired
	private ReplyService replyService;
	
	//이슈 댓글 작성
	@RequestMapping(value="/register.do", method= RequestMethod.POST)
	public String registerReply(@RequestBody ReplyVO vo){
		try {
			replyService.registerReply(vo);
		} catch (Exception e) {
			System.out.println(e);
		}
		//임시 페이지 설정 / 추가-작업 : 이슈 페이지로 돌아가는 작업 추가
	    return "main";
	}
	//댓글 조회
	@RequestMapping(value="/getreply.do", method= RequestMethod.GET)
	public String getReply(@RequestBody ReplyVO vo){
		try {
			ReplyVO reply = replyService.getReply(vo.getCommentId());
			System.out.println(reply.getWriter());
		} catch (Exception e) {
			System.out.println(e);
		}
		//임시 페이지 설정 / 추가-작업 : 이슈 페이지로 돌아가는 작업 추가
	    return "main";
	}
}
