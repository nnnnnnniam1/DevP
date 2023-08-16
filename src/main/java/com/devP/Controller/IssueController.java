package com.devP.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.devP.Service.IssueService;
import com.devP.VO.IssueVO;

@Controller
@RequestMapping("/issue")
public class IssueController {
	
	@Autowired
	private IssueService issueService;
	
	//이슈 등록 페이지
	@RequestMapping(value="/write.do", method= RequestMethod.GET)
    public String issueView(){
        return "issue";
    }
	
	//이슈 등록
	@RequestMapping(value="/write.do", method= RequestMethod.POST)
    public String issueInsert(@ModelAttribute IssueVO issue){
		issueService.insertIssue(issue);
        return "main";
    }
	//이슈 목록
	@RequestMapping(value="/list.do", method= RequestMethod.GET)
    public String getIssuelist(@RequestParam int projectId, Model model){
		issueService.getIssuelist(projectId, model);
        return "issueList";
    }
	//이슈 삭제
	@RequestMapping(value="/delete.do", method= RequestMethod.POST)
    public String deleteIssue(@RequestBody IssueVO issue){
		issueService.deleteIssue(issue);
        return "redirect:/list.do?projectId= " + issue.getProjectId();
	}
	//이슈 상세 - 추가 작업 댓글 추가 해야 됨
	@RequestMapping(value="/detail.do", method= RequestMethod.GET)
    public String getIssuedetail(@RequestParam int issueId, Model model){
		issueService.getIssue(issueId, model);
        return "issueDetail";
	}
	//이슈 해결
	@RequestMapping(value="/solve.do", method= RequestMethod.POST)
    public String solveIssue(@RequestBody IssueVO issue){
		issueService.solveIssue(issue);
        return "redirect:/issue/list.do?projectId= " + issue.getProjectId();
	}
}
