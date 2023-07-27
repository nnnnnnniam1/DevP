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
import java.util.Properties;

@Controller
public class MailController {

    @Autowired
    private JavaMailSender mailSender;

    @RequestMapping(value = "sendId.do",method = RequestMethod.POST)
    public String sendId(String id, String email) throws Exception {

        String from = "daggggg2@naver.com";
        String to = email;
        String subject = "[개발자국] 아이디 찾기 테스트";
        String body = "당신의 아이디는 " + id + "입니다.";

        try {
            MimeMessage mail = mailSender.createMimeMessage();
            MimeMessageHelper mailHelper = new MimeMessageHelper(mail, true, "UTF-8");  // true는 멀티파트 메세지를 사용
            mailHelper.setFrom(new InternetAddress(from,"개발자국","UTF-8"));
            mailHelper.setTo(to);
            mailHelper.setSubject(subject);
            mailHelper.setText(body,true);   // html을 사용하겠다는 의미

            mailSender.send(mail);

            return "success";
        } catch (Exception e){
            e.printStackTrace();
        }
//        mailService.sendEmail(to,from,subject, body);
        return "";
    }
}

