<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f0f0f0;
	margin: 0;
	padding: 0;
}

.wrap {
	width: 100%;
	max-width: 800px;
	margin: 0 auto;
	background-color: #fff;
	padding: 20px;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
	border-radius: 10px;
}

.input-wrap {
	display: flex;
	flex-direction: column;
	margin-bottom: 15px;
}

.input-title {
	margin-bottom: 5px;
}

.input-item {
	width: 100%;
}

input[type="text"],
input[type="password"],
input[type="email"] {
	width: calc(100% - 20px);
	padding: 10px;
	margin: 5px 0;
	border: 1px solid #b0b0b0;
	border-radius: 8px;
	transition: border-color 0.3s, box-shadow 0.3s;
}

input[type="text"]:focus,
input[type="password"]:focus,
input[type="email"]:focus {
	border-color: #808080;
	box-shadow: 0 0 5px rgba(128, 128, 128, 0.5);
	outline: none;
}

button {
	padding: 10px 20px;
	border: none;
	border-radius: 5px;
	background-color: #007bff;
	color: #fff;
	font-size: 16px;
	cursor: pointer;
	transition: background-color 0.3s;
}

button:hover {
	background-color: #0056b3;
}

.join-button-box {
	display: flex;
	justify-content: center;
	align-items: center;
	padding-top: 30px;
}

.btn-primary {
	background-color: #007bff;
	border: none;
	border-radius: 5px;
	padding: 10px 20px;
	color: #fff;
	font-size: 16px;
	cursor: pointer;
	transition: background-color 0.3s;
}

.btn-primary:hover {
	background-color: #0056b3;
}
</style>
<body>
	<div class="wrap">
		<jsp:include page="/WEB-INF/views/common/header.jsp" />
		<main class="content">
			<section class="section join-wrap">
				<div class="page-title">회원가입</div>
				<form action="/member/join" method="post" autocomplete="off"
					onsubmit="return joinValidate()">
					<div class="input-wrap">
						<div class="input-title">
							<label for="memberId">아이디</label>
						</div>
						<div class="input-item">
							<input type="text" id="memberId" name="memberId"
								placeholder="영어 + 숫자 6~12글자" maxlength="12" />
							<button type="button" id="idDuplChkBtn" class="btn-primary">중복체크</button>
						</div>
						<p id="idMessage" class="input-msg"></p>
					</div>
					<div class="input-wrap">
						<div class="input-title">
							<label for="memberPw">비밀번호</label>
						</div>
						<div class="input-item">
							<input type="password" id="memberPw" name="memberPw"
								placeholder="영어 + 숫자 + 특수문자 8~20글자" maxlength="20" />
						</div>
					</div>
					<div class="input-wrap">
						<div class="input-title">
							<label for="memberPwConfirm">비밀번호 확인</label>
						</div>
						<div class="input-item">
							<input type="password" id="memberPwConfirm" maxlength="20" />
						</div>
						<p id="pwMessage" class="input-msg"></p>
					</div>
					<div class="input-wrap">
						<div class="input-title">
							<label for="memberName">이름</label>
						</div>
						<div class="input-item">
							<input type="text" id="memberName" name="memberName"
								placeholder="한글 2~10글자" maxlength="10">
						</div>
						<p id="nameMessage" class="input-msg"></p>
					</div>
					<div class="input-wrap">
						<div class="input-title">
							<label for="memberNick">닉네임</label>
						</div>
						<div class="input-item">
							<input type="text" id="memberNick" name="memberNick"
								placeholder="한글,영어,숫자,특수문자 포함 2~10글자" maxlength="10">
							<button type="button" id="nickDuplChkBtn" class="btn-primary">중복체크</button>
						</div>
						<p id="nickMessage" class="input-msg"></p>
					</div>
					<div class="input-wrap">
						<div class="input-title">
							<label for="memberPhone">전화번호</label>
						</div>
						<div class="input-item">
							<input type="text" id="memberPhone" name="memberPhone"
								placeholder="전화번호(010-0000-0000)" maxlength="13">
						</div>
						<p id="phoneMessage" class="input-msg"></p>
					</div>
					<div class="input-wrap">
						<label for="zipp_btn" class="input-title">주소 *</label>
						<div class="input-item">
							<input type="text" id="zipp_code_id" name="zipp_code"
								maxlength="10" placeholder="우편번호" style="width: 50%; display: inline;">
							<button type="button" id="zipp_btn" class="btn-primary" onclick="execDaumPostcode()">우편번호 찾기</button>
							<input type="text" name="user_add1" id="UserAdd1" maxlength="40" placeholder="기본 주소를 입력하세요" required>
							<input type="text" name="user_add2" id="UserAdd2" maxlength="40" placeholder="상세 주소를 입력하세요">
						</div>
					</div>
					<div class="input-wrap">
						<label for="memberGender">성별</label>
						<div class="input-title">
							<input type="radio" id="memberGender" name="memberGender" value="m">남자
							<input type="radio" id="memberGender" name="memberGender" value="f">여자
						</div>
					</div>
					<div class="input-wrap">
						<div class="input-title">
							<label for="memberEmail">이메일</label>
						</div>
						<div class="input-item">
							<input type="email" id="memberEmail" name="memberEmail">
						</div>
						<p id="emailMessage" class="input-msg"></p>
					</div>
					<div class="join-button-box">
						<button type="submit" class="btn-primary lg">회원가입</button>
					</div>
				</form>
			</section>
		</main>
		<jsp:include page="/WEB-INF/views/common/footer.jsp" />
	</div>
</body>
</html>
