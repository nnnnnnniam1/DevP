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
import com.devP.Service.UserService;
import com.devP.VO.IssueVO;

@Controller
public class IssueController {
	private String url = "/issue";
	@Autowired
	private IssueService issueService;
	@Autowired
    private MailController mailController;
	
	@Autowired
    private JavaMailSender mailSender;

    private String from = "daggggg2@naver.com";	
	
	//이슈 등록 페이지
	@RequestMapping(value="/issue.do", method= RequestMethod.GET)
    public String issueView(HttpSession session, Model model){
		model.addAttribute("username", session.getAttribute("name"));
        return "issue";
    }
	
	//이슈 등록
	@RequestMapping(value="/issue.do", method= RequestMethod.POST)
    public String issueInsert(@ModelAttribute IssueVO issue, HttpSession session){
		String emails = issue.getSendingEmail();
		//세션 아이디 정보 등록
		issue.setUserId(session.getAttribute("id").toString());
		//이슈 상태 초기 설정
		issue.setStatus("진행중");
		
		// 구분자를 쉼표(,)로 지정하여 문자열을 나누고, 이메일 주소들을 ArrayList에 저장
        ArrayList<String> emailList = new ArrayList<>();
        String[] emailArray = emails.split(",");
        for (String email : emailArray) {
            emailList.add(email);
        }
		//이메일 알림 전송
		try {
			mailController.sendMail(from, emailList, issue.getUserId() + "(이)가 이슈 알림을 보냈습니다", issue.getContent());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//---------------------
		issueService.insertIssue(issue);
        return "main";
    }
	//이슈 목록
	@RequestMapping(value="/list.do", method= RequestMethod.GET)
    public String getIssuelist(@RequestParam int projectId, Model model){
		model.addAttribute("issueList", issueService.getIssuelist(projectId));
		String[] statusArray = {"논의중", "진행중", "해결"};
		model.addAttribute("statusarr", statusArray);
        return "issueList";
    }
	//이슈 삭제
	@RequestMapping(value="/delete.do", method= RequestMethod.POST)
    public String deleteIssue(@RequestBody IssueVO issue){
		System.out.println(issue.getIssueId());
		try {
			issueService.deleteIssue(issue.getIssueId());
		} catch (Exception e) {
			// TODO: handle exception
		}
        return "redirect:/list.do?projectId= " + issue.getProjectId();
	}
	//이슈 상세 - 추가 작업 댓글 추가 해야 됨
	@RequestMapping(value="/detail.do", method= RequestMethod.GET)
    public String getIssuedetail(@RequestParam int issueId, Model model){
		try {
			//이슈 조회수 올리기 -추가 작업 동일 세션 아이디 중복 조회 제한
			issueService.countupIssue(issueId);
			model.addAttribute("issue", issueService.getIssue(issueId));
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		System.out.println(model);
        return "issueDetail";
	}
	//이슈 해결
	@RequestMapping(value="/solve.do", method= RequestMethod.POST)
    public String solveIssue(@RequestBody IssueVO issue){
		issue.setStatus("해결");
		try {
			issueService.changeIssueStatus(issue);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
        return "redirect:/list.do?projectId= " + issue.getProjectId();
	}
}
