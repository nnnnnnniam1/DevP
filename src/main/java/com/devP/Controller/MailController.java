package com.devP.Controller;

import com.devP.Service.LeaderService;
import com.devP.VO.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

@Controller
public class MailController {


    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private LeaderService leaderService;

    private String from = "daggggg2@naver.com";
    private ArrayList<String> to= new ArrayList<>();
    private String subject;
    private String body;

    public void sendMail(String from, ArrayList<String> to, String subject, String body ) {
        try {
            MimeMessage mail = mailSender.createMimeMessage();
            MimeMessageHelper mailHelper = new MimeMessageHelper(mail, true, "UTF-8");  // true는 멀티파트 메세지를 사용
            mailHelper.setFrom(new InternetAddress(from,"개발자국","UTF-8"));
            mailHelper.setTo(to.toArray(new String[to.size()]));
            mailHelper.setSubject(subject);
            mailHelper.setText(body,true);   // html을 사용한다는 의미

            mailSender.send(mail);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public void sendId(String id, String email) {
        to.clear();
        to.add(email);
        subject = "[개발자국] 아이디 찾기 테스트";
        body = "당신의 아이디는 " + id + "입니다.";


        sendMail(from,to,subject,body);
    }

    public String sendCode(String email) throws Exception {
        Random random = new Random();
        StringBuffer buffer = new StringBuffer();
        int num = 0;
        while (buffer.length() < 6) {
            num = random.nextInt(10);
            buffer.append(num);
        }
        String authKey = buffer.toString();
        System.out.println(authKey);

        ArrayList<String> to= new ArrayList<>();

        //인증메일 보내기
        to.add(email);
        subject = "[개발자국] 비밀번호찾기 인증번호코드 ";
        body = "인증번호는 <h2>" + authKey + "</h2>입니다.<br>";
        sendMail(from,to,subject,body);

        return authKey;
    }

    public String sendInvitedMail(String leader, String project, String memberName, String email, String token) throws Exception {
        to.add(email);
        subject = "[개발자국] " + project + "초대";
        body = "<h3>" + memberName+"님, 안녕하세요</h3><br>"
                + leader+"님에 의해 " + project+" 프로젝트에 초대되었습니다.<br>"
                + "초대를 수락하신다면 "
                + "<a href='http://localhost:8080/project/addProject/verify?token="+token+"'>초대 수락</a>";
        sendMail(from,to,subject,body);
        return "";
    }

//    @RequestMapping(value="project/addProject/verify", method = RequestMethod.GET)
//    public String invitedVerify(MemberVO vo, HttpSession session,  @RequestParam String token){
//        System.out.println(token);
//        vo = leaderService.getMemberByToken(token);
//        if (vo != null){
//            System.out.println(vo.getUserId());
//            leaderService.updateMemberStatus(vo);
//            System.out.println("변경 성공");
//        } else
//            System.out.println("변경 실패");
//
//        return "redirect:login";
//    }


}

