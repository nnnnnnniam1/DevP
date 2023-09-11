package com.devP.Controller;

import com.devP.Service.LeaderService;
import com.devP.Service.ProjectService;
import com.devP.Service.UserService;
import com.devP.VO.MemberVO;
import com.devP.VO.ProjectVO;
import com.devP.VO.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
//@SessionAttributes("leader")
public class LeaderController {

    @Autowired
    private UserService userService;
    @Autowired
    private LeaderService leaderService;

    @Autowired
    private MailController mailController;

    @ModelAttribute("roleMap")
    public Map<String, String> setRoleMap(){
        Map<String, String> roleMap = new HashMap<String, String>();
        roleMap.put("팀장","팀장");
        return roleMap;
    }

    @ModelAttribute("positionMap")
    public Map<String, String> setPositionMap(){
        Map<String, String> positionMap = new HashMap<String, String>();
        positionMap.put("FE", "FE");
        positionMap.put("BE", "BE");
        positionMap.put("Design", "Design");
        positionMap.put("Server", "Server");

        return positionMap;

    }

    @RequestMapping(value="/project/manageMember.do", method = RequestMethod.GET)
    public String manageMemberView(HttpSession session, MemberVO vo, Model model){
        vo.setProjectId(1); //임시
        session.setAttribute("projectId",vo.getProjectId());    //임시
        session.setAttribute("projectName","임시임");  //임시 데이터
        model.addAttribute("memberList", leaderService.getMemberList(vo));
        return "manageMember";
    }

    @RequestMapping(value = "/project/addMember.do", method = RequestMethod.POST)
    public String addMember(UserVO user, MemberVO vo, Model model) throws Exception {
        int result = leaderService.addMember(user, vo, model);
        return "redirect:/project/manageMember.do";
    }

    @RequestMapping(value="project/addProject/verify", method = RequestMethod.GET)
    public String invitedVerify(MemberVO vo, @RequestParam String token){
//        String code = token;
//        System.out.println(token);
        leaderService.invitedVerify(vo, token);

        return "redirect:/login.do";

    }

    @RequestMapping(value="/project/updateMember.do", method = RequestMethod.POST)
    public String updateMember(MemberVO vo, HttpServletRequest request){
        String[] selectedMembers = request.getParameterValues("memberDataList");
        String userId = request.getParameter("userId");
        String role = request.getParameter("role");
        String position = request.getParameter("position");
        int projectId = Integer.parseInt(request.getParameter("projectId"));

//        for(String userId : selectedMembers){
//            MemberVO vo = new MemberVO();
//            vo.setUserId(request.getParameter("userId"));
//            vo.setRole(request.getParameter("role"));
//            vo.setPosition(request.getParameter("position"));
//            vo.setProjectId(Integer.parseInt(request.getParameter("projectId")));
//            System.out.println(vo.getProjectId());
//        }
        leaderService.updateMemberDatas(vo, selectedMembers, userId, role, position, projectId);


        return "redirect:/project/manageMember.do";
    }

    @RequestMapping(value = "/project/deleteMember.do", method = RequestMethod.POST)
    public ResponseEntity<String> deleteMember(MemberVO vo, HttpServletRequest request ) throws Exception {
        try {
            String userId = request.getParameter("userId");
            int projectId = Integer.parseInt(request.getParameter("projectId"));

            leaderService.deleteMember(vo, userId, projectId);

            return ResponseEntity.ok("Member deleted successfully");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");

        }

    }
}
