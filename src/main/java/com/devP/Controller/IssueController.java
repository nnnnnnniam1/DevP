package com.devP.Controller;

import com.devP.Mapper.Repository.TaskDAOMybatis;
import com.devP.Service.ProjectService;
import com.devP.VO.ProjectVO;

import java.util.List;

import com.devP.VO.TaskVO;
import com.devP.VO.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;import com.devP.Mapper.Repository.MemberDAOMybatis;
import com.devP.Service.IssueService;
import com.devP.VO.IssueVO;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/issue")
public class IssueController {
	
	@Autowired
	private IssueService issueService;

	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private MemberDAOMybatis MemberDAO;
	@Autowired
	private TaskDAOMybatis TaskDAO;
	
	
	//이슈 등록 페이지
	@RequestMapping(value="/view.do", method= RequestMethod.GET)
    public String issueView(@RequestParam int projectId, @RequestParam(required = false) Integer taskId, Model model, TaskVO vo, HttpSession session){
		UserVO userData = (UserVO) session.getAttribute("user");
		vo.setProjectId(projectId); vo.setUserId(userData.getId());

		model.addAttribute("menuId", "issueMenu");
		model.addAttribute("projectId", projectId);
		model.addAttribute("memberList", MemberDAO.getMemberList(projectId));
		model.addAttribute("taskList", TaskDAO.getMyProjectTaskList(vo));

		if (taskId != null) {
			model.addAttribute("taskId", taskId);
		}
        return "issueWrite";
    }
	
	//이슈 등록
	@RequestMapping(value="/write.do", method= RequestMethod.POST)
    public String writeIssue(@ModelAttribute IssueVO issue, Model model){
		model.addAttribute("menuId", "issueMenu");
		if(issue.getTaskId() == 0){issue.setTaskId(null);}
		issueService.insertIssue(issue);

		return "redirect:/issue/list.do?projectId=" + issue.getProjectId();
    }

	//이슈 목록
	@RequestMapping(value="/list.do", method= RequestMethod.GET)	
    public String getIssuelist(@RequestParam int projectId, Model model){
		ProjectVO projectVO = new ProjectVO();
		projectVO.setProjectId(projectId);
		model.addAttribute("menuId", "issueMenu");
		model.addAttribute("project", projectService.getProject(projectVO));
		issueService.getIssuelist(projectId, model);
        return "issueList";
    }
	//이슈 삭제
	@RequestMapping(value="/delete.do", method= RequestMethod.POST)
    public String deleteIssue(@ModelAttribute IssueVO issue, Model model){
		model.addAttribute("menuId", "issueMenu");
		issueService.deleteIssue(issue);
        return "redirect:/issue/list.do?projectId=" + issue.getProjectId();
	}
	//이슈 상세
	@RequestMapping(value="/detail.do", method= RequestMethod.GET)
    public String detailIssue(@RequestParam int issueId, Model model){
		model.addAttribute("menuId", "issueMenu");
		issueService.getIssue(issueId, model);
        return "issueDetail";
	}
	//이슈 해결
	@RequestMapping(value="/solve.do", method= RequestMethod.POST)
    public String solveIssue(@ModelAttribute IssueVO issue, Model model){
		model.addAttribute("menuId", "issueMenu");
		issueService.updateIssueStatus(issue);
        return "redirect:/issue/list.do?projectId=" + issue.getProjectId();
	}
	//이슈 수정 페이지
	@RequestMapping(value="/modify/view.do", method= RequestMethod.GET)
	public String modifyIssueView(@RequestParam int issueId, Model model){
		model.addAttribute("menuId", "issueMenu");
		issueService.getIssue(issueId, model);
	    return "issueModify";
	}
	//이슈 수정
	@RequestMapping(value="/modify.do", method= RequestMethod.POST)
    public String modifyIssue(@ModelAttribute IssueVO issue, Model model){
		model.addAttribute("menuId", "issueMenu");
		System.out.println(issue.getProjectId());
		issueService.updateIssue(issue);
        return "redirect:/issue/list.do?projectId=" + issue.getProjectId();
	}
}
