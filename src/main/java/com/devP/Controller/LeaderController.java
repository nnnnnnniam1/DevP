package com.devP.Controller;

import com.devP.Mapper.Repository.MemberDAOMybatis;
import com.devP.Mapper.Repository.ProjectDAOMybatis;
import com.devP.Service.LeaderService;
import com.devP.Service.ProjectService;
import com.devP.Service.TaskService;
import com.devP.Service.UserService;
import com.devP.VO.*;
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
@RequestMapping("/leader")
//@SessionAttributes("leader")
public class LeaderController {

    @Autowired
    private UserService userService;
    @Autowired
    private LeaderService leaderService;
    @Autowired
    private MemberDAOMybatis memberDAO;
    @Autowired
    private ProjectDAOMybatis projectDAO;
    @Autowired
    private ProjectService projectService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private MailController mailController;
    
    // 멤버페이지
    @ModelAttribute("positionMap")
    public Map<String, String> setRoleMap() {
        Map<String, String> roleMap = new HashMap<String, String>();
        roleMap.put("팀장", "팀장");
        return roleMap;
    }

    @ModelAttribute("roleMap")
    public Map<String, String> setPositionMap() {
        Map<String, String> positionMap = new HashMap<String, String>();
        positionMap.put("FE", "FE");
        positionMap.put("BE", "BE");
        positionMap.put("Design", "Design");
        positionMap.put("Server", "Server");

        return positionMap;

    }

    @ModelAttribute("categoryMap")
    public Map<String, String> setCategoryMap(Model model) {
        Map<String, String> categoryMap = new HashMap<>();

        categoryMap.put("1", "기획");
        categoryMap.put("2", "분석");
        categoryMap.put("3", "디자인");
        categoryMap.put("4", "개발");
        categoryMap.put("5", "서버");
        categoryMap.put("6", "테스트");
        categoryMap.put("7", "완료");


        return categoryMap;
    }

    @ModelAttribute("workPackage1")
    public Map<String, String> setWorkPackage1(Model model) {
        Map<String, String> workPackageMap = new HashMap<>();
            workPackageMap.put("1","기획분석");
            workPackageMap.put("2","일정계획");
        return workPackageMap;
    }
    @ModelAttribute("workPackage2")
    public Map<String, String> setWorkPackage2(Model model) {
        Map<String, String> workPackageMap = new HashMap<>();
        workPackageMap.put("1","IA 정의");
        workPackageMap.put("2","사용자 화면 기획");
        return workPackageMap;
    }
    @ModelAttribute("workPackage3")
    public Map<String, String> setWorkPackage3(Model model) {
        Map<String, String> workPackageMap = new HashMap<>();
        workPackageMap.put("1","디자인");
        return workPackageMap;
    }
    @ModelAttribute("workPackage4")
    public Map<String, String> setWorkPackage4(Model model) {
        Map<String, String> workPackageMap = new HashMap<>();
        workPackageMap.put("1","프로토타입");
        workPackageMap.put("2","퍼블리싱");
        workPackageMap.put("3","개발세팅");
        workPackageMap.put("4","프런트엔드");
        workPackageMap.put("5","백엔드");
        return workPackageMap;
    }
    @ModelAttribute("workPackage5")
    public Map<String, String> setWorkPackage5(Model model) {
        Map<String, String> workPackageMap = new HashMap<>();
        workPackageMap.put("1","서버");
        return workPackageMap;
    }
    @ModelAttribute("workPackage6")
    public Map<String, String> setWorkPackage6(Model model) {
        Map<String, String> workPackageMap = new HashMap<>();
        workPackageMap.put("1","테스트");
        return workPackageMap;
    }
    @ModelAttribute("workPackage7")
    public Map<String, String> setWorkPackage7(Model model) {
        Map<String, String> workPackageMap = new HashMap<>();
        workPackageMap.put("1","모니터링 및 디버깅");
        workPackageMap.put("2","산출물 취합");
        workPackageMap.put("3","운영이관");
        return workPackageMap;
    }
    

    @ModelAttribute("statusMap")
    public Map<String, String> setStatusMap(Model model) {
        Map<String, String> statusMap = new HashMap<>();

        statusMap.put("1", "대기");
        statusMap.put("2", "진행중");
        statusMap.put("3", "검토");
        statusMap.put("4", "완료");

        return statusMap;
    }
    


    @RequestMapping(value = "/detail.do", method = RequestMethod.GET)
    public String detailLeader(@RequestParam int projectId, ProjectVO vo, Model model) {
		model.addAttribute("menuId", "leaderMenu");
        vo.setProjectId(projectId);
        leaderService.getLeaderView(vo, model);

        return "leaderDetail";
    }


    @RequestMapping(value = "/member/view.do", method = RequestMethod.GET)
    public String manageMemberLeaderView(MemberVO vo, Model model, HttpSession session) {
		model.addAttribute("menuId", "leaderMenu");
    	ProjectVO projectData = (ProjectVO) session.getAttribute("project");
        vo.setProjectId(projectData.getProjectId());
        int result = leaderService.getMemberList(vo, model, session);
        if (result == 200) return "manageMember";
        else return "redirect:/user/login/view.do";
    }

    @RequestMapping(value = "/member/add.do", method = RequestMethod.POST)
    public String addMemberLeader(String user, ProjectVO vo, MemberVO vo2, ProjectGroupVO vo3, HttpSession session) throws Exception {
    	ProjectVO projectData = (ProjectVO) session.getAttribute("project");
        vo3.setProjectId(projectData.getProjectId());
        int result = leaderService.insertMember(user, vo, vo2, vo3, session);
        return "redirect:/leader/member/view.do";
    }

    @RequestMapping(value = "/verify.do", method = RequestMethod.GET)
    public String verifyMemberLeader(MemberVO vo, @RequestParam String token) {
//        String code = token;
        System.out.println(token);
        System.out.print(vo.userId);
        leaderService.updateStatusByInvitedVerify(vo, token);

        return "redirect:/user/login/view.do";

    }

    @RequestMapping(value = "/member/modify.do", method = RequestMethod.POST)
    public String modifyMemberLeader(@ModelAttribute MemberVO memberVO, Model model) {
        int result = leaderService.updateMemberDatas(memberVO.getMemberVOList(), model);

        if (result == 200) return "redirect:/leader/member/view.do";
        else return "redirect:/";
    }

    @RequestMapping(value = "/member/delete.do", method = RequestMethod.POST)
    public ResponseEntity<String> deleteMemberLeader(MemberVO vo, HttpServletRequest request) throws Exception {
        try {	
            String userId = request.getParameter("userId");
            int projectId = Integer.parseInt(request.getParameter("projectId"));
            System.out.println(userId);
            leaderService.deleteMember(vo, userId, projectId);

            return ResponseEntity.ok("Member deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");

        }

    }

    @RequestMapping(value = "/task/view.do", method = RequestMethod.GET)
    public String manageTaskLeaderView(TaskVO vo, Model model, HttpSession session) {
		model.addAttribute("menuId", "leaderMenu");
    	ProjectVO projectData = (ProjectVO) session.getAttribute("project");
        vo.setProjectId(projectData.getProjectId());
        int result = leaderService.getTaskDatas(vo, model, session);
        if (result == 200) return "manageTask";
        else return "redirect:/";
    }

    @RequestMapping(value = "/task/add.do", method = RequestMethod.POST)
    public String addTaskLeader(TaskVO vo, HttpSession session) {
		ProjectVO projectData = (ProjectVO) session.getAttribute("project");
        System.out.println(vo.getCategory());
        int result = leaderService.insertTask(vo);

        if (result == 200) {
        	vo.setUserId(vo.getUserId());
			vo.setProjectId(projectData.getProjectId());
			memberDAO.updateMemberProgress(vo);
			projectDAO.updateProgress(vo.getProjectId());
			
			ProjectVO projectVO = new ProjectVO();
			projectVO.setProjectId(vo.getProjectId());
			session.setAttribute("project", projectService.getProject(projectVO));
        	
            return "redirect:/leader/task/view.do";
        } else {
            return "/";
        }

    }


    @RequestMapping(value = "/task/modify.do", method = RequestMethod.POST)
    public String modifyTaskLeader(@ModelAttribute TaskVO vo, Model model, HttpSession session) {
        int result = leaderService.updateTaskDatas(vo.getTaskVOList(), model, session);

        if (result == 200) return "redirect:/leader/task/view.do";
        else return "redirect:/";
    }

    @RequestMapping(value = "/task/delete.do", method = RequestMethod.POST)
    public ResponseEntity<String> deleteTaskLeader(@RequestParam int taskId) throws Exception {
        try {
            System.out.println(taskId);
            taskService.deleteTask(taskId);

            return ResponseEntity.ok("Task deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");

        }
    }

    @RequestMapping(value = "/project/delete/view.do", method = RequestMethod.GET)
    public String deleteProjectView(@RequestParam int projectId, Model model) {
        model.addAttribute("projectId", projectId);
        model.addAttribute("projectName", projectService.getProjectName(projectId));
        return "deleteProject";

    }


    @RequestMapping(value = "/project/delete.do", method = RequestMethod.POST)
    public ResponseEntity<String> deleteProjectLeader(DeleteProjectVO vo,@RequestParam("projectId") int projectId, @RequestParam("reason") String reason) {
        try {
            vo.setProjectId(projectId);
            vo.setReason(reason);

            int result = leaderService.deleteProject(vo);
            if (result == 200) {
                return ResponseEntity.ok("success");
            } else {
                return ResponseEntity.ok("failed");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");

        }
    }

    @RequestMapping(value = "/project/complete.do", method = RequestMethod.GET)
    public ResponseEntity<String> completeProjectLeader(@RequestParam("projectId") int projectId) {
        try {

            int result = leaderService.updateProjectStatus(projectId);
            if (result == 200) {
                return ResponseEntity.ok("success");
            } else {
                return ResponseEntity.ok("failed");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");

        }
    }

}
