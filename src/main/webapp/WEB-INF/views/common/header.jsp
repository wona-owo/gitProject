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
 			<c:when test="${empty sessionScope.loginMember }">
 				<li><a href="/admin/adminDinnerManage">매장 관리페이지</a></li>
				<li><a href="/dinner/likeFrm">인기식당</a></li>
				<li><a href="/member/loginFrm">로그인</a></li>
			</c:when>
			<c:otherwise>
			<c:if test="${loginMember.memberLevel eq 1}">
								<button type="button" onclick="moveAdminPage()"
									class="btn-point lg">관리자 페이지</button>						
				<li><a href="/admin/adminDinnerManageFrm">매장 관리페이지</a></li>
				</c:if>
				<li><a href="/member/mypage">${loginMember.memberName}님</a></li>
				<li><a href="/member/logout">로그아웃</a></li>
			</c:otherwise>
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