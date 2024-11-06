<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="/resources/css/diner_admin_member.css" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>매장관리 페이지</title>
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
</style>

</head>
<body>
 <div class="wrap">
      <main class="content">
         <section class="section admin-wrap">
            <div class="page-title">회원 관리 페이지</div>
            <div class="table-container">
                <div class="search-box">
                    <input type="text" placeholder="회원 이름 검색" id="searchInput">
                    <button onclick="searchMembers()">검색</button>
                </div>
            
            <table class="tbl tbl_hover">
               <tr>
                  <th style="width: 5%">식당코드</th>
                  <th style="width: 10%">식당이름</th>
                  <th style="width: 15%">주소</th>
                  <th style="width: 10%">이메일</th>
                  <th style="width: 10%">매장 번호</th>
                  <th style="width: 5%">승인여부</th>
               </tr>
               
               <c:forEach var="m" items="${memberList}">
               <tr>
                  <td> <!-- 선택 -->
                     <div class="input-wrap">
                        <input type="checkbox" class="chk">
                        <label onclick="chkLabel(this)"></label>
                     </div>
                  </td>
                  <td><a href="/memberDetail?memberNo=${m.memberNo}">${m.memberNo}</a></td>       <!-- 번호 -->
                  <td><a href="/memberDetail?memberNo=${m.memberNo}">${m.memberId}</a></td>        <!-- 아이디 -->
                  <td><a href="/memberDetail?memberNo=${m.memberNo}">${m.memberNick}</a></td>      <!-- 별명 -->
                  <td><a href="/memberDetail?memberNo=${m.memberNo}">${m.memberEmail}</a></td>    <!-- 이메일 -->
                  <td><a href="/memberDetail?memberNo=${m.memberNo}">${m.memberPhone}</a></td>    <!-- 전화번호 -->
                  <td><a href="/memberDetail?memberNo=${m.memberNo}">${m.memberAddr}</a></td>        <!-- 주소 -->

               </c:forEach>
                  
               <tr>
                  <td colspan="10">
                     <button class="btn-selectlv lg" onclick="chgLevel(this)">매장 정보 수정</button>
                  </td>
               </tr>
            </table>
            </div>
         </section>
      </main>
   </div>

   <jsp:include page="/WEB-INF/views/common/footer.jsp" />

</body>
</html>