<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="reviewModal" class="modal">
    <div class="modal-content">
        <span class="close-btn">&times;</span>
        <h2>리뷰 작성하기</h2>
        <form action="/review/write" method="post" id="reviewForm" enctype="multipart/form-data">
        
        	<!-- 히든 필드로 식당 번호와 멤버 번호 전달 -->
		    <input type="hidden" name="dinnerNo" value="${param.dinnerNo}">
		    <input type="hidden" name="memberNo" value="${param.memberNo}">
		    
            <div class="form-group">
                <label for="title">식당명</label>
                <input type="text" id="title" name="title" value="${param.dinnerName}" readonly>
            </div>

            <div class="form-group">
                <label for="content">리뷰 내용</label>
                <textarea id="content" name="content" placeholder="리뷰 내용을 작성하세요" required></textarea>
            </div>

            <div class="form-group">
                <label for="photos">사진 첨부 (최대 3장)</label>
                <input type="file" id="photos" name="photos" accept="image/*" multiple>
            </div>

            <button type="submit" class="submit-btn">작성하기</button>
        </form>
    </div>
</div>
<script>
$("#reviewForm").submit(function (event) {
    event.preventDefault(); // 폼 기본 동작 방지

    const formData = $(this).serialize(); // 폼 데이터를 자동으로 시리얼라이즈

    $.ajax({
        url: "/review/write", // Servlet URL
        method: "POST",
        data: formData, // 폼 데이터 전달
        success: function (response) {
            alert("리뷰 작성이 완료되었습니다.");
            location.href = "/dinnerDetail?dinnerNo=" + $("input[name='dinnerNo']").val();
        },
        error: function () {
            alert("리뷰 작성 중 오류가 발생했습니다.");
        }
    });
});
</script>