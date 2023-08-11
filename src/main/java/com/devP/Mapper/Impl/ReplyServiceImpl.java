package com.devP.Mapper.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devP.Mapper.Repository.ReplyDAOMybatis;
import com.devP.Service.ReplyService;
import com.devP.VO.ReplyVO;

@Service("replyService")
public class ReplyServiceImpl implements ReplyService{
	@Autowired
	private ReplyDAOMybatis replyDAO;
	
	@Override
	public int registerReply(ReplyVO vo) {
		// TODO Auto-generated method stub
		return replyDAO.registerReply(vo);
	}

	@Override
	public ReplyVO getReply(int commentId) {
		// TODO Auto-generated method stub
		return replyDAO.getReply(commentId);
	}
	
}
