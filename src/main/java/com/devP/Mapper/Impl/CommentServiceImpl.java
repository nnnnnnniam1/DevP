package com.devP.Mapper.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devP.Mapper.Repository.CommentDAOMybatis;
import com.devP.Service.CommentService;
import com.devP.VO.CommentVO;

@Service("commentService")
public class CommentServiceImpl implements CommentService{
	@Autowired
	private CommentDAOMybatis CommentDAO;

	@Override
	public int insertComment(CommentVO vo) {
		return CommentDAO.insertComment(vo);
	}

	@Override
	public List<CommentVO> getComment(int issueId) {
		return CommentDAO.getComment(issueId);
	}
}
