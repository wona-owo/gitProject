package com.menupick.api.controller;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.menupick.dinner.service.DinnerService;
import com.menupick.dinner.vo.BookInfo;

/**
 * Servlet implementation class ApiEmailSendServlet
 */
@WebServlet("/api/emailSend")
public class ApiEmailSendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ApiEmailSendServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String bookNo = request.getParameter("bookNo");
		String selectedValue = request.getParameter("selectedValue");

		DinnerService service = new DinnerService();
		BookInfo bookInfoForCancelEmail = service.bookInfoForCancelEmail(bookNo);

		System.out.println("===== from ApiEmailSendServlet =====");
		System.out.println("selectedValue : " + selectedValue);
		System.out.println("bookInfoForCancelEmail : " + bookInfoForCancelEmail);

		String emailTitle = "";
		String receiver = "";
		String emailContent = "";

		// 1. 환경설정 정보 세팅
		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.naver.com"); // 사용할 메일서버(SMTP) 정보
		prop.put("mail.smtp.port", 465); // 사용한 SMTP 서버 PORT
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.ssl.enable", "true");
		prop.put("mail.smtp.ssl.trust", "smtp.naver.com");

		// 2. 세션 설정 및 인증 정보 설정
		Session session = Session.getDefaultInstance(prop, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("unemotioned@naver.com", "Blackdwarf9");
			}
		});

		// 3. 이메일 관련 정보 세팅
		MimeMessage msg = new MimeMessage(session);

		try {
			msg.setSentDate(new Date());

			msg.setFrom(new InternetAddress("unemotioned@naver.com", "UnEmotioneD"));

			InternetAddress to = new InternetAddress(receiver);
			msg.setRecipient(Message.RecipientType.TO, to);

			msg.setSubject(emailTitle);
			msg.setContent(emailContent, "text/html; charset=UTF-8");

			Transport.send(msg);

		} catch (MessagingException e) {
			e.printStackTrace();
		}

		request.setAttribute("title", "알림");
		request.setAttribute("msg", "이메일이 정상적으로 발송 되었습니다");
		request.setAttribute("icon", "success");

		request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
