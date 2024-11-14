<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>식당 정보 등록</title>
<link rel="stylesheet" href="/resources/css/default.css">
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
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
    width: 20%; /* 우편번호 입력란 너비 48% */
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
    width: 10%; /* 우편번호 찾기 버튼도 48%로 설정 */
    display: inline-block;
    padding: 12px 20px;
    background-color: #f40; /* 버튼 색상 */
    color: white;
    border: none;
    border-radius: 8px;
    cursor: pointer;
    font-size: 14px;
    text-align: center;
    margin-left: 0.5%; /* 우편번호와 버튼 간의 간격 */
}

#zipp_btn:hover {
    background-color: #d93636; /* 버튼 호버 시 색상 */
}

/* 기본 주소와 상세 주소 입력란을 한 줄에 나란히 배치 */
#UserAdd1 {
    width: 60%; /* 두 주소 입력란을 각각 48%로 설정 */
    display: inline-block;
    padding: 12px 15px;
    border: 1px solid #ddd;
    border-radius: 8px;
    background-color: #fafafa;
    font-size: 14px;
    color: #333;
    transition: border-color 0.3s, box-shadow 0.3s;
    box-sizing: border-box;
    margin-top : 1%;
    margin-right: 0.5%;
}

#UserAdd2 {
    width: 39%; /* 두 주소 입력란을 각각 48%로 설정 */
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
@media (max-width: 768px) {
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
        width: 100%; /* 화면이 좁아지면 100%로 설정하여 세로로 표시 */
        margin-right: 0;
        margin-left: 0;
    }

    #zipp_btn {
        width: 100%; /* 버튼도 세로로 배치 */
        margin-top: 15px; /* 버튼과 입력란 사이에 간격 추가 */
    }
}
</style>
</head>
<body>
	<div class="wrap">
		<h1 class="page-title">식당 정보 등록</h1>
		<form action="${pageContext.request.contextPath}/dinnerJoin" method="post">
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

			<!-- 영업 시작 시간 -->
			<div class="form-group">
				<label for="dinnerOpen">영업 시작 시간</label> <input type="text"
					id="dinnerOpen" name="dinnerOpen" required
					pattern="^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$"
					placeholder="예: 09:00 (HH:mm)">
			</div>

			<!-- 영업 종료 시간 -->
			<div class="form-group">
				<label for="dinnerClose">영업 종료 시간</label> <input type="text"
					id="dinnerClose" name="dinnerClose" required
					pattern="^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$"
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

			<!-- 아이디 -->
			<div class="form-group">
				<label for="dinnerId">아이디</label> <input type="text" id="dinnerId"
					name="dinnerId" required maxlength="20" placeholder="아이디를 입력하세요.">
			</div>

			<!-- 비밀번호 -->
			<div class="form-group">
				<label for="dinnerPw">비밀번호</label> <input type="password"
					id="dinnerPw" name="dinnerPw" required minlength="8" maxlength="60"
					placeholder="비밀번호를 입력하세요.">
			</div>

			<!-- 제출 버튼 -->
			<button type="submit" class="submit-btn">등록</button>
		</form>

	</div>
</body>

    <!-- CDN 방식 사용 -->
    <script>
    function execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업을 통한 검색 결과 항목 클릭 시 실행
                var addr = ''; // 주소_결과값이 없을 경우 공백 
                var extraAddr = ''; // 참고항목

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 도로명 주소를 선택
                    addr = data.roadAddress;
                } else { // 지번 주소를 선택
                    addr = data.jibunAddress;
                }

                if(data.userSelectedType === 'R'){
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                } else {
                    document.getElementById("UserAdd1").value = '';
                }

                // 선택된 우편번호와 주소 정보를 input 박스에 넣는다.
                document.getElementById('zipp_code_id').value = data.zonecode;
                document.getElementById("UserAdd1").value = addr;
                document.getElementById("UserAdd1").value += extraAddr;
                document.getElementById("UserAdd2").focus(); // 우편번호 + 주소 입력이 완료되었음으로 상세주소로 포커스 이동
            }
        }).open();
    }
	</script>
</html>
