package com.menupick.member.controller;

import com.menupick.member.model.service.MemberService;
import com.menupick.member.model.vo.Member;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Random;

@WebServlet("/member/searchPw")
public class MemberSearchPwServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private MemberService service = new MemberService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String memberId = request.getParameter("memberId");
        String memberPhone = request.getParameter("memberPhone");

        // 회원 정보 조회
        Member member = service.searchMemberPw(memberId, memberPhone);

        if (member != null) {
            // 임시 비밀번호 생성
            String tempPassword = generateTemporaryPassword();

            // 비밀번호 업데이트
            boolean isUpdated = service.updateMemberPassword(memberId, tempPassword);

            if (isUpdated) {
                // 임시 비밀번호를 이메일로 전송
                boolean emailSent = service.sendPwByEmail(member.getMemberEmail(), tempPassword);

                // 이메일 발송 여부에 따른 응답
                if (emailSent) {
                    response.setContentType("text/plain");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write("임시 비밀번호가 이메일로 전송되었습니다.");
                } else {
                    response.setContentType("text/plain");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write("이메일 전송에 실패했습니다. 다시 시도해 주세요.");
                }
            } else {
                response.setContentType("text/plain");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write("비밀번호 업데이트에 실패했습니다.");
            }
        } else {
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("일치하는 회원이 없습니다.");
        }
    }
    private static final char[] rndAllCharacters = new char[]{
            //number
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            //uppercase
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            //lowercase
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            //special symbols
            '@', '$', '!', '%', '*', '?', '&'
    };

    // 임시 비밀번호 생성 (예시로 숫자 6자리 랜덤 비밀번호 생성)
    private String generateTemporaryPassword() {
    	 SecureRandom random = new SecureRandom();
    	    StringBuilder stringBuilder = new StringBuilder();

    	    int passwordLength = 8;
    	    int rndAllCharactersLength = rndAllCharacters.length;
    	    for (int i = 0; i < passwordLength; i++) {
    	        stringBuilder.append(rndAllCharacters[random.nextInt(rndAllCharactersLength)]);
    	    }

    	    return stringBuilder.toString();
    	}
		
    }
