package com.devP.Controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
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
import com.devP.Service.MailService;
import com.devP.Service.UserService;
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
		model.addAttribute("issueList", issueService.getIssuelist(projectId));
		String[] statusArray = {"논의중", "진행중", "완료"};
		model.addAttribute("statusarr", statusArray);
        return "issueList";
    }
}
