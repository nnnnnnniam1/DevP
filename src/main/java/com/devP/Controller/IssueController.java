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
		//이메일 알림 전송 구현해야 함
		try {
			mailController.sendMail(from, emailList, issue.getUserId() + "(이)가 이슈 알림을 보냈습니다", issue.getContent());
		} catch (Exception e) {
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
		String[] statusArray = {"논의중", "진행중", "완료"};
		model.addAttribute("statusarr", statusArray);
        return "issueList";
    }
}