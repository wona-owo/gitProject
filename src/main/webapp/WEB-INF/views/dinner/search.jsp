<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>인기식당</title>
    <link rel="stylesheet"
        href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <style>
        @font-face {
            font-family: 'LINESeedKR-Bd';
            src: url('https://fastly.jsdelivr.net/gh/projectnoonnu/noonfonts_11-01@1.0/LINESeedKR-Bd.woff2') format('woff2');
            font-weight: 700;
            font-style: normal;
        }

        .filter-container {
            padding: 8px 12px;
            background-color: #f9f9f9;
            border: 1px solid #ddd;
            margin: 10px 0;
            border-radius: 8px;
            display: none;
        }

        .filter-section {
            margin-bottom: 6px;
            display: flex;
            flex-wrap: wrap;
            gap: 4px;
        }

        .filter-button {
            padding: 4px 8px;
            border: 1px solid #ff4400;
            background-color: #ffffff;
            color: #ff4400;
            cursor: pointer;
            border-radius: 15px;
            font-size: 0.8em;
            transition: background-color 0.3s, color 0.3s;
            display: flex;
            align-items: center;
        }

        .filter-button.active {
            background-color: #ff4400;
            color: #ffffff;
        }

        .arrow {
            margin-left: 5px;
            transition: transform 0.3s;
            display: inline-block;
        }

        .filter-title {
            font-size: 0.9em;
            font-weight: bold;
            margin-bottom: 5px;
            color: #333;
        }

        .search-box {
            margin-bottom: 20px;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .search-input {
            padding: 8px;
            width: 60%;
            border: 1px solid #ff4400;
            border-radius: 20px;
            margin-left: 10px;
            font-size: 1em;
        }

        .search-button {
            padding: 8px 15px;
            border: none;
            background-color: #ff4400;
            color: #ffffff;
            border-radius: 20px;
            cursor: pointer;
            font-size: 1em;
            margin-left: 10px;
        }

        .card-container {
            display: flex;
            flex-wrap: wrap;
            justify-content: flex-start;
            padding: 20px;
        }

        .card {
            border: 1px solid #ddd;
            border-radius: 10px;
            padding: 10px;
            margin: 10px;
            width: 160px;
            height: 250px;
            cursor: pointer;
            display: flex;
            flex-direction: column;
            align-items: center;
            background-color: #fff;
            transition: transform 0.3s;
            text-align: left;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        .card:hover {
            transform: scale(1.05);
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
        }

        .card img {
            width: 100%;
            height: auto;
            max-height: 100px;
            object-fit: cover;
            border-radius: 5px;
            margin-bottom: 8px;
        }

        .card-info {
            width: 100%;
            text-align: left;
            padding-left: 5px;
        }

        .card-info h3 {
            margin: 4px 0;
            font-size: 1.1em;
        }

        .card-info p {
            margin: 3px 0;
            font-size: 1em;
        }

        .card-info .cuisine-type {
            color: #aaa;
            font-size: 0.9em;
        }

        .favorite-button {
            cursor: pointer;
            margin-top: auto;
            align-self: flex-end;
            font-size: 1.5em;
            color: #888;
            position: relative;
            bottom: 0;
            transition: transform 0.3s ease;
        }

        .favorite-button:active {
            transform: scale(1.2);
        }
    </style>
</head>
<body>
    <jsp:include page="/WEB-INF/views/common/header.jsp" />

    <div class="wrap">
        <main class="content">
            <div class="card-container" id="cardContainer">
                <c:forEach var="dinner" items="${dinnerList}">
                    <div class="card" data-name="${dinner.dinnerName}"
                        data-addr="${dinner.dinnerAddr}"
                        data-cuisine="${dinner.foodNation}" data-type="${dinner.foodCat}">
                        <img
                            src="${pageContext.request.contextPath}/resources/images/${dinner.dinnerNo != null && !dinner.dinnerNo.isEmpty() ? dinner.dinnerNo : 'default'}.jpg"
                            onerror="this.onerror=null;this.src='${pageContext.request.contextPath}/resources/images/default.jpg';"
                            alt="${dinner.dinnerName} 이미지">
                        <div class="card-info">
                            <h3>${dinner.dinnerName}</h3>
                            <p>${dinner.dinnerAddr}</p>
                            <p class="cuisine-type">${dinner.foodNation}</p>
                        </div>
                        <!-- 즐겨찾기 버튼: memberLevel == 2인 경우만 표시 -->
                        <c:if
                            test="${sessionScope.loginType eq 'member' && sessionScope.memberLevel == 2}">
                            <div class="favorite-button"
                                onclick="toggleFavorite('${dinner.dinnerNo}', event)"
                                id="favoriteButton-${dinner.dinnerNo}">
                                <i class="fa-solid fa-map-pin"></i>
                            </div>
                        </c:if>
                    </div>
                </c:forEach>
            </div>
        </main>
        <jsp:include page="/WEB-INF/views/common/footer.jsp" />
    </div>

    <script>
        // 서버에서 전달된 loginType과 memberLevel을 JavaScript 변수로 설정
        const loginType = '${sessionScope.loginType}';
        const memberLevel = '${sessionScope.memberLevel}';

        // 쿠키에서 데이터 가져오는 함수
        function getCookie(name) {
            const cookies = document.cookie.split(';');
            for (let i = 0; i < cookies.length; i++) {
                const cookie = cookies[i].trim();
                if (cookie.startsWith(name + '=')) {
                    return cookie.substring(name.length + 1);
                }
            }
            return null;
        }

        // 즐겨찾기 토글
        function toggleFavorite(dinnerNo, event) {
            event.stopPropagation();

            // 로그인 상태 및 회원 등급 확인
            if (loginType !== 'member' || memberLevel !== '2') {
                alert("일반 회원만 즐겨찾기 기능을 사용할 수 있습니다.");
                return;
            }

            const memberNo = getCookie('memberNo');
            if (!memberNo) {
                alert("로그인이 필요합니다.");
                console.log(memberNo);
                return;
            }

            // 서버와 통신 (AJAX 방식)
            fetch(`/favorite/toggle`, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ userId, dinnerNo })
            }).then(response => response.json())
            .then(data => {
                if (data.success) {
                    const favoriteButton = document.getElementById(`favoriteButton-${dinnerNo}`);
                    const isFavorited = data.isFavorited;

                    // 즐겨찾기 상태 전환 및 아이콘 업데이트
                    favoriteButton.querySelector('i').className = isFavorited ? 'fa-solid fa-map-pin' : 'fa-regular fa-map-pin';
                    favoriteButton.style.color = isFavorited ? '#ff4400' : '#888';
                } else {
                    alert("오류가 발생했습니다.");
                }
            }).catch(() => {
                alert("서버와의 통신 중 오류가 발생했습니다.");
            });
        }

        // 상세 페이지 열기
        function openDetailPage(dinnerNo, event) {
            if (event.target.closest('.favorite-button')) {
                return;
            }
            window.location.href = "/dinner/dinnerDetail?dinnerNo=" + dinnerNo;
        }
    </script>
</body>
</html>
