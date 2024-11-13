<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
</style>
</head>
<body>
	<div>
		<jsp:include page="/WEB-INF/views/common/header.jsp" />
		<%-- 탭 메뉴와 콘텐츠 섹션 --%>
		<div class="restaurant-detail-content">
			<div class="tab-content active" id="information-content">정보 콘텐츠</div>
			<div class="tab-content" id="menu-content">메뉴 콘텐츠</div>
			<div class="tab-content" id="review-content">리뷰 콘텐츠</div>
			<div class="tab-content" id="picture-content">사진 콘텐츠</div>
			<div class="tab-content" id="around-content">주변 콘텐츠</div>
		</div>
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
			<%-- 탭 콘텐츠 --%>

		</section>

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
						<div class="restaurant-category">${dinnerDetail.foodCat}</div>
						<div class="restaurant-name">${dinnerDetail.dinnerName}</div>
					</div>
					<div class="restaurant-info">
						<i class="fa-solid fa-location-dot"></i> <span id="location">주소:
							${dinnerDetail.dinnerAddr}</span><br /> <i class="fa-solid fa-clock"></i><span
							id="time"> 영업시간 : ${dinnerDetail.dinnerOpen} ~
							${dinnerDetail.dinnerClose}</span><br /> <i class="fa-solid fa-phone"></i>
						<span id="phone">전화번호 : ${dinnerDetail.dinnerPhone}</span><br />
						<i class="fa-solid fa-car"></i> <span id="parking">
							${dinnerDetail.dinnerParking}</span><br />
					</div>
				</div>
			</section>
		</main>
		<jsp:include page="/WEB-INF/views/common/footer.jsp" />
	</div>
</body>
</html>