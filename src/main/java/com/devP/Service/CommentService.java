package com.devP.Service;


import java.util.List;

import com.devP.VO.CommentVO;

public interface CommentService {

	int registerComment(CommentVO vo);

	List<CommentVO> getComment(int issueId);
}
