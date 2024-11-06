<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>오늘 메뉴 픽해볼까? Menu Pick!</title>
<link rel="stylesheet" href="/resources/css/default.css" />
<script src="https://dapi.kakao.com/v2/maps/sdk.js?appkey=1ca3b7a5df74e4b03123d97229e9ebde&libraries=services"></script>
</head>
<body>
	<div>
		<jsp:include page="/WEB-INF/views/common/header.jsp" />
		<div class="wrap">
			<main>
				<div class="search-box">
					<input type="text" class="search-input" placeholder="식당 이름, 지역 등을 입력하세요" />
					<button type="submit" class="search-button">검색</button>
				</div>
				<section class="map-focus-section">
					<div class="map-container">
						<div id="map" style="width:100%;height:400px;"></div>
					</div>
					<div class="focus-info" id="info">
						<h2 class="focus-title" id="info-title">식당 이름</h2>
						<p class="focus-description" id="info-description">식당 정보나 사진이 여기 뜰 예정이에용</p>
					</div>
				</section>
			</main>
		</div>
		<jsp:include page="/WEB-INF/views/common/footer.jsp" />
	</div>

	<script>
        document.addEventListener("DOMContentLoaded", function () {
            const mapContainer = document.getElementById("map");
            const mapOptions = {
                center: new kakao.maps.LatLng(37.5665, 126.978),
                level: 3,
            };
            const map = new kakao.maps.Map(mapContainer, mapOptions);

            // 사용자 정의 마커 이미지 설정
            const imageSrc = '<%= request.getContextPath() %>/resources/images/pin.png';
            const imageSize = new kakao.maps.Size(64, 64);
            const imageOption = { offset: new kakao.maps.Point(32, 64) };
            const markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption);

            // 지오코더 객체 생성
            const geocoder = new kakao.maps.services.Geocoder();

            // 주소 데이터를 가져와 지오코딩 후 마커 표시
            fetch('<%= request.getContextPath() %>/locations')
                .then(response => response.json())
                .then(data => {
                    data.forEach(location => {
                        // 지오코딩을 통해 주소를 좌표로 변환
                        geocoder.addressSearch(location.address, function(result, status) {
                            if (status === kakao.maps.services.Status.OK) {
                                const coords = new kakao.maps.LatLng(result[0].y, result[0].x);

                                // 마커 생성 및 설정
                                const marker = new kakao.maps.Marker({
                                    position: coords,
                                    map: map,
                                    image: markerImage
                                });

                                // 마커 클릭 시 정보창 및 애니메이션 업데이트
                                kakao.maps.event.addListener(marker, "click", function () {
                                    document.getElementById("info-title").innerText = location.name;
                                    document.getElementById("info-description").innerText = location.description;
                                    animateMarkerDrop(marker);
                                });
                            } else {
                                console.error("지오코딩 실패:", location.address);
                            }
                        });
                    });
                })
                .catch(error => console.error("주소 데이터 가져오기 실패:", error));
        });

        // 클릭 시 마커가 내려찍히는 애니메이션 함수
        function animateMarkerDrop(marker) {
            let originalPosition = marker.getPosition();
            let dropHeight = 20; // 내려가는 높이
            let duration = 300; // 내려찍는 애니메이션 시간
            let start = null;

            function dropAnimationStep(timestamp) {
                if (!start) start = timestamp;
                let progress = timestamp - start;
                let amount = Math.min(progress / duration, 1); // 진행률 계산

                // 내려찍히는 동작: 내려갔다가 원위치로 돌아옴
                let displacement = dropHeight * Math.sin(amount * Math.PI); // 내려갔다가 올라오는 효과

                let newPosition = new kakao.maps.LatLng(
                    originalPosition.getLat() - displacement / 100000, // 미세 조정으로 자연스러운 효과
                    originalPosition.getLng()
                );
                marker.setPosition(newPosition);

                if (amount < 1) {
                    requestAnimationFrame(dropAnimationStep); // 애니메이션 계속
                } else {
                    marker.setPosition(originalPosition); // 애니메이션 종료 후 원래 위치
                }
            }

            requestAnimationFrame(dropAnimationStep);
        }
    </script>
</body>
</html>
