package com.devP.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.devP.Service.CommentService;
import com.devP.VO.CommentVO;

@Controller
@RequestMapping("/reply")
public class ReplyController {
	
	private String url = "/reply";
	
	@Autowired
	private CommentService commentService;
	
	//이슈 댓글 작성
	@RequestMapping(value="/write.do", method= RequestMethod.POST)
	public String registerReply(@RequestBody CommentVO vo){
		commentService.registerComment(vo);
		//임시 페이지 설정 / 추가-작업 : 이슈 페이지로 돌아가는 작업 추가
	    return "main";
	}
}
