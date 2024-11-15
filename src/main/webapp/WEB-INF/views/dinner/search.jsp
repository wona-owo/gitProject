<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>인기식당</title>
<link rel="stylesheet" href="/resources/css/default.css" />
<style>
/* 검색창 스타일 */
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

/* 카드 스타일 */
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
    width: 200px;
    height: 300px;
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
    height: 100%;
    max-height: 150px;
    object-fit: contain;
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
</style>
</head>
<body>
    <jsp:include page="/WEB-INF/views/common/header.jsp" />
    <div class="wrap">
        <main>
            <!-- 검색 박스 -->
            <div class="search-box">
                <input type="text" id="searchInput" class="search-input"
                       value="<%= request.getParameter("query") != null ? request.getParameter("query") : "" %>"
                       placeholder="검색어를 입력하세요">
                <button class="search-button" onclick="search()">검색</button>
            </div>

            <!-- 검색 결과 카드 -->
            <div class="card-container" id="cardContainer">
                <c:forEach var="dinner" items="${dinnerList}">
                    <div class="card" data-name="${dinner.dinnerName}" data-addr="${dinner.dinnerAddr}"
                         data-cuisine="${dinner.foodNation}" data-type="${dinner.foodCat}">
                        <img src="${pageContext.request.contextPath}/resources/images/${dinner.dinnerNo != null && !dinner.dinnerNo.isEmpty() ? dinner.dinnerNo : 'default'}.jpg" onerror="this.onerror=null;this.src='${pageContext.request.contextPath}/resources/images/default.jpg';" alt="${dinner.dinnerName} 이미지">
                        <div class="card-info">
                            <h3>${dinner.dinnerName}</h3>
                            <p>${dinner.dinnerAddr}</p>
                            <p class="cuisine-type">${dinner.foodNation}</p>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </main>
    </div>
    <jsp:include page="/WEB-INF/views/common/footer.jsp" />

    <script>
        // 검색 버튼 클릭 시 검색어 전달
        function search() {
            const searchKeyword = document.getElementById("searchInput").value.trim();
            if (searchKeyword) {
                // 검색어를 쿼리 파라미터로 추가하여 페이지 리로드
                window.location.href = "<%= request.getContextPath() %>/search?query=" + encodeURIComponent(searchKeyword);
            } else {
                alert("검색어를 입력해주세요.");
            }
        }

        // 검색어로 카드 필터링
        document.addEventListener("DOMContentLoaded", function () {
            const searchKeyword = "<%= request.getParameter("query") != null ? request.getParameter("query") : "" %>".toLowerCase();
            const cards = document.querySelectorAll(".card");

            if (searchKeyword) {
                cards.forEach(card => {
                    const name = card.getAttribute("data-name").toLowerCase();
                    const addr = card.getAttribute("data-addr").toLowerCase();
                    if (name.includes(searchKeyword) || addr.includes(searchKeyword)) {
                        card.style.display = "flex";
                    } else {
                        card.style.display = "none";
                    }
                });
            }
        });

        // Enter 키로도 검색 가능
        document.getElementById("searchInput").addEventListener("keyup", function (event) {
            if (event.key === "Enter") {
                search();
            }
        });
    </script>
</body>
</html>
