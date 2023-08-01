package com.devP.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

@Controller
public class MailController {


    @Autowired
    private JavaMailSender mailSender;

    private String from = "daggggg2@naver.com";
    private String subject;
    private String body;

    public void sendMail(String from, ArrayList<String> to, String subject, String body ) throws Exception{
        try {
            MimeMessage mail = mailSender.createMimeMessage();
            MimeMessageHelper mailHelper = new MimeMessageHelper(mail, true, "UTF-8");  // true는 멀티파트 메세지를 사용
            mailHelper.setFrom(new InternetAddress(from,"개발자국","UTF-8"));
            mailHelper.setTo(to.toArray(new String[to.size()]));
            mailHelper.setSubject(subject);
            mailHelper.setText(body,true);   // html을 사용하겠다는 의미

            mailSender.send(mail);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "sendId.do",method = RequestMethod.POST)
    public void sendId(String id, String email) throws Exception {
        ArrayList<String> to= new ArrayList<>();
        to.clear();
        to.add(email);
        subject = "[개발자국] 아이디 찾기 테스트";
        body = "당신의 아이디는 " + id + "입니다.";


        sendMail(from,to,subject,body);
    }

    @RequestMapping(value="/sendCode", method = RequestMethod.POST)
    public void sendCode(String id, String email) throws Exception {
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
    }
}

