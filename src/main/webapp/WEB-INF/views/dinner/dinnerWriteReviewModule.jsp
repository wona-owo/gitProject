<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="reviewModal" class="modal">
    <div class="modal-content">
        <span class="close-btn">&times;</span>
        <h2>리뷰 작성하기</h2>
        <form action="/submitReview" method="post" id="reviewForm" enctype="multipart/form-data">
            <div class="form-group">
                <label for="title">식당명</label>
                <input type="text" id="title" name="title" placeholder="식당명을 입력하세요" required>
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
