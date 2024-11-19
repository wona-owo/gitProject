<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
/* 시간표 버튼 스타일 */
.time-list {
	list-style: none;
	padding: 0;
	display: flex;
	flex-wrap: wrap;
	gap: 10px;
}

.time-list li {
	display: inline-block;
	padding: 10px 15px;
	background-color: #f30;
	color: #fff;
	border-radius: 5px;
	cursor: pointer;
	text-align: center;
	transition: background-color 0.3s;
}

.time-list li.selected {
	background-color: #f30;
}

.time-list li:hover {
	background-color: #0056b3;
}

#resBtn:click {
	display: inline-block;
}

.btn-primary {
	margin: 10px;
}

.time-button {
	display: inline-block;
	padding: 5px 10px;
	border: 1px solid #ddd;
	margin: 5px;
	cursor: pointer;
}

.time-button.selected {
	background-color: #007bff;
	color: white;
}
</style>
</head>
<body>
	<form action="/member/reservation" method="get">
		<input id="memberNo" type="hidden" name="${dinner.dinnerNo}" value="${dinner.dinnerNo}">
		<input type="hidden" name="${member.memberId}">
		
		<div id="resDetail" style="margin-top: 20px;"></div>
		<div class=resBtn id="resOption">
			<label for="resDate">날짜 선택:</label> <input type="date" id="resDate"
				name="bookDate" required>
			<button type="button" class="btn-primary" id="mBookCnt">-</button>
			<label id="cnt" for="bookCnt">0</label> <input type="hidden"
				name="bookCnt" id="cntInput" value="0">
			<button type="button" class="btn-primary" id="pBookCnt">+</button>
			<ul id="resTime" class="time-list"></ul>
				<input type="hidden" name="bookTime" id="timeInput">
			<button type="submit" class="btn-primary">확인</button>
		</div>
	</form>
	<script>
		document.getElementById("mBookCnt").addEventListener("click",
				function() {
					const cntInput = document.getElementById("cntInput");
					const cnt = document.getElementById("cnt");
					let count = parseInt(cntInput.value) || 0;
					if (count > 0)
						count--; // 인원수 감소
					cntInput.value = count;
					cnt.innerText = count;
				});

		document.getElementById("pBookCnt").addEventListener("click",
				function() {
					const cntInput = document.getElementById("cntInput");
					const cnt = document.getElementById("cnt");
					let count = parseInt(cntInput.value) || 0;
					count++; // 인원수 증가
					cntInput.value = count;
					cnt.innerText = count;
				});
			
		const openTime = parseInt('${dinner.dinnerOpen}', 10); // 예: '0900' -> 900
		const closeTime = parseInt('${dinner.dinnerClose}', 10); // 예: '1800' -> 1800

		const interval = 100; // 1시간 간격 (100 단위)

		const today = new Date();
		const yyyy = today.getFullYear();
		const mm = String(today.getMonth() + 1).padStart(2, '0'); // 월은 0부터 시작하므로 +1
		const dd = String(today.getDate()).padStart(2, '0');
		const minDate = `${yyyy}-${mm}-${dd}`;
		
		let selectedTime = null;
		//ul태그 지정
		const ulEl = document.getElementById('resTime');
		//인원수(span) 태그 지정
		const bookCntEl = document.getElementById('bookCnt');

		for (let hour = openTime; hour <= closeTime; hour += interval) {
			//li 태그 생성
			const liEl = document.createElement('li');

			// 시간을 4자리 문자열 형식으로 변환 (0900, 1000 등)

			const hourString = hour.toString().padStart(4, '0');
			const oneHour = hourString.slice(0, 2) + ':'
					+ hourString.slice(2); // '0900' -> '09:00'

			liEl.innerText = oneHour; // 포맷된 시간 출력
			liEl.className = 'time-button';

			// 시간 클릭 시 선택된 시간을 hidden input에 저장
			liEl.addEventListener('click',function() {
				if (selectedTime) {
					selectedTime.classList.remove('selected');
				}
					liEl.classList.add('selected');
					selectedTime = liEl; // 선택된 시간 저장

					// 선택된 시간을 hidden input에 저장하여 서블릿으로 전송
					document.getElementById('timeInput').value = hourString;
			});

			ulEl.appendChild(liEl);
		   }
		
		

		/*
		 document.getElementById('reserveBtn').addEventListener('click',
		 function() {
		 const resEl = document.getElementById('resDetail');

		 // 선택된 시간이 없거나 인원 수가 0명 이하일 때 안내 메시지 출력
		
		 if (!selectedTime) {
		 resEl.innerText = '시간을 선택해 주세요.';
		 return;
		 }
		 if (bookCount <= 0) {
		 resEl.innerText = '인원 수를 선택해 주세요.';
		 return;
		 }
		
		 });

		 document.getElementById('reserveBtn').addEventListener('click',
		 function() {
		 const resOption = document.getElementById('resOption');
		 if (resOption.style.display === 'none') {
		 resOption.style.display = 'block'; // 예약 옵션 div를 표시
		 } else {
		 resOption.style.display = 'none';
		 }
		 });

		 const form = document.createElement('form');
		 form.method = 'POST';
		 form.action = '/dinner/bookDinner'; // 서블릿 경로로 변경


		 // 선택된 시간 값 전달
		 const timeInput = document.createElement('input');
		 timeInput.type = 'hidden';
		 timeInput.name = 'bookTime';
		 timeInput.value = selectedTime.innerText;
		 form.appendChild(timeInput);
		 console.log(timeInput);
		 // 폼을 문서에 추가하고 전송
		 document.body.appendChild(form);
		 form.submit();
		 */

		let resDate = new Date();
		 
		 
	</script>
</body>
</html>