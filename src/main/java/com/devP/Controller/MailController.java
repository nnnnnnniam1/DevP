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
import java.util.Random;

@Controller
public class MailController {


    @Autowired
    private JavaMailSender mailSender;

    private String from = "daggggg2@naver.com";
    private String to;
    private String subject;
    private String body;
    
    //from 나 to 보낼 사람 subject 이메일 제목 body 내용
    public void sendMail(String from, String to, String subject, String body ) throws Exception{
        try {
            MimeMessage mail = mailSender.createMimeMessage();
            MimeMessageHelper mailHelper = new MimeMessageHelper(mail, true, "UTF-8");  // true�뒗 硫��떚�뙆�듃 硫붿꽭吏�瑜� �궗�슜
            mailHelper.setFrom(new InternetAddress(from,"媛쒕컻�옄援�","UTF-8"));
            mailHelper.setTo(to);
            mailHelper.setSubject(subject);
            mailHelper.setText(body,true);   // html�쓣 �궗�슜�븯寃좊떎�뒗 �쓽誘�

            mailSender.send(mail);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "sendId.do",method = RequestMethod.POST)
    public void sendId(String id, String email) throws Exception {

        to = email;
        subject = "[媛쒕컻�옄援�] �븘�씠�뵒 李얘린 �뀒�뒪�듃";
        body = "�떦�떊�쓽 �븘�씠�뵒�뒗 " + id + "�엯�땲�떎.";

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

        //�씤利앸찓�씪 蹂대궡湲�
        to = email;
        subject = "[媛쒕컻�옄援�] 鍮꾨�踰덊샇李얘린 �씤利앸쾲�샇肄붾뱶 ";
        body = "�씤利앸쾲�샇�뒗 <h2>" + authKey + "</h2>�엯�땲�떎.<br>" +
                "<a href='localhost:8080/login.do'>濡쒓렇�씤�븯�윭 媛�湲�";

        sendMail(from,to,subject,body);
    }
}

