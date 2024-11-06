<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>오늘 메뉴 픽해볼까? Menu Pick!</title>
<link rel="stylesheet" href="/resources/css/default.css" />
<script
	src="https://dapi.kakao.com/v2/maps/sdk.js?appkey=1ca3b7a5df74e4b03123d97229e9ebde"></script>
</head>
<body>
	<div>
		<jsp:include page="/WEB-INF/views/common/header.jsp" />

		
		<div><a href="/member/dinnerCalendar">Goes to dinnerCalendar.jsp</a></div>

		<div class="wrap">
			<!-- 메인 콘텐츠 -->
			<main>
				<!-- 검색창 -->
				<div class="search-box">
					<input type="text" class="search-input"
						placeholder="식당 이름, 지역 등을 입력하세요" />
					<button type="submit" class="search-button">검색</button>
				</div>

				<!-- 지도 및 포커스 식당 정보 -->
				<section class="map-focus-section">
					<!-- 지도 -->
					<div class="map-container">
						<div id="map"></div>
					</div>

					<!-- 클릭된 핀의 정보 표시 -->
					<div class="focus-info" id="info">
						<h2 class="focus-title" id="info-title">식당 이름</h2>
						<p class="focus-description" id="info-description">식당 정보나 사진이
							여기 뜰 예정이에용</p>
					</div>
				</section>

				<!-- 요즘 많이 찾는 카테고리: 검색 데이터 따라 상위 키워드 자동 삽입 예정-->
				<section class="trending-section">
					<h2 class="section-title">요즘 많이 찾는</h2>
					<div class="category-container">
						<div class="category-item">#한식</div>
						<div class="category-item">#일식</div>
						<div class="category-item">#양식</div>
						<div class="category-item">#중식</div>
					</div>
				</section>

				<!-- 내 근처 맛집 섹션 -->
				<section class="nearby-section">
					<h2 class="section-title">내 근처 맛집</h2>
					<div class="nearby-content">스크롤해서 식당 정보 계속 노출</div>
				</section>

				<!-- 상단으로 이동 버튼 -->
				<div class="top-button">
					<a href="#top" class="top">TOP ↑</a>
				</div>
			</main>
		</div>
		<jsp:include page="/WEB-INF/views/common/footer.jsp" />
	</div>

	<script>
      // 식당 데이터 (예시 데이터) - 추후 DB 연동 필요
      const restaurantData = [
        { name: "우육면관 청계천점", description: "서울시 종로구 청계천로 1", lat: 37.5665, lng: 126.978, },
        { name: "송화산시도삭면 2호점", description: "서울시 중구 을지로 2", lat: 37.5675, lng: 126.977, },
        { name: "팀호완", description: "서울시 용산구 이태원로 3", lat: 37.5705, lng: 126.982, },
        { name: "대가방 본점", description: "서울시 강남구 강남대로 4", lat: 37.5645, lng: 126.989, },
      ];

      // DOMContentLoaded 이벤트 이후에 초기화되도록 설정
      document.addEventListener("DOMContentLoaded", function () {
        const mapContainer = document.getElementById("map");
        const mapOptions = {
          center: new kakao.maps.LatLng(37.5665, 126.978), // 초기 중심 좌표
          level: 3, // 지도 확대 레벨
        };

        const map = new kakao.maps.Map(mapContainer, mapOptions);

        // 핀(마커)을 추가하고 클릭 이벤트 설정
        restaurantData.forEach((restaurant) => {
          const markerPosition = new kakao.maps.LatLng(
            restaurant.lat,
            restaurant.lng
          );
          const marker = new kakao.maps.Marker({
            position: markerPosition,
            map: map,
          });

          // 마커 클릭 시 정보 업데이트
          kakao.maps.event.addListener(marker, "click", function () {
            document.getElementById("info-title").innerText = restaurant.name;
            document.getElementById("info-description").innerText =
              restaurant.description;
          });
        });
      });
    </script>

</body>
</html>
