package com.devP.Service;

import java.util.ArrayList;

public interface MailService {

    void sendMail(String from, ArrayList<String> to, String subject, String body) throws Exception;

    void sendId(String id, String email) throws Exception;

    String sendCode(String email) throws Exception;

    String sendInvitedMail(String leader, String project, String memberName, String email, String token) throws Exception;
}