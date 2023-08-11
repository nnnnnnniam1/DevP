package com.devP.Controller;

import com.devP.Service.LeaderService;
import com.devP.Service.ProjectService;
import com.devP.VO.MemberVO;
import com.devP.VO.ProjectVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@SessionAttributes("project")
public class LeaderController {
    @Autowired
    private LeaderService leaderService;

    @RequestMapping(value="/project/manageMember.do", method = RequestMethod.GET)
    public String manageMemberView(HttpSession session, MemberVO vo, Model model){
        vo.setProjectId(3);
//        List<MemberVO> memberList = leaderService.getMemberList(vo);
        session.setAttribute("projectName","임시임");  //임시 데이터
        model.addAttribute("memberList", leaderService.getMemberList(vo));
        return "manageMember";
    }

    @RequestMapping(value = "/project/addMember.do", method = RequestMethod.POST)
    public String addMember(MemberVO vo,Model model){
        vo.setProjectId(3); //임시 데이터
        model.addAttribute("memberList",leaderService.getMemberList(vo));

        return "manageMember";
    }
}
