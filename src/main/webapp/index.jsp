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
        <!-- 공통 헤더 포함 -->
        <jsp:include page="/WEB-INF/views/common/header.jsp" />
        <div class="wrap">
            <main>
                <!-- 검색 박스 -->
                <div class="search-box">
                    <input type="text" id="searchInput" class="search-input" placeholder="식당 이름, 지역 등을 입력하세요">
                    <button type="submit" class="search-button" onclick="search()">검색</button>
                </div>

                <!-- 지도 영역 -->
                <section class="map-focus-section">
                    <div class="map-container">
                        <div id="map" style="width: 100%; height: 400px;"></div>
                    </div>
                    <div class="focus-info" id="info">
                        <h2 class="focus-title" id="info-title">식당 이름</h2>
                        <p class="focus-description" id="info-description">식당 정보나 사진이 여기 뜰 예정이에요</p>
                    </div>
                </section>
            </main>
        </div>
        <!-- 공통 푸터 포함 -->
        <jsp:include page="/WEB-INF/views/common/footer.jsp" />
    </div>

    <%
    // 세션에서 로그인 멤버의 주소 가져오기
    String memberAddress = "";
    if (session.getAttribute("loginMember") != null) {
        Object loginMember = session.getAttribute("loginMember");
        try {
            memberAddress = (String) loginMember.getClass().getMethod("getMemberAddr").invoke(loginMember);
        } catch (Exception e) {
            memberAddress = "";
        }
    }
    %>

    <script>
        document.addEventListener("DOMContentLoaded", function () {
            const mapContainer = document.getElementById("map");
            const defaultCenter = new kakao.maps.LatLng(37.5105, 127.1194); // 기본 중심 좌표
            const mapOptions = { center: defaultCenter, level: 4 }; // 지도 옵션
            const map = new kakao.maps.Map(mapContainer, mapOptions);

            const geocoder = new kakao.maps.services.Geocoder();
            const memberAddr = "<%= memberAddress %>";

            // 사용자의 주소를 지도 중심으로 설정
            if (memberAddr) {
                geocoder.addressSearch(memberAddr, function (result, status) {
                    if (status === kakao.maps.services.Status.OK) {
                        const coords = new kakao.maps.LatLng(result[0].y, result[0].x);
                        map.setCenter(coords);
                    } else {
                        console.error("주소 지오코딩 실패:", memberAddr, "상태:", status);
                        map.setCenter(defaultCenter);
                    }
                });
            } else {
                map.setCenter(defaultCenter);
            }

            // 마커 이미지 설정
            const imageSrc = '<%= request.getContextPath() %>/resources/images/pin.png';
            const imageSize = new kakao.maps.Size(64, 64);
            const imageOption = { offset: new kakao.maps.Point(32, 64) };
            const markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption);

            let markers = [];

            // 기존 마커 삭제
            function removeMarkers() {
                markers.forEach(marker => marker.setMap(null));
                markers = [];
            }

            // 현재 지도 범위 내의 장소 검색
            function searchPlacesInBounds() {
                const bounds = map.getBounds();

                fetch('<%= request.getContextPath() %>/locations')
                    .then(response => response.json())
                    .then(data => {
                        removeMarkers();

                        data.forEach((location, index) => {
                            setTimeout(() => {
                                geocoder.addressSearch(location.address, function (result, status) {
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
                                                document.getElementById("info-description").innerText =
                                                    location.description || location.address;
                                                animateMarkerDrop(marker);
                                            });
                                        }
                                    } else {
                                        console.error("주소 지오코딩 실패:", location.address, "상태:", status);
                                    }
                                });
                            }, index * 100);
                        });
                    })
                    .catch(error => console.error("데이터 가져오기 실패:", error));
            }

            // 지도 이동 시 장소 검색
            kakao.maps.event.addListener(map, "idle", searchPlacesInBounds);

            searchPlacesInBounds();

            // 마커 애니메이션
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
        });

        // 검색 기능
        function search() {
            const searchKeyword = document.getElementById("searchInput").value.trim();
            if (searchKeyword) {
                // 검색 페이지로 이동하며 검색어 전달
                window.location.href = "<%= request.getContextPath() %>/search?query=" + encodeURIComponent(searchKeyword);
            } else {
                alert("검색어를 입력해주세요.");
            }
        }
    </script>
</body>
</html>
