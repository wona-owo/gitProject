<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
    <style>

        /* 콤팩트한 필터 스타일 */
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

        /* 화살표 스타일 */
        .arrow {
            margin-left: 5px;
            transition: transform 0.3s;
            display: inline-block;
        }

        /* 필터 제목 스타일 */
        .filter-title {
            font-size: 0.9em;
            font-weight: bold;
            margin-bottom: 5px;
            color: #333;
        }

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
            width: 160px;
            height: 200px;
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
            max-height: 80px;
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

    </style>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
</head>
<body>
	<div class="wrap">
    <main class="content">
        <!-- 검색 박스 -->
        <div class="search-box">
            <button class="filter-button" onclick="toggleFilterContainer()">필터 <span class="arrow">▼</span></button>
            <input type="text" id="searchInput" class="search-input" placeholder="식당 이름 또는 메뉴 검색">
            <button class="search-button" onclick="search()">검색</button>
        </div>

        <!-- 콤팩트 필터 컨테이너 -->
        <div class="filter-container">
            <div class="filter-title">국가별 필터</div>
            <div class="filter-section">
                <span class="filter-button" data-value="한식" onclick="toggleFilter(event, 'cuisine')">한식</span>
                <span class="filter-button" data-value="양식" onclick="toggleFilter(event, 'cuisine')">양식</span>
                <span class="filter-button" data-value="중식" onclick="toggleFilter(event, 'cuisine')">중식</span>
                <span class="filter-button" data-value="일식" onclick="toggleFilter(event, 'cuisine')">일식</span>
            </div>

            <div class="filter-title">음식 유형 필터</div>
            <div class="filter-section">
                <span class="filter-button" data-value="육류" onclick="toggleFilter(event, 'type')">육류</span>
                <span class="filter-button" data-value="생선" onclick="toggleFilter(event, 'type')">생선</span>
                <span class="filter-button" data-value="피자" onclick="toggleFilter(event, 'type')">피자</span>
                <span class="filter-button" data-value="비건" onclick="toggleFilter(event, 'type')">비건</span>
                <span class="filter-button" data-value="디저트" onclick="toggleFilter(event, 'type')">디저트</span>
            </div>
        </div>

        <!-- 예시 카드 -->
        <div class="card-container">
            <div class="card" data-cuisine="한식" data-type="육류" onclick="window.location.href='diner_detail.html'">
                <img src="img/jungsik.jpg" alt="음식 이미지">
                <div class="card-info">
                    <h3>정식당</h3>
                    <p>서울특별시 강남구</p>
                    <p class="cuisine-type">한식</p>
                </div>
            </div>
            <div class="card" data-cuisine="양식" data-type="피자" onclick="window.location.href='detail2.html'">
                <img src="img/yangsik.png" alt="음식 이미지">
                <div class="card-info">
                    <h3>예시 식당 2</h3>
                    <p>서울특별시 종로구</p>
                    <p class="cuisine-type">양식</p>
                </div>
            </div>
        </div>
    </main>
</div>
<script>
const filters = {
        cuisine: [],
        type: []
    };

    function toggleFilter(event, filterType) {
        const button = event.target;
        const value = button.getAttribute('data-value');
        const isActive = button.classList.toggle('active');

        if (isActive) {
            filters[filterType].push(value);
        } else {
            filters[filterType] = filters[filterType].filter(item => item !== value);
        }

        filterCards();
    }

    function filterCards() {
        const cards = document.querySelectorAll('.card');
        cards.forEach(card => {
            const cardCuisine = card.getAttribute('data-cuisine');
            const cardType = card.getAttribute('data-type');
            
            const cuisineMatch = filters.cuisine.length === 0 || filters.cuisine.includes(cardCuisine);
            const typeMatch = filters.type.length === 0 || filters.type.includes(cardType);

            card.style.display = cuisineMatch && typeMatch ? 'flex' : 'none';
        });
    }

    function search() {
        const searchKeyword = document.getElementById("searchInput").value.toLowerCase();
        const cards = document.querySelectorAll('.card');
        cards.forEach(card => {
            const cardTitle = card.querySelector('h3').innerText.toLowerCase();
            card.style.display = cardTitle.includes(searchKeyword) ? 'flex' : 'none';
        });
    }

    function toggleFilterContainer() {
        const filterContainer = document.querySelector('.filter-container');
        const arrow = document.querySelector('.arrow');
        const isOpen = filterContainer.style.display === 'block';
        filterContainer.style.display = isOpen ? 'none' : 'block';
        arrow.style.transform = isOpen ? 'rotate(0deg)' : 'rotate(180deg)';
    }
</script>
	
</body>
</html>