package com.devP.Mapper.Impl;

import com.devP.Controller.MailController;
import com.devP.Mapper.Repository.IssueDAOMybatis;
import com.devP.Service.CommentService;
import com.devP.Service.IssueService;
import com.devP.Service.MailService;
import com.devP.Service.UserService;
import com.devP.VO.IssueVO;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service("issueService")
public class IssueServiceImpl implements IssueService {
	@Autowired
	private IssueDAOMybatis issueDAO;
	@Autowired
	private HttpSession session;
	
    private String from = "daggggg2@naver.com";

	@Autowired
    private MailService mailService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private UserService userService;
	
	
	@Override
	public int insertIssue(IssueVO issue){
		String emails = issue.getSendingEmail();
		//세션 아이디 정보 등록
		issue.setUserId(session.getAttribute("id").toString());
		//이슈 상태 초기 설정
		issue.setStatus("대기");
		
		// 구분자를 쉼표(,)로 지정하여 문자열을 나누고, 이메일 주소들을 ArrayList에 저장
        ArrayList<String> emailList = new ArrayList<>();
        String[] emailArray = emails.split(",");
        for (String email : emailArray) {
            emailList.add(email);
        }
		try {
			mailService.sendMail(from, emailList, issue.getUserId() + "(이)가 이슈 알림을 보냈습니다", issue.getContent());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return issueDAO.insertIssue(issue);
	}
	
	@Override
	public int getIssuelist(int projectId, Model model) {
		String[] statusArray = {"대기", "검토", "해결"};
		model.addAttribute("issueList", issueDAO.getIssuelist(projectId));
		model.addAttribute("statusarr", statusArray);
		return 0;
	}
	@Override
	public int deleteIssue(IssueVO issue) {
		return issueDAO.deleteIssue(issue.getIssueId());
	}
	//이슈 상세 - 추가 작업 댓글 추가 해야 됨
	@Override
	public int getIssue(int issueId, Model model) {
		try {
			IssueVO issue = new IssueVO();
			issue = issueDAO.getIssue(issueId);
			if ("대기".equals(issue.getStatus().toString())) {
				issue.setStatus("검토");
			}
			//이슈 조회수 올리기 -추가 작업 동일 세션 아이디 중복 조회 제한
			issueDAO.countupIssue(issueId);
			//이슈 상태 변경
			issueDAO.changeIssueStatus(issue);
			model.addAttribute("issue", issue);
			model.addAttribute("commentList", commentService.getComment(issue.getIssueId()));
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		return 0;
	}
	@Override
	public int solveIssue(IssueVO issue) {
		issue.setStatus("해결");	
		try {
			issueDAO.changeIssueStatus(issue);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		return 0;
	}

	@Override
	public int modifyIssue(IssueVO issue) {
		String emails = issue.getSendingEmail();
		try {
			//세션 아이디 정보 등록
			issue.setUserId(session.getAttribute("id").toString());
			// 구분자를 쉼표(,)로 지정하여 문자열을 나누고, 이메일 주소들을 ArrayList에 저장
	        ArrayList<String> emailList = new ArrayList<>();
	        String[] emailArray = emails.split(",");
	        for (String email : emailArray) {
	            emailList.add(email);
	        }
	        //이메일 알림 전송
	        mailService.sendMail(from, emailList, issue.getUserId() + "(이)가 이슈 수정 알림을 보냈습니다", issue.getContent());
			issueDAO.modifyIssue(issue);
		} catch (Exception e) {
			System.out.println(e);
		}
		return issueDAO.modifyIssue(issue);
	}
}