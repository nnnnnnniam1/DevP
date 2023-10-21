package com.devP.Service;


import java.util.List;

import com.devP.VO.CommentVO;

public interface CommentService {

	int insertComment(CommentVO vo);

	List<CommentVO> getComment(int issueId);
}
