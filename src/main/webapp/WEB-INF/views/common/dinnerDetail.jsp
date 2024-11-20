<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    // 세션에서 로그인 상태 확인
    Boolean isLogIn = (session.getAttribute("loginMember") != null);
%>
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

#resBtn:click {
	display: inline-block;
}

.btn-primary {
	margin: 10px;
}

/*------------리뷰 작성 모듈 여기부터------------*/
/* 기본 설정 */
* {
	box-sizing: border-box; /* 모든 요소에 border-box 적용 */
}

/* 모달 배경 스타일 */
.modal {
	display: none;
	position: fixed;
	z-index: 1000;
	left: 0;
	top: 0;
	width: 100%;
	height: 100%;
	background-color: rgba(0, 0, 0, 0.6);
	display: flex;
	align-items: center;
	justify-content: center;
}

/* 모달 컨텐츠 스타일 */
.modal-content {
	width: 90%;
	max-width: 600px;
	background-color: #fff;
	padding: 30px;
	border-radius: 10px;
	box-shadow: 0 4px 12px rgba(0, 0, 0, 0.2);
	position: relative;
	text-align: center;
}

/* 닫기 버튼 스타일 */
.close-btn {
	position: absolute;
	top: 15px;
	right: 15px;
	font-size: 20px;
	cursor: pointer;
	color: #f40;
	font-weight: bold;
	transition: color 0.3s;
}

.close-btn:hover {
	color: #c30;
}

/* 버튼 공통 스타일 */
button {
	padding: 12px 20px;
	font-size: 16px;
	border: none;
	border-radius: 5px;
	cursor: pointer;
	transition: background-color 0.3s;
}

/* 리뷰 작성 버튼 스타일 */
#openModalBtn {
	background-color: #f40;
	color: #fff;
	margin: 20px auto;
	display: block;
}

#openModalBtn:hover {
	background-color: #c30;
}

/* 제출 버튼 스타일 */
.submit-btn {
	background-color: #f40;
	color: #fff;
	width: 100%;
}

.submit-btn:hover {
	background-color: #c30;
}

/* 입력 폼 스타일 */
.form-group {
	margin-bottom: 20px;
	text-align: left;
}

.form-group label {
	display: block;
	font-weight: bold;
	margin-bottom: 5px;
	color: #333;
}

.form-group input[type="text"], .form-group textarea {
	width: 100%;
	padding: 15px;
	font-size: 16px;
	border: 1px solid #ddd;
	border-radius: 5px;
	box-sizing: border-box;
	transition: border-color 0.3s;
}

textarea {
	height: 200px;
	resize: none;
}

#charCount {
	display: block;
	margin-top: 5px;
	font-size: 14px;
	color: #555;
}
/*------------리뷰 작성 모듈 여기까지---------------*/
</style>

</head>
<body>
	<div>
		<jsp:include page="/WEB-INF/views/common/header.jsp" />

		<%-- 레스토랑 상세 정보 섹션 --%>
		<main>
			<%-- TODO form tag 가 없었음 action, method 지정 필요 --%>
				<section class="restaurant-detail-header">
			<form action="/dinner/settingsfrm" method="get">
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
							<i class="fa-solid fa-location-dot"></i><span id="location">주소
								: ${dinner.dinnerAddr}</span><br /> <i class="fa-solid fa-clock"></i><span
								id="time"> 영업시간 : ${dinner.dinnerOpen} ~
								${dinner.dinnerClose}</span><br /> <i class="fa-solid fa-phone"></i> <span
								id="phone">전화번호 : ${dinner.dinnerPhone}</span><br /> <i
								class="fa-solid fa-car"></i> <span id="parking">
								${dinner.dinnerParking}</span><br />
						</div>

						<button type="button" id="reserveBtn" class="btn-primary"
							onclick="resBtn()">예약</button>

						<div id="resDetail" style="margin-top: 20px;"></div>
						<div class=resBtn id="resOption" style="display: none;">
							<label for="resDate">날짜 선택:</label> <input type="date"
								id="resDate" name="bookDate" required>
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
						id="menu" type="radio" name="tab-item" /> <label class="tab-item"
						for="menu" onclick="showContent('menu-content')">메뉴</label> <input
						id="review" type="radio" name="tab-item" /> <label
						class="tab-item" for="review"
						onclick="showContent('review-content')">리뷰</label> <input
						id="picture" type="radio" name="tab-item" /> <label
						class="tab-item" for="picture"
						onclick="showContent('picture-content')">사진</label> <input
						id="around" type="radio" name="tab-item" />
				</div>

				<%-- 탭 콘텐츠 --%>
				<div class="restaurant-detail-content">
					<div class="tab-content active" id="information-content">정보 콘텐츠</div>
					<jsp:include page="/WEB-INF/views/common/menu.jsp" />		
					<div class="tab-content" id="review-content">리뷰 콘텐츠
						<div>
							<c:import url="/WEB-INF/views/dinner/dinnerWriteReview.jsp">
							    <c:param name="dinnerName" value="${dinner.dinnerName}" />
							    <c:param name="dinnerNo" value="${dinner.dinnerNo}" />
							    <c:param name="memberNo" value="${member.memberNo}" />
							</c:import>
						</div>
						<div>
							<c:import url="/dinner/review">
							    <c:param name="dinnerNo" value="${dinner.dinnerNo}" />
							</c:import>
						</div>
					</div>
					<div class="tab-content" id="picture-content">사진 콘텐츠</div>
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
      
      let isLogIn = <%= isLogIn %>;
      function resBtn(){
    	  if(isLogIn = null){
    		  event.preventDefault();
    		  msg("알림","로그인 후 이용하세요","warning");
    	  }else{
    		  window.open("/member/reservationFrm?dinnerNo=${dinner.dinnerNo}&memberNo=${member.memberNo}",'a','width=700, height=700, scrollbars=yes, resizable=no');	  
    	  }
      }

    </script>

</body>
</html>