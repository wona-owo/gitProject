<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<style>
.login-container {
	display: flex;
	justify-content: center;
	align-items: center;
}

.login-wrap {
	width: 500px;
}

.login-wrap .input-wrap {
	padding: 15px 30px;
}

.member-link-box {
	display: flex;
	justify-content: center;
	gap: 20px;
}

.member-link-box>a:hover {
	text-decoration: underline;
}

/* 체크박스와 레이블을 정확히 같은 줄에 배치 */
.input-wrap.checkbox-container {
	display: flex;
	align-items: center; /* 수직 가운데 정렬 */
	flex-direction: row; /* 가로 배치 */
	gap: 5px; /* 간격 조정 */
	padding-top: 10px; /* 위쪽 여백 조정 (필요 시) */
}

input[type="checkbox"] {
	margin: 0; /* 체크박스 여백 초기화 */
}
.search{
	border:none;
	background-color:white;
	font-size:15.8px;
}
.search:hover{
	cursor:pointer;
	text-decoration: underline;
}

</style>
</head>
<body>
	<div class="wrap">
		<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
		<main class="content login-container">
			<section class="section login-wrap">
				<div class="page-title">로그인</div>
				<form action="/member/login" method="post" autocomplete="off"
					onsubmit="return loginValidate()">

					<div class="input-wrap">
						<div class="input-title">
							<label for="loginId">아이디</label>
						</div>
						<div class="input-item">
							<%-- MemberLoginServlet에서 저장한 쿠키 값을 기본값으로 설정 --%>
							<input type="text" id="loginId" name="loginId"
								value="${cookie.saveId.value}">
						</div>
					</div>

					<div class="input-wrap">
						<div class="input-title">
							<label for="loginPw">비밀번호</label>
						</div>
						<div class="input-item">
							<input type="password" id="loginPw" name="loginPw">
						</div>
					</div>

					<div class="input-wrap checkbox-container">
						<c:if test="${empty cookie.saveId.value}">
							<input type="checkbox" name="saveId" id="saveId" value="chk">
						</c:if>
						<c:if test="${!empty cookie.saveId.value}">
							<input type="checkbox" name="saveId" id="saveId" value="chk"
								checked>
						</c:if>
						<label for="saveId">아이디 저장</label>
					</div>

					<div class="login-button-box">
						<button type="submit" class="btn-primary lg">로그인</button>
					</div>

					<div class="member-link-box">
						<a href="/member/joinFrm">회원가입</a> | <button type="button" onclick="showIdSearchPopup()" class="search">아이디 찾기</button> | <a
							href="javascript:void(0)" onclick="searchInfo('pw')">비밀번호 찾기</a>
					</div>
				</form>
			</section>
		</main>
		<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
	</div>

	<script>
		//로그인 버튼 클릭 시, 동작 함수(submit 이전에) 
		function loginValidate() {
			if ($('#loginId').val().length < 1) {
				msg("알림", "아이디를 입력하세요.", "warning");
				$('#loginId').focus();
				return false;
			}
			if ($('#loginPw').val().length < 1) {
				msg("알림", "비밀번호를 입력하세요.", "warning");
				return false;
			}
		}
		function showIdSearchPopup() {
			 
			  var popupURL = "/member/searchIdFrm";
			  var popupProperties = "width=600,height=500,scrollbars=yes";
			  
			  window.open(popupURL, "Popup", popupProperties);
			}
			
	</script>
</body>
</html>