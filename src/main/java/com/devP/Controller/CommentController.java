package com.devP.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.devP.Service.CommentService;
import com.devP.VO.CommentVO;

@Controller
@RequestMapping("/comment")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	//이슈 댓글 작성
	@RequestMapping(value="/write.do", method= RequestMethod.POST)
	public String registerComment(CommentVO vo){
		commentService.insertComment(vo);
	    return "redirect:/issue/detail.do?issueId=" + vo.getIssueId();
	}
}
