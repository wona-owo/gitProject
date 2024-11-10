<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>매장관리 페이지</title>
<link rel="stylesheet" href="/resources/css/dinner_admin_member.css" />
<style>
.notice-list-wrap {
	width: 1200px;
	margin: 0 auto;
}

.list-content {
	height: 500px;
}

.list-header {
	padding: 15px 0px;
	text-align: right;
}

.search-section {
	display: flex;
	justify-content: flex-end;
	align-items: center;
	width: 80%;
	margin: 20px 0;
}

.search-section input[type="text"] {
	width: 50%;
	padding: 20px;
	font-size: 16px;
	border-radius: 5px;
	border: 1px solid #CCC;
}

.search-section button {
	margin-left: 10px;
	padding: 10px;
	font-size: 16px;
	background-color: #A5C9FF;
	color: #000;
	border: none;
	border-radius: 5px;
}
    #pagination {
        margin-top: 20px;
        text-align: center;
    }

    #pagination button {
        margin: 0 5px;
        padding: 10px;
        font-size: 16px;
        border-radius: 5px;
        border: 1px solid #CCC;
        color: #000;
    }

    #pagination button.active {
        font-weight: bold;
        background-color: #0056b3;
        color: #FFF;
    }
</style>

</head>
<body>
	<div class="wrap">
		<jsp:include page="/WEB-INF/views/common/header.jsp" />
		<main class="content">
			<section class="section admin-wrap">
				<div class="page-title">매장 관리 페이지</div>
				<div class="table-container">
					<div class="search-box">
						<button type="button" id="firstname" class="btn-primary" onclick="sortTableByName()">매장이름순 정렬</button>
						<button type="button" id="firstCon" class="btn-primary" onclick="sortTableByConfirm()">승인여부 순 정렬</button>
						<input type="text" placeholder="매장 이름 검색" id="searchInput">
						<button onclick="searchDinner()">검색</button>
					</div>
				</div>

				<table id="dinnerTable" class="tbl tbl_hover"> <!-- 공백을 제거한 id="dinnerTable" -->
					<tr>
						<th style="width: 5%">식당코드</th>
						<th style="width: 10%">식당이름</th>
						<th style="width: 15%">주소</th>
						<th style="width: 10%">이메일</th>
						<th style="width: 10%">매장 번호</th>
						<th style="width: 5%">승인여부</th>
					</tr>
					<c:forEach var="d" items="${dinnerList}">
						<tr onclick="location.href='/dinnerDetail?dinnerNo=${d.dinnerNo}'" style="cursor: pointer;">
							<td class="dinnerNo">${d.dinnerNo}</td>
							<td class="dinnerName">${d.dinnerName}</td>
							<td>${d.dinnerAddr}</td>
							<td>${d.dinnerEmail}</td>
							<td>${d.dinnerPhone}</td>
							<td class="dinnerConfirm">${d.dinnerConfirm}</td>
						</tr>
					</c:forEach>
				</table>
				<!-- 페이지 번호가 표시될 영역 -->
                <div id="pagination"></div>
                <jsp:include page="/WEB-INF/views/common/footer.jsp" />
			</section>
		</main>

	</div>

		<script>
		let currentPage = 1;
		const rowsPerPage = 8;

		function sortTableByName() {
		    const table = document.getElementById("dinnerTable");
		    const rows = Array.from(table.getElementsByTagName("tr")).slice(1); // 헤더 행을 제외한 모든 행을 배열로 변환

		    rows.sort((a, b) => {
		        const nameA = a.getElementsByClassName("dinnerName")[0].innerText.toLowerCase();
		        const nameB = b.getElementsByClassName("dinnerName")[0].innerText.toLowerCase();
		        return nameA.localeCompare(nameB);
		    });

		    // 정렬된 행을 다시 테이블에 추가
		    rows.forEach(row => table.appendChild(row));
		    displayTablePage(1); // 첫 페이지로 이동하여 정렬된 결과를 표시
		}

		function sortTableByConfirm() {
		    const table = document.getElementById("dinnerTable");
		    const rows = Array.from(table.getElementsByTagName("tr")).slice(1);

		    rows.sort((a, b) => {
		        const confirmA = a.getElementsByClassName("dinnerConfirm")[0].innerText.toLowerCase();
		        const confirmB = b.getElementsByClassName("dinnerConfirm")[0].innerText.toLowerCase();
		        return confirmA.localeCompare(confirmB);
		    });

		    rows.forEach(row => table.appendChild(row));
		    displayTablePage(1); // 첫 페이지로 이동하여 정렬된 결과를 표시
		}

		function displayTablePage(page) {
		    const table = document.getElementById("dinnerTable");
		    const rows = Array.from(table.getElementsByTagName("tr")).slice(1); // 모든 데이터를 대상으로 함

		    // 시작과 끝 인덱스를 계산
		    const start = (page - 1) * rowsPerPage;
		    const end = start + rowsPerPage;

		    // 모든 행을 숨기고, 해당 페이지에 해당하는 행만 표시
		    rows.forEach((row, index) => {
		        row.style.display = (index >= start && index < end) ? "" : "none";
		    });

		    currentPage = page;
		    updatePagination();
		}

		function updatePagination() {
		    const table = document.getElementById("dinnerTable");
		    const rows = table.getElementsByTagName("tr");
		    const totalPages = Math.ceil((rows.length - 1) / rowsPerPage);

		    const pagination = document.getElementById("pagination");
		    pagination.innerHTML = "";

		    const maxVisibleButtons = 3;
		    let startPage = Math.max(1, currentPage - Math.floor(maxVisibleButtons / 2));
		    let endPage = startPage + maxVisibleButtons - 1;

		    if (endPage > totalPages) {
		        endPage = totalPages;
		        startPage = Math.max(1, endPage - maxVisibleButtons + 1);
		    }

		    if (currentPage > 1) {
		        const prevButton = document.createElement("button");
		        prevButton.innerHTML = "이전";
		        prevButton.onclick = () => displayTablePage(currentPage - 1);
		        pagination.appendChild(prevButton);
		    }

		    for (let i = startPage; i <= endPage; i++) {
		        const pageButton = document.createElement("button");
		        pageButton.innerHTML = i;
		        pageButton.className = i === currentPage ? "active" : "";
		        pageButton.onclick = () => displayTablePage(i);
		        pagination.appendChild(pageButton);
		    }

		    if (currentPage < totalPages) {
		        const nextButton = document.createElement("button");
		        nextButton.innerHTML = "다음";
		        nextButton.onclick = () => displayTablePage(currentPage + 1);
		        pagination.appendChild(nextButton);
		    }
		}

		// 초기 로드시 첫 페이지를 표시
		document.addEventListener("DOMContentLoaded", () => {
		    displayTablePage(1);
		});
		</script>
</body>
</html>