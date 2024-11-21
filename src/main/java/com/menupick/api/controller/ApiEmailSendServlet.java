/**
 * AJAX with  DinnerCancelReservationServlet.java
 * 
 * 이전 서블릿에서 삭제를 하기 전에 이메일을 보낸다음에 삭제를 한다
 * 그리고 돌아가서 DB 에서 예약 정보 삭제
 * 
 * @author 김찬희
 */
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

		String emailContent = "취소 사유 : ";

		switch (selectedValue) {
		case "0":
			emailContent += "숯불에 불남";
			break;
		case "1":
			emailContent += "불판에 불남";
			break;
		case "2":
			emailContent += "예약 시스템의 오류로 시간당 최대 수용인원을 초과 하였습니다";
			break;
		default:
			emailContent = "foobar";
			break;
		}

		DinnerService service = new DinnerService();
		BookInfo info = service.bookInfoForCancelEmail(bookNo);

		String dinnerName = info.getDinnerName();

		String memberName = info.getMemberName();
		String receiver = info.getMemberEmail();

		String bookDate = info.getBookDate();
		String bookTime = info.getBookTime();
		String newDate = bookDate.substring(5, 7) + "월 " + bookDate.substring(8) + "일";
		String newTime = bookTime.substring(0, 2) + "시 " + bookTime.substring(2) + "분";

		String emailTitle = memberName + " 님의 '" + dinnerName + "'에 대한 " + newDate + " " + newTime + " 예약이 취소 되었습니다";

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

			String emailSender = "메뉴픽";
			msg.setFrom(new InternetAddress("unemotioned@naver.com", emailSender));

			InternetAddress to = new InternetAddress(receiver);
			msg.setRecipient(Message.RecipientType.TO, to);

			msg.setSubject(emailTitle);
			msg.setContent(emailContent, "text/html; charset=UTF-8");

			Transport.send(msg);

		} catch (MessagingException e) {
			e.printStackTrace();
		}

		int emailSent = 1;
		response.getWriter().print(emailSent);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
