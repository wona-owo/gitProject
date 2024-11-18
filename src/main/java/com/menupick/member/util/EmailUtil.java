package com.menupick.member.util;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailUtil {
    public static void sendEmail(String to, String subject, String messageText) throws Exception {
    	 final String from = "audgus4086@naver.com"; // 네이버 이메일
         final String password = "a115411-'"; // 비밀번호

         if (!to.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
             throw new IllegalArgumentException("Invalid email address: " + to);
         }

         Properties props = new Properties();
         props.put("mail.smtp.host", "smtp.naver.com"); // 네이버 SMTP 서버
         props.put("mail.smtp.port", "465"); // SSL 포트
         props.put("mail.smtp.auth", "true"); // 인증 필요
         props.put("mail.smtp.ssl.enable", "true"); // SSL 활성화
         props.put("mail.smtp.ssl.trust", "smtp.naver.com"); // 신뢰할 SMTP 서버 지정

         Session session = Session.getInstance(props, new Authenticator() {
             protected PasswordAuthentication getPasswordAuthentication() {
                 return new PasswordAuthentication(from, password); // 인증 정보 설정
             }
         });

         Message message = new MimeMessage(session);
         message.setFrom(new InternetAddress(from));
         message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
         message.setSubject(subject);
         message.setText(messageText);

         Transport.send(message);
     }
}
