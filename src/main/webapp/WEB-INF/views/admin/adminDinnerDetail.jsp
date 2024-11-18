<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="/resources/js/sweetalert.min.js"></script>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>매장 상세 페이지</title>
    <link rel="stylesheet" href="/resources/css/diner_admin_memberdetail.css" />
    <link rel="stylesheet" href="/resources/css/default.css" />
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <script src="/resources/js/sweetalert.min.js"></script>
    <style>
        /* 페이지 제목 스타일 */
        .page-title {
            font-size: 1.5em;
            text-align: center;
            margin-bottom: 20px;
        }

        /* 테이블 스타일 */
        .tbl {
            width: 80%;
            margin: 0 auto;
            border-collapse: collapse;
            font-size: 1em;
            border-radius: 10px;
            overflow: hidden;
            box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
        }

        .tbl th, .tbl td {
            padding: 10px;
            border: 1px solid #ddd;
            text-align: center;
        }

        .tbl th {
            background-color: #f40;
            color: #fff;
            width: 30%;
        }

        /* 입력 필드 스타일 */
        .tbl td input[type="text"], .tbl td select {
            width: 50%;
            border: none;
            border-bottom: 1px solid #ddd;
            padding: 5px;
            font-size: 1em;
            text-align: center;
        }

        .tbl td input[type="text"]:focus, .tbl td select:focus {
            outline: none;
            border-bottom: 1px solid #3a5fcd;
        }

        /* 컨테이너 스타일 */
        .mypage-container {
            max-width: 600px;
            margin: 0 auto;
            padding: 20px;
            border: 1px solid #ddd;
            border-radius: 10px;
            background-color: #ffffff;
            box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
        }

        /* 버튼 스타일 */
        .mypage-btn {
            text-align: center;
            margin-top: 20px;
        }

        .btn-primary, .btn-secondary {
            padding: 10px 20px;
            color: #fff;
            border: none;
            border-radius: 5px;
            font-size: 1em;
            cursor: pointer;
        }

        .btn-primary {
            background-color: #3a5fcd;
        }

        .btn-primary:hover {
            background-color: #2f4fa8;
        }
    </style>
</head>
<body>
    <jsp:include page="/WEB-INF/views/common/header.jsp" />
    <div class="container">
        <main class="content mypage-container">
            <section class="section mypage-wrap">
                <div class="page-title">매장 정보페이지</div>
                
                <form id="updateForm" action="/dinner/update" method="post">
                
                    <input type="hidden" name="dinnerNo" value="${dinner.dinnerNo}">
                    
                    <table class="tbl">
                        <tr>
                            <th>식당 코드</th>
                            <td>${dinner.dinnerNo}</td>
                        </tr>
                        <tr>
                            <th><label for="dinnerName">식당 이름</label></th>
                            <td><input type="text" id="dinnerName" name="dinnerName" required maxlength="100" value="${dinner.dinnerName}"></td>
                        </tr>
                        <tr>
                            <th>식당 주소</th>
                            <td><input type="text" name="dinnerAddr" value="${dinner.dinnerAddr}"></td>
                        </tr>
                        <tr>
                            <th>오픈 시간</th>
                            <td><input type="text" id="dinnerOpen" name="dinnerOpen" placeholder="예: 09:00 (HH:mm)" value="${dinner.dinnerOpen}"></td>
                        </tr>
                        <tr>
                            <th>마감 시간</th>
                            <td><input type="text" id="dinnerClose" name="dinnerClose" placeholder="예: 22:00 (HH:mm)" value="${dinner.dinnerClose}"></td>
                        </tr>
                        <tr>
                            <th>식당 번호</th>
                            <td><input type="text" id="dinnerPhone" name="dinnerPhone" placeholder="예: 010-1234-5678" value="${dinner.dinnerPhone}"></td>
                        </tr>
                        <tr>
                            <th>식당 이메일</th>
                            <td><input type="text" name="dinnerEmail" value="${dinner.dinnerEmail}"></td>
                        </tr>
                        <tr>
                            <th>주차 여부</th>
                            <td><select name="dinnerParking">
                                <option value="Y" ${dinner.dinnerParking == 'Y' ? 'selected' : ''}>Y</option>
                                <option value="N" ${dinner.dinnerParking == 'N' ? 'selected' : ''}>N</option>
                            </select></td>
                        </tr>
                        <tr>
                            <th>수용 인원</th>
                            <td><input type="text" name="dinnerMaxPerson" required min="1" placeholder="최대 수용 인원을 입력하세요." value="${dinner.dinnerMaxPerson}"></td>
                        </tr>
                        <tr>
                            <th>사업자 등록번호</th>
                            <td><input type="text" name="busiNo" value="${dinner.busiNo}"></td>
                        </tr>
                        <tr>
                            <th>매장 ID</th>
                            <td>${dinner.dinnerId}</td>
                        </tr>
                        <tr>
                            <th>승인 여부</th>
                            <td><select name="dinnerConfirm">
                                <option value="Y" ${dinner.dinnerConfirm == 'Y' ? 'selected' : ''}>Y</option>
                                <option value="N" ${dinner.dinnerConfirm == 'N' ? 'selected' : ''}>N</option>
                            </select></td>
                        </tr>
                    </table>

                    <div class="mypage-btn">
                       <button type="button" id="updateButton" class="btn-primary" style="margin-right: 100px;" onclick="updateDinner()">정보수정</button>
                        <button type="button" onclick="window.location.href='/admin/DinnerPageFrm'" class="btn-primary">매장 상세페이지 이동</button>
                    </div>
                </form>
            </section>
        </main>
    </div>
    <jsp:include page="/WEB-INF/views/common/footer.jsp" />

    <script>
    
    function updateDinner() {
        // 입력 필드 선택
        const dinnerOpen = $('#dinnerOpen'); // 오픈 시간
        const dinnerClose = $('#dinnerClose'); // 마감 시간
        const dinnerPhone = $('#dinnerPhone'); // 전화번호

        // 정규식
        const timeExp = /^([01][0-9]|2[0-3]):[0-5][0-9]$/; // HH:mm 형식
        const phoneExp = /^010-\d{3,4}-\d{4}$/; // 전화번호 형식

        // 유효성 검사
        if (!timeExp.test(dinnerOpen.val())) {
            swal("알림", "오픈 시간은 예: 09:00 (HH:mm) 형식으로 적어주세요", "warning");
            return;
        }

        if (!timeExp.test(dinnerClose.val())) {
            swal("알림", "마감 시간은 예: 22:00 (HH:mm) 형식으로 적어주세요", "warning");
            return;
        }

        if (!phoneExp.test(dinnerPhone.val())) {
            swal("알림", "전화번호는 - 포함 13자리로 입력해주세요", "warning");
            return;
        }
        
     // 값 변환: ":" 제거
        const formattedDinnerOpen = dinnerOpen.val().replace(':', '');
        const formattedDinnerClose = dinnerClose.val().replace(':', '');
        const formattedDinnerPhone = dinnerPhone.val();


        // 유효성 검사가 모두 통과한 경우 swal 확인 팝업
        swal({
            title: "알림",
            text: "매장 정보를 수정하시겠습니까?",
            icon: "warning",
            buttons: {
                cancel: {
                    text: "취소",
                    value: false,
                    visible: true,
                    closeModal: true
                },
                confirm: {
                    text: "수정",
                    value: true,
                    visible: true,
                    closeModal: true
                }
            }
        }).then(function(isConfirm) {
            // 수정 버튼 클릭 시 form 제출
            if (isConfirm) {
                $('#updateForm').submit(); // form 태그 submit
            }
        });
    }
    </script>
</body>
</html>
