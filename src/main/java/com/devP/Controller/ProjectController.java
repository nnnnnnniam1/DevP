package com.devP.Controller;

import com.devP.Mapper.Repository.MemberDAOMybatis;
import com.devP.Mapper.Repository.ReportDAOMybatis;
import com.devP.Service.IssueService;
import com.devP.Service.LeaderService;
import com.devP.Service.ProjectService;
import com.devP.Service.TaskService;
import com.devP.VO.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("project")
public class ProjectController {

	@Autowired
	private IssueService issueService;

	@Autowired
	private ProjectService projectService;


    @Autowired
    private MemberDAOMybatis memberDAO;
    @Autowired
    private ReportDAOMybatis reportDAO;
	@Autowired
	private TaskService taskService;

	@Autowired
	private LeaderService leaderService;

	@Autowired
	private LeaderController leaderController;

	@Autowired
	private MailController mailController;

	@ModelAttribute("colorMap")
	public Map<String, String> setColorMap() {
		Map<String, String> colorMap = new HashMap<>();

		colorMap.put("1", "F5A9A9");
		colorMap.put("2", "F5D0A9");
		colorMap.put("3", "F2F5A9");
		colorMap.put("4", "BCF5A9");
		colorMap.put("5", "BCF5A9");
		colorMap.put("6", "A9D0F5");
		colorMap.put("7", "A9BCF5");
		colorMap.put("8", "A9A9F5");
		colorMap.put("9", "D0A9F5");
		colorMap.put("10", "F6CEEC");

		return colorMap;
	}

	@ModelAttribute("statusMap")
	public Map<String, String> setStatusMap(Model model) {
		Map<String, String> statusMap = new HashMap<>();

		statusMap.put("1", "대기");
		statusMap.put("2", "진행중");
		statusMap.put("3", "검토");
//        statusMap.put("4", "완료");

		return statusMap;
	}

	// 프로젝트 추가 화면
	@RequestMapping(value = "/add/view.do", method = RequestMethod.GET)
	public String addProjectView(HttpSession session) {
		if (projectService.insertProjectView(session) == 200) {
			return "insertProject";
		} else if (projectService.insertProjectView(session) == 405) {
			return "redirect:/user/login/view.do";
		} else {
			return "redirect:/";
		}
	}

	@RequestMapping(value = "/color/set.do", method = RequestMethod.GET)
	public String setColorProject(@RequestParam String projectColor, MemberVO vo, HttpSession session) {
		ProjectVO projectData = (ProjectVO) session.getAttribute("project");
		UserVO userData = (UserVO) session.getAttribute("user");
		System.out.println(projectColor);
		vo.setColor("#" + projectColor);
		vo.setUserId(userData.getId());
		vo.setProjectId(projectData.getProjectId());
		int result = projectService.insertProjectColor(vo);

		int projectId = projectData.getProjectId();
		System.out.println("projectId is =" + projectId);
		System.out.println("getColor is =" + vo.getColor());

		return "redirect:/project/detail.do?projectId=" + projectId;
//        return "main";

	}

	// 프로젝트 상세
	@RequestMapping(value = "/detail.do", method = RequestMethod.GET)
	public String detailProject(@RequestParam int projectId, ProjectVO vo, MemberVO member, Model model,
			HttpSession session) {
		UserVO userData = (UserVO) session.getAttribute("user");
		model.addAttribute("menuId", "projectMenu");
		if (session.getAttribute("project") != null)
			session.removeAttribute("project");
		session.setAttribute("project", projectService.getProject(vo));
//        if(session.getAttribute("projectId")!=null) session.removeAttribute("projectId");
//        session.setAttribute("project", projectId);

		vo.setProjectId(projectId);
		member.setProjectId(projectId);
		member.setUserId(userData.getId());

		int result = projectService.getProjectDetail(vo, member, model, session);
//        vo.setProjectId(projectId);
//        member.setProjectId(projectId);
//        member.setUserId(session.getAttribute("id").toString());

		if (result == 200)
			return "projectDetail";
		else
			return "redirect:/";
	}

	@RequestMapping(value = "/add.do", method = RequestMethod.POST)
	public String addProject(@ModelAttribute ProjectVO vo, MemberVO vo2, ProjectGroupVO vo3, HttpSession session)
			throws Exception {
		int result = projectService.insertProject(vo, vo2, vo3, session);
		if (result == 200) {
			int projectId = projectService.getProjectId(vo);
			vo.setProjectId(projectId);
			session.setAttribute("project", vo);
			return "redirect: /project/task/add/view.do";
		} else if (result == 405)
			return "redirect: /project/insertProject.do";
		return null;
	}

	@RequestMapping(value = "/task/add/view.do", method = RequestMethod.GET)
	public String addTaskView(TaskVO vo, Model model, HttpSession session) {
		model.addAttribute("menuId", "projectMenu");
		ProjectVO projectData = (ProjectVO) session.getAttribute("project");
		vo.setProjectId(projectData.getProjectId());
		List<String> memberList = projectService.getMemberNames(vo.getProjectId());

		model.addAttribute("menuId", "");
		model.addAttribute("categoryMap", taskService.setCategoryMap());
		model.addAttribute("statusMap", taskService.setStatusMap());
		model.addAttribute("workPackage1",leaderController.setWorkPackage1(model));
		model.addAttribute("workPackage2",leaderController.setWorkPackage2(model));
		model.addAttribute("workPackage3",leaderController.setWorkPackage3(model));
		model.addAttribute("workPackage4",leaderController.setWorkPackage4(model));
		model.addAttribute("workPackage5",leaderController.setWorkPackage5(model));
		model.addAttribute("workPackage6",leaderController.setWorkPackage6(model));
		model.addAttribute("workPackage7",leaderController.setWorkPackage7(model));
		model.addAttribute("memberMap",projectService.getMemberMap(memberList));
		int result = leaderService.getTaskDatas(vo, model, session);

		if (result == 200)
			return "insertTask";
		else {
			session.removeAttribute("project");
			return "redirect:/list.do";
		}
	}

	@RequestMapping(value = "/task/add.do", method = RequestMethod.POST)
	public ResponseEntity<String> addTask(TaskVO vo, Model model, HttpSession session) throws Exception {
		ProjectVO projectData = (ProjectVO) session.getAttribute("project");
		try {
			vo.setProjectId(projectData.getProjectId());
			System.out.println(vo.getProjectId());
			int result = leaderService.insertTask(vo);

			return ResponseEntity.ok("Task insert successfully");

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");

		}
	}

	@RequestMapping(value = "/task/modify.do", method = RequestMethod.POST)
	public String modifyTaskLeader(@ModelAttribute TaskVO vo, Model model, HttpSession session) {
		int result = leaderService.updateTaskDatas(vo.getTaskVOList(), model, session);

		if (result == 200)
			return "redirect:/project/task/add/view.do";
		else
			return "redirect:/list";
	}

	// 프로젝트 목록
	@RequestMapping(value = "/list/view.do", method = RequestMethod.GET)
	public String listProjectView(Model model, HttpSession session) {
		if (session.getAttribute("project") != null) {
			session.removeAttribute("project");
			System.out.println("프로젝트 세션 값 초기화");
		}
		if (projectService.getProjectList(model, session) == 200) {
			return "projectList";
		} else if (projectService.getProjectList(model, session) == 405) {
			return "login";
		}
		return null;
	}

	@RequestMapping(value = "/list/complete/view.do", method = RequestMethod.GET)
	public String completeListProjectView(Model model, HttpSession session) {
		int result = projectService.getCompleteProjectList(model, session);
		if (result == 200) {
			return "completeProjectList";
		} else if (result == 405) {
			return "mainTemp";
		}
		return null;
	}

	@RequestMapping(value = "/myTask.do", method = RequestMethod.GET)
	public String myTaskView(ProjectVO project, MemberVO member, TaskVO task, Model model, HttpSession session)
			throws Exception {
		model.addAttribute("menuId", "taskMenu");
		int result = projectService.getMyTaskView(project, member, task, model, session);
		if (result == 200)
			return "task";
		else
			return "main";
	}

	@RequestMapping(value = "/member/list.do", method = RequestMethod.GET)
	public String manageMemberView(MemberVO vo, HttpSession session, Model model) {
		model.addAttribute("menuId", "memberMenu");
		int result = projectService.getProjectMemberList(vo, model, session);

		if (result == 200)
			return "member";
		else
			return "mainTemp";
	}

	@RequestMapping(value = "/review/report.do", method = RequestMethod.GET)
	public String reviewWriteView(@RequestParam int projectId, ProjectVO vo, MemberVO member, Model model,
			HttpSession session) {
		vo.setProjectId(projectId);
		session.setAttribute("project", projectService.getProject(vo));
		ProjectVO projectData = (ProjectVO) session.getAttribute("project");
		UserVO userData = (UserVO) session.getAttribute("user");
		member.setProjectId(projectId);
		member.setUserId(userData.getId());
		if(projectService.getReview(member) != null) {
			model.addAttribute("myReview", projectService.getMyReview(member));
			model.addAttribute("projectData",projectData);
			model.addAttribute("taskList",taskService.getProjectTaskList(projectId));

			model.addAttribute("reportData", reportDAO.getReportTaskData(projectId));

			if(projectData.getStatus().equals("삭제")){
				model.addAttribute("deleteReason",reportDAO.getDeleteProjectReason(projectId));
			}

			return "reviewReport";
		}

		model.addAttribute("projectData", projectData);
		model.addAttribute("memberData", projectService.getProjectMemberList(projectId));

		return "reviewWrite";
	}

    @RequestMapping(value = "/review/report.do", method = RequestMethod.POST)
    public String reviewReportView(@RequestParam("content") List<String> contentList,
								   @RequestParam("evaMemberId") List<String> evaMemberIdList,
								   MemberVO member, HttpSession session, Model model, MemberVO vo) {
        ProjectVO projectData = (ProjectVO) session.getAttribute("project");
        UserVO userData = (UserVO) session.getAttribute("user");

        int projectId = projectData.getProjectId();
		member.setProjectId(projectId);
		member.setUserId(userData.getId());

        vo.setProjectId(projectId); vo.setUserName(userData.getId());
		projectService.updateReviewStatus(member);
		projectService.insertReview(session, contentList, evaMemberIdList, projectData, userData);
		model.addAttribute("myReview", projectService.getMyReview(member));

        model.addAttribute("projectData",projectData);
        model.addAttribute("taskList",taskService.getProjectTaskList(projectId));

        model.addAttribute("reportData", reportDAO.getReportTaskData(projectId));
		if(projectData.getStatus().equals("삭제")){
			model.addAttribute("deleteReason",reportDAO.getDeleteProjectReason(projectId));
		}

		return "reviewReport";
	}

	@RequestMapping(value = "/progress.do", method = RequestMethod.GET)
	public String progressView(HttpSession session, ProjectVO vo, Model model){
		model.addAttribute("menuId", "progressMenu");
		ProjectVO projectData = (ProjectVO) session.getAttribute("project");
		vo.setProjectId(projectData.getProjectId());
		leaderService.getLeaderView(vo, model);

		return "progress";
	}
}
