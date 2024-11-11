<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<style>
.join {
	display: flex;
	justify-content: center;
	align-items: center;
	font-size: 25px;
	padding-top: 145px;
	padding-bottom: 30px;
}

.joinbox {
	display: flex;
	justify-content: center;
	align-items: center;
	color: white;
	background-color: #f40;
	border: 1px solid white;
	border-radius: 30px;
	width: 340px;
	height: 50px;
}

.input-item {
	width: 340px;
	display: flex;
	justify-content: center;
	align-items: center;
	flex-direction: row; /* 가로 배치 */
	gap: 5px; /* 간격 조정 */
	padding-bottom: 20px;
	padding-left: 580px;
}

.joindiv {
	display: flex;
	justify-content: center;
	align-items: center;
	padding-top: 50px;
	padding-bottom: 100px;
}

.input-title {
	padding-left: 580px;
}
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
	<div class="join">회원가입</div>
	<br>

	<form action="/member/join" method="post" class="form">

		<div class="input-wrap">
			<div class="input-title">
				<label for='memberId'>아이디</label>
			</div>
			<div class="input-item">
				<input type="text" id="memberId" name="memberId">
			</div>
		</div>
		<div class="input-wrap">
			<div class="input-title">
				<label for='memberPw'>비밀번호</label>
			</div>
			<div class="input-item">
				<input type="password" id="memberPw" name="memberPw">
			</div>
		</div>
		<div class="input-wrap">
			<div class="input-title">
				<label for='memberName'>이름</label>
			</div>
			<div class="input-item">
				<input type="text" id="memberName" name="memberName">
			</div>
		</div>
		<div class="input-wrap">
			<div class="input-title">
				<label for='memberNick'>닉네임</label>
			</div>
			<div class="input-item">
				<input type="text" id="memberNick" name="memberNick">
			</div>
		</div>
		<div class="input-wrap">
			<div class="input-title">
				<label for='memberPhone'>전화번호</label>
			</div>
			<div class="input-item">
				<input type="text" id="memberPhone" name="memberPhone">
			</div>
		</div>
		<div class="input-wrap">
			<div class="input-title">
				<label for='memberAddr'>주소</label>
			</div>
			<div class="input-item">
				<input type="text" id="memberAddr" name="memberAddr">
			</div>
		</div>
		<div class="input-wrap">
			<div class="input-title">
				<label for='memberGender'>성별</label>
			</div>
			<div class="input-item">
				<input type="text" id="memberGender" name="memberGender">
			</div>
		</div>
		<div class="input-wrap">
			<div class="input-title">
				<label for='memberEmail'>이메일</label>
			</div>
			<div class="input-item">
				<input type="text" id="memberEmail" name="memberEmail">
			</div>
		</div>
		<div class="joindiv">
			<input type="submit" value="회원가입" class="joinbox">
		</div>

	</form>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
</body>
</html>