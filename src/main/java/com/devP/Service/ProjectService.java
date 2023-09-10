package com.devP.Service;

import com.devP.VO.MemberVO;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

public interface ProjectService {

    int showProjectMemberList(MemberVO vo, Model model);

}
