<%@page import="com.menupick.member.model.vo.Member"%>
<%@page import="com.menupick.dinner.vo.Book"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
Member member = (Member) request.getAttribute("loginMember");
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>memberReservation.jsp</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<style>
form {
	max-width: 600px;
	margin: 0 auto;
	padding: 20px;
	border: 1px solid #ccc;
	border-radius: 8px;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
	background-color: #f9f9f9;
}

/* 날짜 선택 */
#resDate {
	display: block;
	margin: 10px 0;
	padding: 8px;
	width: 100%;
	box-sizing: border-box;
	border: 1px solid #ddd;
	border-radius: 4px;
}

/* 버튼 스타일 */
button.btn-primary {
	padding: 8px 12px;
	margin: 5px;
	border: none;
	border-radius: 4px;
	background-color: #f40;
	color: white;
	cursor: pointer;
	font-size: 14px;
}

button.btn-primary:focus {
	background-color: #0056b3;
}

/* 인원수 버튼 및 라벨 */
#cnt {
	display: inline-block;
	margin: 0 10px;
	font-size: 16px;
	font-weight: bold;
}

/* 시간 선택 리스트 */
ul.time-list {
	display: flex;
	flex-wrap: wrap;
	list-style: none;
	padding: 0;
	margin: 20px 0;
	gap: 10px;
}

ul.time-list li {
	padding: 10px;
	border: 1px solid #ddd;
	border-radius: 4px;
	background-color: #ffffff;
	cursor: pointer;
	text-align: center;
	width: calc(25% - 10px); /* 4개 한 줄 배치 */
	box-sizing: border-box;
}

ul.time-list li:focus {
	background-color: #f40;
	border-color: #007BFF;
}

ul.time-list li.selected {
	background-color: #f40;
	color: white;
	border-color: #0056b3;
}

/* 예약 확인 버튼 */
button[type="submit"] {
	display: block;
	margin: 20px auto 0;
	padding: 10px 20px;
	font-size: 16px;
	font-weight: bold;
	background-color: #000;
	border-color: #000;
	border-radius: 4px;
}

button[type="submit"]:hover {
	background-color: #f40;
}

ul.time-list li.disabled {
	background-color: #e0e0e0; /* 비활성화된 시간의 배경 색 */
	color: #888; /* 비활성화된 시간의 글자 색 */
	cursor: not-allowed; /* 마우스 커서를 'not-allowed'로 변경 */
}

#dinnerName {
	display: block;
	text-align: center;
	padding: 10px 20px;
	font-size: 16px;
	font-weight: bold;
	background-color: #f40;
	align-items: center;
	border-radius: 4px;
	font-family: arial;
	color: white;
}
</style>
</head>
<body>
	<form action="/member/reservation" method="get">
		<input id="dinnerNo" type="hidden" name="dinnerNo"
			value="${dinner.dinnerNo}"> <input type="hidden"
			id="memberNo" name="memberNo" value="${loginMember.memberNo}">
		<span id="dinnerName">${dinner.dinnerName}</span>
		<div id="resDetail" style="margin-top: 20px;"></div>
		<div class=resBtn id="resOption">
			<label for="resDate">날짜 선택:</label> <input type="date" id="resDate"
				name="bookDate" required> <label id="person">인원수 :</label>
			<button type="button" class="btn-primary" id="mBookCnt">-</button>
			<label id="cnt" for="bookCnt">0</label> <input type="hidden"
				name="bookCnt" id="cntInput" value="0">
			<button type="button" class="btn-primary" id="pBookCnt">+</button>
			<ul id="resTime" class="time-list"></ul>
			<input type="hidden" name="bookTime" id="timeInput">
			<button type="submit" id="btn" class="btn-primary">확인</button>
		</div>
	</form>

	<script>
		let maxCnt = '${dinner.dinnerMaxPerson}';
		let minCnt = 0;

		// 제출 버튼 DOM 요소
		const submitBtn = document.getElementById("btn");

		// 초기 버튼 상태 설정
		submitBtn.disabled = true;

		//인원수 감소
		document.getElementById("mBookCnt").addEventListener("click",
				function() {
					const cntInput = document.getElementById("cntInput");
					const cnt = document.getElementById("cnt");
					let count = parseInt(cntInput.value);
					if (count > minCnt) {
						count--; // 인원수 감소
						cntInput.value = count;//서버 전송 값
						cnt.innerText = count;//화면 출력 값
						submitBtnFunc(count);
					} else {
						alert("인원수는 " + '\${minCnt}' + " 이상 입력바랍니다.");
					}
				});
		//인원수 증가
		document.getElementById("pBookCnt").addEventListener("click",
				function() {
					const cntInput = document.getElementById("cntInput");
					const cnt = document.getElementById("cnt");
					let count = parseInt(cntInput.value);
					if (count < maxCnt) {
						count++; // 인원수 증가
						cntInput.value = count;
						cnt.innerText = count;
						submitBtnFunc(count);
					} else {
						alert("식당 수용할 인원수 이상입니다.");
					}
				});

		function submitBtnFunc(count) {
			submitBtn.disabled = count === minCnt; // 인원수가 minCnt이면 비활성화, 1 이상이면 활성화
		}

		const openTime = parseInt('${dinner.dinnerOpen}'.replace(":", ""), 10);
		const closeTime = parseInt('${dinner.dinnerClose}'.replace(":", ""), 10);
		const interval = 100; // 1시간 간격 (100 단위)
		let selectedTime = null; // 선택 시간값 초기호

		// 예약된 시간 조회 및 UI 비활성화
		function resTime(bookDate) {
			const dinnerNo = document.getElementById("dinnerNo").value;

			if (bookDate) {
				$.ajax({
					url : '/member/checkReservedTimes',
					type : 'GET',
					data : {
						bookDate : bookDate,
						dinnerNo : dinnerNo
					},
					dataType : 'json',
					success : function(reservedTimes) {
						// 시간대 비활성화
						document.querySelectorAll('.time-button').forEach(
								function(timeSlot) {
									const time = timeSlot
											.getAttribute('data-time');
									if (reservedTimes.includes(time)) {
										timeSlot.classList.add('disabled');
										timeSlot.removeEventListener('click',
												clickTime);
									} else {
										timeSlot.classList.remove('disabled');
										timeSlot.addEventListener('click',
												clickTime);
									}
								});
					},
					error : function(xhr, status, error) {
						console.error('AJAX 오류:', error);
						alert("예약된 시간을 불러오는 데 실패했습니다.");
					}
				});
			}
		}
		// 시간 클릭 핸들러
		function clickTime(e) {
			const liEl = e.target;
			if (selectedTime) {
				selectedTime.classList.remove("selected");
			}
			liEl.classList.add("selected");
			selectedTime = liEl;
			// 선택된 시간을 hidden input에 저장
			document.getElementById("timeInput").value = liEl
					.getAttribute("data-time");
		}
		// 시간대 생성
		const ulEl = document.getElementById("resTime");
		for (let hour = openTime; hour <= closeTime; hour += interval) {
			const liEl = document.createElement("li");
			const hourString = hour.toString().padStart(4, "0");
			const oneHour = hourString.slice(0, 2) + ":" + hourString.slice(2);

			liEl.innerText = oneHour; // 시간 표시
			liEl.className = "time-button";
			liEl.setAttribute("data-time", hourString);

			liEl.addEventListener("click", clickTime);
			ulEl.appendChild(liEl);
		}

		// 날짜 변경 시 예약된 시간 조회
		document.getElementById("resDate").addEventListener("change",
				function() {
					const bookDate = this.value; // 'YYYY/MM/DD' 형식
					resTime(bookDate);
				});
		// 초기화: 오늘 날짜로 예약된 시간 조회
		const today = new Date();
		const yyyy = today.getFullYear();//주어진 날짜 현지시간 기준으로 반환
		const mm = String(today.getMonth() + 1).padStart(2, "0");// 2글자로 day 반환 2자리면 ex)11
		const dd = String(today.getDate()).padStart(2, "0");// 2글자로 day 반환 2자리면 ex)21
		document.getElementById("resDate").value = `${yyyy}/${mm}/${dd}`;//DB에 값 출력시
	</script>
</body>
</html>