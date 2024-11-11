<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 상세 페이지</title>
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
						<td>${member.adultConfirm}</td>
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

				<br>

				<p>회원이 쓴 리뷰</p>
				<div class="my-info-wrap">
					<div class="rvMainBox">   						<!-- 리뷰 공간 건드릴려면 이거 ㅇㅇ -->
						<c:forEach var="review" items="${reviews}">
							<div>
								<p>식당명: ${review.dinnerName}</p>
								<p>신고당한 횟수: 0</p>
								<!-- 신고 횟수는 필요 시 추가 -->
								<p>아이디(별명): ${member.memberId} (${member.memberNick})</p>
								<br>
								<p>작성일자: ${review.reviewDate}</p>
								<br>
								<div class="reviewBox">
									<!-- 리뷰 이미지 -->
									<c:choose>
										<c:when test="${review.reviewImage != null}">
											<div class="reviewPhoto">
												<img
													src="data:image/jpeg;base64,${fn:escapeXml(review.reviewImage)}"
													alt="리뷰 이미지">
											</div>
										</c:when>
										<c:otherwise>
											<div class="reviewPhoto">
												<img src="/resources/images/default_review.png" alt="기본 이미지">
											</div>
										</c:otherwise>
									</c:choose>
								</div>
								<p>${review.reviewContent}</p>
								<div class="checkbox-container">
									<input type="checkbox" name="rpchk"
										data-review-no="${review.reviewNo}">
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</section>
		</main>
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
</body>
</html>