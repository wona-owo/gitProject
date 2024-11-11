<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>식당 정보 등록</title>
    <link rel="stylesheet" href="/resources/css/default.css">
    <style>
        /* 페이지 전체 스타일 */
        body {
            background-color: #f7f7f7; /* 밝은 회색 배경 */
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }

        .wrap {
            max-width: 800px;
            margin: 50px auto;
            background: #ffffff; /* 카드 스타일 흰색 배경 */
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
        }

        .page-title {
            font-size: 24px;
            font-weight: bold;
            color: #f40; /* 포인트 색상 */
            text-align: center;
            margin-bottom: 20px;
        }

        /* 입력 폼 스타일 */
        .form-group {
            margin-bottom: 20px;
        }

        .form-group label {
            display: block;
            font-size: 14px;
            font-weight: bold;
            margin-bottom: 8px;
            color: #333; /* 진한 회색 텍스트 */
        }

        .form-group input,
        .form-group select,
        .form-group textarea {
            width: 100%;
            padding: 12px 15px;
            border: 1px solid #ddd;
            border-radius: 8px;
            background-color: #fafafa; /* 밝은 배경 */
            font-size: 14px;
            color: #333;
            transition: border-color 0.3s, box-shadow 0.3s;
            box-sizing: border-box;
        }

        .form-group input:focus,
        .form-group select:focus,
        .form-group textarea:focus {
            outline: none;
            border-color: #f40; /* 포커스 시 포인트 색상 */
            box-shadow: 0 0 6px rgba(255, 68, 68, 0.3); /* 포인트 색상 강조 */
        }

        /* 제출 버튼 스타일 */
        .submit-btn {
            display: inline-block;
            width: 100%;
            padding: 12px 20px;
            font-size: 16px;
            font-weight: bold;
            color: #fff; /* 텍스트 흰색 */
            background-color: #f40; /* 포인트 색상 */
            border: none;
            border-radius: 8px;
            cursor: pointer;
            text-align: center;
            transition: background-color 0.3s, box-shadow 0.3s;
        }

        .submit-btn:hover {
            background-color: #d93636; /* 호버 시 약간 어두운 빨간색 */
            box-shadow: 0 4px 10px rgba(217, 54, 54, 0.3); /* 부드러운 그림자 */
        }

        .submit-btn:active {
            background-color: #b12b2b; /* 클릭 시 더 어두운 빨간색 */
            box-shadow: none;
        }

        /* 반응형 디자인 */
        @media (max-width: 768px) {
            .wrap {
                padding: 20px;
            }

            .page-title {
                font-size: 20px;
            }

            .form-group input,
            .form-group select,
            .form-group textarea {
                font-size: 13px;
                padding: 10px 12px;
            }

            .submit-btn {
                font-size: 14px;
                padding: 10px;
            }
        }
    </style>
</head>
<body>
    <div class="wrap">
        <h1 class="page-title">식당 정보 등록</h1>
        <form action="/admin/dinnerRegister" method="post" enctype="multipart/form-data">
            <!-- 식당 이름 -->
            <div class="form-group">
                <label for="dinnerName">식당 이름</label>
                <input type="text" id="dinnerName" name="dinnerName" required placeholder="식당 이름을 입력하세요.">
            </div>

            <!-- 식당 주소 -->
            <div class="form-group">
                <label for="dinnerAddr">식당 주소</label>
                <input type="text" id="dinnerAddr" name="dinnerAddr" required placeholder="식당 주소를 입력하세요.">
            </div>

            <!-- 운영 시간 -->
            <div class="form-group">
                <label for="dinnerOpen">운영 시간</label>
                <input type="text" id="dinnerOpen" name="dinnerOpen" required placeholder="예: 09:00 ~ 22:00">
            </div>

            <!-- 전화번호 -->
            <div class="form-group">
                <label for="dinnerPhone">전화번호</label>
                <input type="text" id="dinnerPhone" name="dinnerPhone" required placeholder="예: 010-1234-5678">
            </div>

            <!-- 주차 가능 여부 -->
            <div class="form-group">
                <label for="dinnerParking">주차 가능 여부</label>
                <select id="dinnerParking" name="dinnerParking" required>
                    <option value="Y">가능</option>
                    <option value="N">불가능</option>
                </select>
            </div>

            <!-- 최대 인원 -->
            <div class="form-group">
                <label for="dinnerMaxPerson">최대 인원</label>
                <input type="number" id="dinnerMaxPerson" name="dinnerMaxPerson" required min="1" placeholder="최대 수용 인원을 입력하세요.">
            </div>

            <!-- 식당 이미지 -->
            <div class="form-group">
                <label for="dinnerImg">식당 이미지</label>
                <input type="file" id="dinnerImg" name="dinnerImg" accept="image/*">
            </div>

            <!-- 승인 여부 -->
            <div class="form-group">
                <label for="dinnerConfirm">승인 여부</label>
                <select id="dinnerConfirm" name="dinnerConfirm" required>
                    <option value="Y">승인</option>
                    <option value="N">미승인</option>
                </select>
            </div>

            <!-- 제출 버튼 -->
            <button type="submit" class="submit-btn">등록</button>
        </form>
    </div>
</body>
</html>
