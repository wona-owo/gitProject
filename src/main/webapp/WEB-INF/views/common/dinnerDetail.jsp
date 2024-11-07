<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>dinnerDetail</title>
</head>
<body>
	<div>
		<jsp:include page="/WEB-INF/views/common/header.jsp" />

		<!-- 레스토랑 상세 정보 섹션 -->
		<main>
			<section class="restaurant-detail-header">
				<div class="dinner-main-img">
					<img src="/resources/images/jungsik.jpg" id="main-img"
						alt="Restaurant Image" />
				</div>
				<div class="restaurant-detail">
					<div class="restaurant-detail-container">
						<div class="restaurant-category">${food.foodName}</div>
						<div class="restaurant-name">${dinner.dinnerName}</div>
					</div>
					<div class="restaurant-info">
						<i class="fa-solid fa-location-dot"></i><span id="location">${dinner.dinnerAddr}</span><br />
						<i class="fa-solid fa-clock"></i><span id="time">
							${dinner.dinnerOpen} ~ ${dinner.dinnerClose}</span><br /> <i
							class="fa-solid fa-phone"></i> <span id="phone">${dinner.dinnerPhone}</span><br />
						<i class="fa-solid fa-car"></i> <span id="parking">${dinner.dinnerParking}</span><br />
					</div>
					<button id="book" class="btn-primary">예약</button>
				</div>

			</section>

			<!-- 탭 메뉴와 콘텐츠 섹션 -->
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
						id="around" type="radio" name="tab-item" /> <label
						class="tab-item" for="around"
						onclick="showContent('around-content')">주변</label>
				</div>

				<!-- 탭 콘텐츠 -->
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

	<script>
      function showContent(contentId) {
        // 모든 탭 콘텐츠 숨기기
        const contents = document.querySelectorAll(".tab-content");
        contents.forEach((content) => content.classList.remove("active"));

        // 선택된 콘텐츠만 보이도록 설정
        document.getElementById(contentId).classList.add("active");
      }
    </script>

</body>
</html>