<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰 작성</title>
<link rel="stylesheet" href="/resources/css/default.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<style>
/* 기본 설정 */
* {
    box-sizing: border-box;
}

/* 모달 배경 스타일 */
.modal {
    display: none;
    position: fixed;
    z-index: 1000;
    left: 0;
    top: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.6);
    display: flex;
    align-items: center;
    justify-content: center;
}

/* 모달 컨텐츠 스타일 */
.modal-content {
    width: 90%;
    max-width: 600px;
    background-color: #fff;
    padding: 30px;
    border-radius: 10px;
    box-shadow: 0 4px 12px rgba(0, 0, 0, 0.2);
    position: relative;
    text-align: center;
}

/* 닫기 버튼 스타일 */
.close-btn {
    position: absolute;
    top: 15px;
    right: 15px;
    font-size: 20px;
    cursor: pointer;
    color: #f40;
    font-weight: bold;
    transition: color 0.3s;
}

.close-btn:hover {
    color: #c30;
}

/* 버튼 공통 스타일 */
button {
    padding: 12px 20px;
    font-size: 16px;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    transition: background-color 0.3s;
}

#openModalBtn {
    background-color: #f40;
    color: #fff;
    margin: 20px auto;
    display: block;
}

#openModalBtn:hover {
    background-color: #c30;
}

</style>
</head>
<body>
    <!-- 리뷰 작성 버튼 -->
    <button id="openModalBtn">리뷰 작성하기</button>

    <!-- AJAX로 모달을 삽입할 컨테이너 -->
    <div id="reviewModalContainer"></div>

    <script>
    $(document).ready(function () {
        $("#openModalBtn").click(function () {
            // 이미 로드된 경우 다시 로드하지 않음
            if ($("#reviewModal").length > 0) {
                $("#reviewModal").fadeIn();
                return;
            }

            // AJAX로 JSP 로드
            $.ajax({
                url: "/reviewWrite/module", // 서블릿 URL
                method: "GET",
                data: {
                    dinnerName: "${dinner.dinnerName}",
                    dinnerNo: "${dinner.dinnerNo}",
                    memberNo: "${member.memberNo}"
                },
                success: function (data) {
                    // 데이터를 컨테이너에 삽입
                    $("#reviewModalContainer").html(data);

                    // 모달 보이기
                    $("#reviewModal").fadeIn();

                    // 동적 모달 초기화
                    setupReviewModal();
                },
                error: function () {
                    alert("리뷰 작성 모듈을 불러오는 데 실패했습니다.");
                }
            });
        });

        // ESC 키로 모달 닫기
        $(document).on("keydown", function (event) {
            if (event.key === "Escape" && $("#reviewModal").is(":visible")) {
                $("#reviewModal").fadeOut();
            }
        });
    });

    function setupReviewModal() {
        // 모달 닫기
        $(".close-btn").click(function () {
            $("#reviewModal").fadeOut();
        });
    }
    </script>
</body>
</html>
