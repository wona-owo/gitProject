<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="/resources/css/default.css" />
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons"
	type="text/css" />
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script src="/resources/js/sweetalert.min.js"></script>

<header class="header">
	<div>
		<div class="logo">
			<img src="/resources/images/logo.png" class="logo-img" /> <a
				href="/">Menu Pick!</a>
		</div>

		<ul class="sub-menu">
			<c:choose>
				<%-- 비로그인 상태 --%>
				<c:when test="${empty sessionScope.loginMember}">
					<li><a href="/dinner/likeFrm">인기식당</a></li>
					<li><a href="/member/loginFrm">로그인</a></li>
				</c:when>

				<%-- 일반 회원 로그인 상태 --%>
				<c:when test="${sessionScope.loginType eq 'member'}">
					<c:choose>
						<c:when test="${sessionScope.memberLevel == 1}">
							<li><a href="/admin/member">회원 관리페이지</a></li>
							<li><a href="/admin/dinner">매장 관리페이지</a></li>
							<li><a href="/dinnerJoinForm">매장 등록(테스트)</a></li>		<!-- 매장등록 테스트할려고 잠깐 만들어놓음 -경래- -->

						</c:when>
						<c:when test="${sessionScope.memberLevel == 2}">
							<li><a href="/dinner/likeFrm">인기식당</a></li>
							<li><a href="/member/mypage">
									${sessionScope.loginMember.memberName}님 </a></li>
						</c:when>
					</c:choose>
					<li><a href="/member/logout">로그아웃</a></li>
				</c:when>

				<%-- 식당 계정 로그인 상태 --%>
				<c:when test="${sessionScope.loginType eq 'dinner'}">
					<li><a href="/member/dinnerCalendarFrm">${sessionScope.loginMember.dinnerName}님
							(식당)</a></li>
					<li><a href="/dinner/settings">식당 설정</a></li>
					<li><a href="/member/logout">로그아웃</a></li>
				</c:when>
			</c:choose>
		</ul>
	</div>

	<script>
		function msg(title, text, icon) {
			swal({

				title : title,
				text : text,
				icon : icon

			});
		}
	</script>
</header>