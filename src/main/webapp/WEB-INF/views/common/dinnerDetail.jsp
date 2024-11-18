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
			<form action="/dinner/dinnerDetailFrm" method="get">
			<input type="hidden" name="${dinner.dinnerNo}">
			<input type="hidden" name="memberNo">
					<div class="dinner-main-img">
						<img src="/resources/images/${dinner.dinnerNo}.jpg" id="main-img"
							alt="Restaurant Image" />
					</div>
					<div class="restaurant-detail">
						<div class="restaurant-detail-container">
							<div class="restaurant-category">${dinner.foodCat}</div>
							<div class="restaurant-name">${dinner.dinnerName}</div>
						</div>
						<div class="restaurant-info">
							<i class="fa-solid fa-location-dot"></i><span id="location">주소 : ${dinner.dinnerAddr}</span><br />
							<i class="fa-solid fa-clock"></i><span id="time">
								영업시간 : ${dinner.dinnerOpen} ~ ${dinner.dinnerClose}</span><br /> <i
								class="fa-solid fa-phone"></i> <span id="phone">전화번호 : ${dinner.dinnerPhone}</span><br />
							<i class="fa-solid fa-car"></i> <span id="parking"> ${dinner.dinnerParking}</span><br />
						</div>
						
						<button type="button" id="reserveBtn" class="btn-primary" onclick="resBtn()" >예약</button>
						
						<div id="resDetail" style="margin-top: 20px;"></div>
						<div class=resBtn id="resOption" style="display : none;">
							<label for="resDate">날짜 선택:</label>
    						<input type="date" id="resDate" name="bookDate" required>
							<button type="button" class="btn-primary" id="mBookCnt">-</button>
							<span id="bookCnt">0</span>
							<button type="button" class="btn-primary" id="pBookCnt">+</button>
							<ul id="resTime" class="time-list"></ul>
						</div>
						 
						
					</div>
				</form>
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
      
      function resBtn(){
    	  window.open("/member/reservation?dinnerNo=${dinner.dinnerNo}&memberId=${member.memberId}",'a','width=700, height=700, scrollbars=yes, resizable=no');
    			  
      }

    
	    

    </script>

</body>
</html>