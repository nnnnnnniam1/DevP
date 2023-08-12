package com.devP.Controller;

import com.devP.Service.LeaderService;
import com.devP.Service.ProjectService;
import com.devP.Service.UserService;
import com.devP.VO.MemberVO;
import com.devP.VO.ProjectVO;
import com.devP.VO.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

@Controller
//@SessionAttributes("leader")
public class LeaderController {

    @Autowired
    private UserService userService;
    @Autowired
    private LeaderService leaderService;

    @Autowired
    private MailController mailController;


    @RequestMapping(value="/project/manageMember.do", method = RequestMethod.GET)
    public String manageMemberView(HttpSession session, MemberVO vo, Model model){
        vo.setProjectId(3); //임시
        session.setAttribute("projectId",vo.getProjectId());    //임시
        session.setAttribute("projectName","임시임");  //임시 데이터
        model.addAttribute("memberList", leaderService.getMemberList(vo));
        return "manageMember";
    }

    @RequestMapping(value = "/project/addMember.do", method = RequestMethod.POST)
    public String addMember(UserVO user, MemberVO vo, HttpSession session, Model model) throws Exception {
        String leader = (String) session.getAttribute("name");
        String project = (String) session.getAttribute("projectName");
        int projectId = (int) session.getAttribute("projectId");
        // 수락 상태를 확인할 token 발행
        String token = UUID.randomUUID().toString();
        System.out.println(token);
        UserVO member = userService.getUserDataEmail(user);
        if(member != null){
            mailController.sendInvitedMail(leader, project, member.getName(), member.getEmail(), token);
            vo.setProjectId(projectId);
            vo.setUserId(member.getId());
//            vo.setPosition();
//            vo.setRole();
            vo.setStatus(token);
            leaderService.insertMember(vo);
        }
        return "redirect:/project/manageMember.do";
    }

    @RequestMapping(value = "/project/deleteMember.do", method = RequestMethod.POST)
    public String addMember(MemberVO vo, HttpServletRequest request ) throws Exception {

        vo.setUserId(request.getParameter("userId"));
        vo.setProjectId(Integer.parseInt(request.getParameter("projectId")));
        System.out.println(vo.getUserId());
        System.out.println("일단 넘어옴");

        return  "";
    }

}
