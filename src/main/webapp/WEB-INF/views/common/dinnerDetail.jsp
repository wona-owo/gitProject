<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>dinnerDetail</title>
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
    
    #resBtn:click{
    	display : inline-block;
    }

    .btn-primary{
    	margin: 10px;
    }

</style>

</head>
<body>
	<div>
		<jsp:include page="/WEB-INF/views/common/header.jsp" />

		<%-- 레스토랑 상세 정보 섹션 --%>
		<main>
			<%-- TODO form tag 가 없었음 action, method 지정 필요 --%>
			
				<section class="restaurant-detail-header">
					<div class="dinner-main-img">
						<img src="/resources/images/jungsik.jpg" id="main-img"
							alt="Restaurant Image" />
					</div>
					<div class="restaurant-detail">
						<div class="restaurant-detail-container">
							<div class="restaurant-category">${food.foodCat}</div>
							<div class="restaurant-name">${dinner.dinnerName}</div>
						</div>
						<div class="restaurant-info">
							<i class="fa-solid fa-location-dot"></i><span id="location">주소 : ${dinner.dinnerAddr}</span><br />
							<i class="fa-solid fa-clock"></i><span id="time">
								영업시간 : ${dinner.dinnerOpen} ~ ${dinner.dinnerClose}</span><br /> <i
								class="fa-solid fa-phone"></i> <span id="phone">전화번호 : ${dinner.dinnerPhone}</span><br />
							<i class="fa-solid fa-car"></i> <span id="parking"> ${dinner.dinnerParking}</span><br />
						</div>
						
						<button type="button" id="reserveBtn" class="btn-primary" >예약</button>
						<%--
						<div id="resDetail" style="margin-top: 20px;"></div>
						<div class=resBtn id="resOption" style="display : none;">
							<label for="resDate">날짜 선택:</label>
    						<input type="date" id="resDate" name="bookDate" required>
							<button type="button" class="btn-primary" id="mBookCnt">-</button>
							<span id="bookCnt">0</span>
							<button type="button" class="btn-primary" id="pBookCnt">+</button>
							<ul id="resTime" class="time-list"></ul>
						</div>
						 --%>
						
					</div>

				</section>

				<%-- 탭 메뉴와 콘텐츠 섹션 --%>
				<section class="restaurant-detail-info">
					<div class="restaurant-detail-menu">
						<input id="information" type="radio" name="tab-item" checked /> <label
							class="tab-item" for="information"
							onclick="showContent('information-content')">정보</label> <input
							id="menu" type="radio" name="tab-item" /> <label
							class="tab-item" for="menu" onclick="showContent('menu-content')">메뉴</label>
						<input id="review" type="radio" name="tab-item" /> <label
							class="tab-item" for="review"
							onclick="showContent('review-content')">리뷰</label> <input
							id="picture" type="radio" name="tab-item" /> <label
							class="tab-item" for="picture"
							onclick="showContent('picture-content')">사진</label> <input
							id="around" type="radio" name="tab-item" /> <label
							class="tab-item" for="around"
							onclick="showContent('around-content')">주변</label>
					</div>

					<%-- 탭 콘텐츠 --%>
					<div class="restaurant-detail-content">
						<div class="tab-content active" id="information-content">정보
							콘텐츠</div>
						<div class="tab-content" id="menu-content">메뉴 콘텐츠</div>
						<div class="tab-content" id="review-content">리뷰 콘텐츠</div>
						<div class="tab-content" id="picture-content">사진 콘텐츠</div>
						<div class="tab-content" id="around-content">주변 콘텐츠</div>
					</div>
				</section>
			
		</main>

		
		<jsp:include page="/WEB-INF/views/common/footer.jsp" />
	</div>
	<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
	<script>
      function showContent(contentId) {
        // 모든 탭 콘텐츠 숨기기
        const contents = document.querySelectorAll(".tab-content");
        contents.forEach((content) => content.classList.remove("active"));
		
        // 선택된 콘텐츠만 보이도록 설정
        document.getElementById(contentId).classList.add("active");
      }
      
    	  
      const parking = document.getElementById('parking');
      parking.innerHTML = "";
      if('${dinner.dinnerParking}' === 'y'){
    	  parking.innerHTML = "주차가능"
      }else {
    	  parking.innerHTML = "주차자리없음"
      }
      
      let bookCount = 0;

      document.getElementById('mBookCnt').addEventListener('click', function() {
          if (bookCount > 0) {
              bookCount--;
              bookCntEl.innerText = bookCount;
          }
      });

      document.getElementById('pBookCnt').addEventListener('click', function() {
          bookCount++;
          bookCntEl.innerText = bookCount;
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
	        const formattedHour = hourString.slice(0, 2) + ':' + hourString.slice(2); // '0900' -> '09:00'

	        liEl.innerText = formattedHour; // 포맷된 시간 출력
	        
	        liEl.className = 'time-button';
	        liEl.addEventListener('click', function() {
	            if (selectedTime) {
	                selectedTime.classList.remove('selected');
	            }
	            liEl.classList.add('selected');
	            selectedTime = liEl; // 선택된 시간 저장
	        });

	        ulEl.appendChild(liEl); // ul 요소에 li 요소 추가
	    }
	    
	    document.getElementById('reserveBtn').addEventListener('click', function() {
	        const resEl = document.getElementById('resDetail');
	        
	        // 선택된 시간이 없거나 인원 수가 0명 이하일 때 안내 메시지 출력
	        /*
	        if (!selectedTime) {
	        	resEl.innerText = '시간을 선택해 주세요.';
	            return;
	        }
	        if (bookCount <= 0) {
	        	resEl.innerText = '인원 수를 선택해 주세요.';
	            return;
	        }
	        */
	    });
	    
	    document.getElementById('reserveBtn').addEventListener('click', function() {
	        const resOption = document.getElementById('resOption');
	        if(resOption.style.display === 'none'){
	        	resOption.style.display = 'block'; // 예약 옵션 div를 표시
	        }else{
	        	resOption.style.display = 'none';
	        }
	    });
	    
	    
	    const form = document.createElement('form');
        form.method = 'POST';
        form.action = '/dinner/bookDinner'; // 서블릿 경로로 변경

        // 인원수 값 전달
        const bookCountInput = document.createElement('input');
        bookCountInput.type = 'hidden';
        bookCountInput.name = 'bookCnt';
        bookCountInput.value = bookCount;
        form.appendChild(bookCountInput);

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
        
        let resDate = new Date();
    
	    

    </script>

</body>
</html>