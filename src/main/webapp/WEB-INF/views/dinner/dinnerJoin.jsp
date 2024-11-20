<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>식당 정보 등록</title>
<link rel="stylesheet" href="/resources/css/default.css">
<script
	src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<style>
/* 페이지 전체 스타일 */
body {
	background-color: #f7f7f7; /* 밝은 회색 배경 */
	font-family: Arial, sans-serif;
	margin: 0;
	padding: 0;
}

.wrap {
	max-width: 800px;
	margin: 50px auto;
	background: #ffffff; /* 카드 스타일 흰색 배경 */
	padding: 30px;
	border-radius: 10px;
	box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
}

/* 오른쪽 위 이미지 스타일 */
.header-img {
	position: absolute;
	top: 10px;
	right: 0px;
	width: 50px;
	height: auto;
}

.page-title {
	font-size: 24px;
	font-weight: bold;
	color: #f40; /* 포인트 색상 */
	text-align: center;
	margin-bottom: 20px;
}

/* 입력 폼 스타일 */
.form-group {
	margin-bottom: 20px;
}

.form-group label {
	display: block;
	font-size: 14px;
	font-weight: bold;
	margin-bottom: 8px;
	color: #333; /* 진한 회색 텍스트 */
}

.form-group input, .form-group select {
	width: 100%;
	padding: 12px 15px;
	border: 1px solid #ddd;
	border-radius: 8px;
	background-color: #fafafa; /* 밝은 배경 */
	font-size: 14px;
	color: #333;
	transition: border-color 0.3s, box-shadow 0.3s;
	box-sizing: border-box;
}

.form-group input:focus, .form-group select:focus {
	outline: none;
	border-color: #f40; /* 포커스 시 포인트 색상 */
	box-shadow: 0 0 6px rgba(255, 68, 68, 0.3); /* 포인트 색상 강조 */
}

/* 주소 입력란 스타일 */
#zipp_code_id {
	width: 20%;
	display: inline-block;
	padding: 12px 15px;
	border: 1px solid #ddd;
	border-radius: 8px;
	background-color: #fafafa;
	font-size: 14px;
	color: #333;
	transition: border-color 0.3s, box-shadow 0.3s;
	box-sizing: border-box;
}

#zipp_btn {
	width: 10%;
	display: inline-block;
	padding: 12px 20px;
	background-color: #f40; /* 버튼 색상 */
	color: white;
	border: none;
	border-radius: 8px;
	cursor: pointer;
	font-size: 14px;
	text-align: center;
	margin-left: 0.5%;
}

#zipp_btn:hover {
	background-color: #c30; /* 버튼 호버 시 색상 */
}

/* 기본 주소와 상세 주소 입력란을 한 줄에 나란히 배치 */
#UserAdd1 {
	width: 60%;
	display: inline-block;
	padding: 12px 15px;
	border: 1px solid #ddd;
	border-radius: 8px;
	background-color: #fafafa;
	font-size: 14px;
	color: #333;
	transition: border-color 0.3s, box-shadow 0.3s;
	box-sizing: border-box;
	margin-top: 1%;
	margin-right: 0.5%;
}

#UserAdd2 {
	width: 39%;
	display: inline-block;
	padding: 12px 15px;
	border: 1px solid #ddd;
	border-radius: 8px;
	background-color: #fafafa;
	font-size: 14px;
	color: #333;
	transition: border-color 0.3s, box-shadow 0.3s;
	box-sizing: border-box;
}

/* 우편번호 찾기 버튼과 기본주소 및 상세주소가 잘 보이도록 여백 설정 */
.form-group input:focus {
	border-color: #f40;
	box-shadow: 0 0 6px rgba(255, 68, 68, 0.3);
}

/* 제출 버튼 스타일 */
.submit-btn {
	display: inline-block;
	width: 100%;
	padding: 12px 20px;
	font-size: 16px;
	font-weight: bold;
	color: #fff; /* 텍스트 흰색 */
	background-color: #f40; /* 포인트 색상 */
	border: none;
	border-radius: 8px;
	cursor: pointer;
	text-align: center;
	transition: background-color 0.3s, box-shadow 0.3s;
}

.submit-btn:hover {
	background-color: #d93636; /* 호버 시 약간 어두운 빨간색 */
	box-shadow: 0 4px 10px rgba(217, 54, 54, 0.3); /* 부드러운 그림자 */
}

.submit-btn:active {
	background-color: #b12b2b; /* 클릭 시 더 어두운 빨간색 */
	box-shadow: none;
}

/* 반응형 디자인 */
@media ( max-width : 768px) {
	.wrap {
		padding: 20px;
	}
	.page-title {
		font-size: 20px;
	}
	.form-group input, .form-group select {
		font-size: 13px;
		padding: 10px 12px;
	}
	.submit-btn {
		font-size: 14px;
		padding: 10px;
	}

	/* 주소 입력란을 세로로 배치하도록 조정 */
	#zipp_code_id, #UserAdd1, #UserAdd2 {
		width: 100%;
		margin-right: 0;
		margin-left: 0;
	}
	#zipp_btn {
		width: 100%;
		margin-top: 15px;
	}
	/* 아이디 입력칸과 버튼 정렬 및 높이 맞춤 */
	.id-check input {
		width: auto;
		flex: 1;
		height: 40px;
		padding: 10px;
		font-size: 14px;
		border: 1px solid #ddd;
		border-radius: 8px;
		box-sizing: border-box;
	}
	.id-check button {
		height: 50px;
		padding: 0 15px;
		font-size: 14px;
		background-color: #f40;
		color: white;
		border: none;
		border-radius: 8px;
		cursor: pointer;
		flex-shrink: 0;
		transition: background-color 0.3s;
	}
	.id-check button:hover {
		background-color: #c30;
	}
}

/* 비밀번호 유효성 검사 메시지 스타일 */
#pwMessage, #pwConfirmMessage {
	font-size: 12px !important; /* 글자 크기를 작게 설정 */
	margin-top: 5px !important; /* 위와의 간격 */
	line-height: 1.2 !important; /* 줄 간격 */
}

#pwMessage.valid, #pwConfirmMessage.valid {
	color: green !important; /* 유효한 메시지는 초록색 */
}

#pwMessage.invalid, #pwConfirmMessage.invalid {
	color: red !important; /* 유효하지 않은 메시지는 빨간색 */
}

.submit-btn, .cancel-btn {
	flex: 1;
	max-width: 48%; /* 버튼이 너무 넓어지지 않도록 제한 */
}

.submit-btn:hover, .cancel-btn:hover {
	background-color: #c30;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2); /* 부드러운 강조 효과 */
}

.btn-primary {
	background-color: #f40; /* 기본 색상 */
	color: white; /* 텍스트 흰색 */
	border: none;
	border-radius: 8px;
	cursor: pointer;
	padding: 12px 20px;
	transition: background-color 0.3s, box-shadow 0.3s; /* 부드러운 전환 효과 */
}

.btn-primary:hover {
	background-color: #c30; /* 호버 시 색상 */
	box-shadow: 0 4px 10px rgba(217, 54, 54, 0.3); /* 버튼 호버 시 그림자 */
}

.btn-primary:active {
	background-color: #b12b2b; /* 클릭 시 더 어두운 빨간색 */
	box-shadow: none; /* 클릭 시 그림자 제거 */
}
</style>
</head>
<body>
	<div class="wrap">
		<a href="/"> <img src="/resources/images/logo.png" alt="Logo"
			class="header-img">
		</a>
		<h1 class="page-title">식당 정보 등록</h1>
		<form action="${pageContext.request.contextPath}/dinnerJoin"
			method="post">
			<!-- 식당 번호 (히든 필드) -->
			<input type="hidden" id="dinnerNo" name="dinnerNo"
				value="${dinnerNo}">
			<!-- 승인 여부 (히든 필드) -->
			<input type="hidden" id="dinnerConfirm" name="dinnerConfirm"
				value="n">

			<!-- 식당 이름 -->
			<div class="form-group">
				<label for="dinnerName">식당 이름</label> <input type="text"
					id="dinnerName" name="dinnerName" required maxlength="100"
					placeholder="식당 이름을 입력하세요.">
			</div>

			<!-- 식당 주소 -->
			<div class="form-group">
				<label for="dinnerAddr">식당 주소</label>
				<div>
					<input type="text" id="zipp_code_id" name="zipp_code"
						maxlength="10" placeholder="우편번호"
						style="width: 50%; display: inline;">
					<button type="button" id="zipp_btn" class="btn btn-primary"
						onclick="execDaumPostcode()">찾기</button>
					<input type="text" name="user_add1" id="UserAdd1" maxlength="40"
						placeholder="기본 주소를 입력하세요" required> <input type="text"
						name="user_add2" id="UserAdd2" maxlength="40"
						placeholder="상세 주소를 입력하세요">
				</div>
			</div>

			<script>
				function execDaumPostcode() {
					new daum.Postcode(
							{
								oncomplete : function(data) {
									var addr = ''; // 기본 주소 변수
									var extraAddr = ''; // 참고 항목 변수

									// 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
									if (data.userSelectedType === 'R') { // 도로명 주소
										addr = data.roadAddress;
									} else { // 지번 주소
										addr = data.jibunAddress;
									}

									// 참고항목 설정 (도로명 주소 선택 시)
									if (data.userSelectedType === 'R') {
										if (data.bname !== ''
												&& /[동|로|가]$/g.test(data.bname)) {
											extraAddr += data.bname;
										}
										if (data.buildingName !== ''
												&& data.apartment === 'Y') {
											extraAddr += (extraAddr !== '' ? ', '
													+ data.buildingName
													: data.buildingName);
										}
										if (extraAddr !== '') {
											extraAddr = ' (' + extraAddr + ')';
										}
									}

									// 선택된 주소 정보를 해당 필드에 입력
									document.getElementById('zipp_code_id').value = data.zonecode; // 우편번호
									document.getElementById('UserAdd1').value = addr
											+ extraAddr; // 기본 주소
									document.getElementById('UserAdd2').focus(); // 상세주소로 포커스 이동
								}
							}).open();
				}
			</script>

			<!-- 영업 시작 시간 -->
			<div class="form-group">
				<label for="dinnerOpen">영업 시작 시간</label> <input type="text"
					id="dinnerOpen" name="dinnerOpen" required
					pattern="^(0[0-9]|1[0-9]|2[0-3]|24):[0-5][0-9]$"
					placeholder="예: 09:00 (HH:mm)">
			</div>

			<!-- 영업 종료 시간 -->
			<div class="form-group">
				<label for="dinnerClose">영업 종료 시간</label> <input type="text"
					id="dinnerClose" name="dinnerClose" required
					pattern="^(0[0-9]|1[0-9]|2[0-3]|24):[0-5][0-9]$"
					placeholder="예: 22:00 (HH:mm)">
			</div>

			<!-- 전화번호 -->
			<div class="form-group">
				<label for="dinnerPhone">전화번호</label> <input type="text"
					id="dinnerPhone" name="dinnerPhone" required
					pattern="^\d{3}-\d{4}-\d{4}$" maxlength="13"
					placeholder="예: 010-1234-5678">
			</div>

			<!-- 이메일 -->
			<div class="form-group">
				<label for="dinnerEmail">이메일</label> <input type="email"
					id="dinnerEmail" name="dinnerEmail" required maxlength="30"
					placeholder="이메일 주소를 입력하세요.">
			</div>

			<!-- 주차 가능 여부 -->
			<div class="form-group">
				<label for="dinnerParking">주차 가능 여부</label> <select
					id="dinnerParking" name="dinnerParking" required>
					<option value="y">가능</option>
					<option value="n">불가능</option>
				</select>
			</div>

			<!-- 최대 인원 -->
			<div class="form-group">
				<label for="dinnerMaxPerson">최대 인원</label> <input type="number"
					id="dinnerMaxPerson" name="dinnerMaxPerson" required min="1"
					placeholder="최대 수용 인원을 입력하세요.">
			</div>

			<!-- 사업자 등록 번호 -->
			<div class="form-group">
				<label for="busiNo">사업자 등록 번호</label> <input type="text" id="busiNo"
					name="busiNo" required pattern="^\d{3}-\d{2}-\d{5}$" maxlength="12"
					placeholder="예: 123-45-67890">
			</div>

			<%-- 매장 사진 업로드 --%>
			<%-- daniel --%>
			<div class="form-group">
				<label for="uploadFile">매장 사진 업로드</label> <input type="file"
					name="uploadFile" id="uploadFile">
			</div>

			<!-- 아이디 -->
			<div class="form-group id-check">
				<label for="dinnerId">아이디</label>
				<div style="display: flex; align-items: center;">
					<input type="text" id="dinnerId" name="dinnerId" required
						maxlength="20" placeholder="아이디를 입력하세요."
						style="flex: 1; margin-right: 10px;">
					<button type="button" id="idDuplChkBtn" class="btn-primary">중복체크</button>
				</div>
			</div>

			<!-- 비밀번호 -->
			<div class="form-group"
				style="display: flex; align-items: center; gap: 10px;">
				<div style="flex: 1;">
					<label for="dinnerPw">비밀번호</label> <input type="password"
						id="dinnerPw" name="dinnerPw" required minlength="8"
						maxlength="60" placeholder="비밀번호를 입력하세요." style="width: 100%;">
					<p id="pwMessage"></p>
				</div>
				<div style="flex: 1;">
					<label for="dinnerPwConfirm">비밀번호 확인</label> <input type="password"
						id="dinnerPwConfirm" name="dinnerPwConfirm" required minlength="8"
						maxlength="60" placeholder="비밀번호를 다시 입력하세요." style="width: 100%;">
					<p id="pwConfirmMessage"></p>
				</div>
			</div>

			<!-- 제출 버튼 -->
			<div
				style="display: flex; justify-content: space-between; gap: 10px;">
				<button type="submit" class="submit-btn">등록</button>
				<button type="button" class="submit-btn cancel-btn"
					onclick="history.back();">취소</button>
			</div>
		</form>
	</div>
</body>

<script>
	$(document)
			.ready(
					function() {
						const checkObj = {
							dinnerPw : false,
							dinnerPwConfirm : false,
							dinnerPwChanged : false,
							idDuplChk : false
						};

						const dinnerPw = $('#dinnerPw');
						const dinnerPwConfirm = $('#dinnerPwConfirm');
						const pwMessage = $('#pwMessage');
						const pwConfirmMessage = $('#pwConfirmMessage');

						// 비밀번호 입력 유효성 검사
						dinnerPw
								.on(
										'input',
										function() {
											checkObj.dinnerPwChanged = true;
											pwMessage
													.removeClass('valid invalid');

											const regExp = /^(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*])[A-Za-z0-9!@#$%^&*]{8,20}$/;

											if (regExp.test($(this).val())) {
												pwMessage
														.html("사용 가능한 비밀번호입니다");
												pwMessage.addClass("valid");
												checkObj.dinnerPw = true;

												// 비밀번호가 유효할 때만 비밀번호 확인 일치 여부를 검사
												if (dinnerPwConfirm.val() !== "") {
													checkPasswordMatch();
												}
											} else {
												pwMessage
														.html("비밀번호는 8~20자로 설정해주세요. (숫자, 영문자, 특수문자를 포함)");
												pwMessage.addClass("invalid");
												checkObj.dinnerPw = false;

												// 비밀번호 형식이 유효하지 않으면 비밀번호 확인 메시지 초기화
												pwConfirmMessage.html("");
												pwConfirmMessage
														.removeClass('valid invalid');
												checkObj.dinnerPwConfirm = false;
											}
										});

						// 비밀번호 확인 일치 여부 검사
						dinnerPwConfirm.on('input', function() {
							// 비밀번호 형식이 유효할 때만 일치 여부를 검사
							if (checkObj.dinnerPw) {
								checkPasswordMatch();
							} else {
								pwConfirmMessage.html("");
								pwConfirmMessage.removeClass('valid invalid');
								checkObj.dinnerPwConfirm = false;
							}
						});

						// 비밀번호와 비밀번호 확인 일치 여부 검사 함수
						function checkPasswordMatch() {
							pwConfirmMessage.removeClass('valid invalid');

							if (dinnerPw.val() === dinnerPwConfirm.val()) {
								pwConfirmMessage.html("비밀번호가 일치합니다");
								pwConfirmMessage.addClass('valid');
								checkObj.dinnerPwConfirm = true;
							} else {
								pwConfirmMessage.html("비밀번호가 일치하지 않습니다");
								pwConfirmMessage.addClass('invalid');
								checkObj.dinnerPwConfirm = false;
							}
						}

						// 아이디 중복체크
						$('#idDuplChkBtn').on(
								'click',
								function() {
									const dinnerId = $('#dinnerId').val();

									if (!dinnerId) {
										msg("알림", "유효한 아이디를 입력한 후 중복체크를 진행하세요",
												"error");
										return false;
									}

									$.ajax({
										url : "/dinner/idDuplChk",
										data : {
											"dinnerId" : dinnerId
										},
										type : "get",
										success : function(res) {
											if (res == 0) {
												msg("알림", "사용 가능한 아이디입니다",
														"success");
												checkObj.idDuplChk = true;
											} else {
												msg("알림", "중복된 아이디가 존재합니다",
														"warning");
												checkObj.idDuplChk = false;
											}
										},
										error : function() {
											console.log('ajax 오류 발생');
										}
									});
								});

						// SweetAlert 메시지 표시 함수
						function msg(title, text, icon) {
							swal({
								title : title,
								text : text,
								icon : icon,
							});
						}

						$('form').on(
								'submit',
								function(event) {
									if (!checkObj.idDuplChk) {
										msg("알림", "아이디 중복체크를 진행해주세요", "error");
										event.preventDefault();
										return false;
									}

									if (!checkObj.dinnerPw
											|| !checkObj.dinnerPwConfirm) {
										msg("알림", "비밀번호를 확인해주세요", "error");
										event.preventDefault();
										return false;
									}

									// 영업 시작 시간과 종료 시간 값 가져오기
									const openTime = $('#dinnerOpen').val(); // HH:mm 형식
									const closeTime = $('#dinnerClose').val(); // HH:mm 형식

									// HH:mm -> HHmm 형식으로 변환
									const formattedOpenTime = openTime.replace(
											':', '');
									const formattedCloseTime = closeTime
											.replace(':', '');

									// 변환된 값으로 input 값을 업데이트
									$('#dinnerOpen').val(formattedOpenTime);
									$('#dinnerClose').val(formattedCloseTime);
								});

					});
</script>
</html>
