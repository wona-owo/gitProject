<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>오늘 메뉴 픽해볼까? Menu Pick!</title>
<link rel="stylesheet" href="/resources/css/default.css" />
<script
	src="https://dapi.kakao.com/v2/maps/sdk.js?appkey=1ca3b7a5df74e4b03123d97229e9ebde&libraries=services"></script>
</head>
<body>
	<div>
		<jsp:include page="/WEB-INF/views/common/header.jsp" />
		<div class="wrap">
			<main>
				<div>
					<a href='member/dinnerCalendar'>goes to dinnerCalendar.jsp via
						DinnerCalendarFrmServlet.java</a>
				</div>

				<div class="search-box">
					<input type="text" class="search-input"
						placeholder="식당 이름, 지역 등을 입력하세요" />
					<button type="submit" class="search-button">검색</button>
				</div>
				<section class="map-focus-section">
					<div class="map-container">
						<div id="map" style="width: 100%; height: 400px;"></div>
					</div>
					<div class="focus-info" id="info">
						<h2 class="focus-title" id="info-title">식당 이름</h2>
						<p class="focus-description" id="info-description">식당 정보나 사진이
							여기 뜰 예정이에용</p>
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
            center: new kakao.maps.LatLng(37.5563, 126.9220), // 홍대 중심 좌표
            level: 3, // 초기 지도 확대 레벨
        };
        const map = new kakao.maps.Map(mapContainer, mapOptions);

        // 사용자 정의 마커 이미지 설정
        const imageSrc = '<%=request.getContextPath()%>/resources/images/pin.png';
        const imageSize = new kakao.maps.Size(64, 64);
        const imageOption = { offset: new kakao.maps.Point(32, 64) };
        const markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption);

        const geocoder = new kakao.maps.services.Geocoder(); // 지오코더 객체 생성
        let markers = []; // 현재 지도에 표시된 마커 배열

        // 기존 마커 제거 함수
        function removeMarkers() {
            markers.forEach(marker => marker.setMap(null));
            markers = [];
        }

        function searchPlacesInBounds() {
            const bounds = map.getBounds(); // 현재 지도 경계를 가져옴

            // 서버에서 장소 데이터 가져오기
            fetch('<%=request.getContextPath()%>/locations')
                .then(response => response.json())
                .then(data => {
                    removeMarkers(); // 기존 마커 제거

                    data.forEach((location, index) => {
                        setTimeout(() => {
                            const encodedAddress = encodeURIComponent(location.address);
                            
                            console.log("Encoded Address:", encodedAddress);

                            
                            geocoder.addressSearch(encodedAddress, function(result, status) {
                                if (status === kakao.maps.services.Status.OK) {
                                    const coords = new kakao.maps.LatLng(result[0].y, result[0].x);
                                    if (bounds.contain(coords)) {
                                        const marker = new kakao.maps.Marker({
                                            position: coords,
                                            map: map,
                                            image: markerImage
                                        });
                                        markers.push(marker);
                                        kakao.maps.event.addListener(marker, "click", function () {
                                            document.getElementById("info-title").innerText = location.name;
                                            document.getElementById("info-description").innerText = location.description || location.address;
                                            animateMarkerDrop(marker);
                                        });
                                    }
                                } else {
                                    console.error("주소 지오코딩 실패:", location.address, "상태:", status);
                                }
                            });
                        }, index * 100); // 100ms의 간격을 두고 호출
                    });
                }) // 여기까지가 fetch의 then 블록
                .catch(error => console.error("데이터 가져오기 실패:", error));
        }

        // 지도 이동 또는 확대/축소가 끝날 때마다 실행
        kakao.maps.event.addListener(map, "idle", searchPlacesInBounds);

        // 페이지 로드 시 초기 검색 실행
        searchPlacesInBounds();

    });

    // 마커 내려찍기 애니메이션 함수
    function animateMarkerDrop(marker) {
        let originalPosition = marker.getPosition();
        let dropHeight = 20;
        let duration = 300;
        let start = null;

        function dropAnimationStep(timestamp) {
            if (!start) start = timestamp;
            let progress = timestamp - start;
            let amount = Math.min(progress / duration, 1);

            let displacement = dropHeight * Math.sin(amount * Math.PI);
            let newPosition = new kakao.maps.LatLng(
                originalPosition.getLat() - displacement / 100000,
                originalPosition.getLng()
            );
            marker.setPosition(newPosition);

            if (amount < 1) {
                requestAnimationFrame(dropAnimationStep);
            } else {
                marker.setPosition(originalPosition);
            }
        }

        requestAnimationFrame(dropAnimationStep);
    }
</script>





</body>
</html>
