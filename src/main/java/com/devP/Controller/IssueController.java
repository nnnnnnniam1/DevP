package com.devP.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.devP.Service.IssueService;
import com.devP.Service.UserService;
import com.devP.VO.IssueVO;

@Controller
public class IssueController {
	private String url = "/issue";
	@Autowired
	private IssueService issueService;
	@Autowired
    private MailController mailController;
	
	//이슈 등록 페이지
	@RequestMapping(value="/issue.do", method= RequestMethod.GET)
    public String issueView(){
        return "issue";
    }
	
	//이슈 등록
	@RequestMapping(value="/issue.do", method= RequestMethod.POST)
    public String issueInsert(@ModelAttribute IssueVO issue, HttpSession session){
		//세션 아이디 정보 등록
		issue.setUserId(session.getAttribute("id").toString());
		//이슈 상태 초기 설정
		issue.setStatus("진행중");
		//이메일 알림 전송 구현해야 함
		
		//---------------------
		issueService.insertIssue(issue);
        return "main";
    }
	//이슈 목록
	@RequestMapping(value="/list.do", method= RequestMethod.GET)
    public String getIssuelist(@RequestParam int projectId, Model model){
		model.addAttribute("issueList", issueService.getIssuelist(projectId));
		String[] statusArray = {"논의중", "진행중", "완료"};
		model.addAttribute("statusarr", statusArray);
        return "issueList";
    }
}
