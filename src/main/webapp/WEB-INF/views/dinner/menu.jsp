<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="/resources/css/dinner_admin_memberdetail.css">
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp" />

	<div class="wrap">
		<main class="content mypage-container">
			<section class="section memberRv-wrap">
				<div class="page-title">회원 상세 페이지</div>
				<table class="tbl tbl_hover">
					<tr>
						<th>번호</th>
						<th>아이디</th>
						<th>이름</th>
						<th>별명</th>
						<th>전화번호</th>
						<th>주소</th>
						<th>성별</th>
						<th>이메일</th>
						<th>가입일자</th>
						<th>성인인증</th>
						<th>등급</th>
					</tr>

					<tr>
						<td>${member.memberNo}</td>
						<td>${member.memberId}</td>
						<td>${member.memberName}</td>
						<td>${member.memberNick}</td>
						<td>${member.memberPhone}</td>
						<td>${member.memberAddr}</td>
						<td>${member.memberGender}</td>
						<td>${member.memberEmail}</td>
						<td>${member.enrollDate}</td>
						<td>${member.adultValid}</td>
						<td>${member.memberLevel}</td>
					</tr>
				</table>

				<br>

				<div class="my-info-box">
					<p>총 신고당한 횟수 : 9999+</p>
					<div class="action-bar">
						<div class="sort-dropdown">
							<select id="sort-options">
								<option value="latest">최신순</option>
								<option value="oldest">오래된순</option>
								<option value="report">신고순</option>
							</select>
						</div>
						<button class="delete-button">선택삭제</button>
					</div>
				</div>
			</section>
		</main>
	</div>
</body>
</html>