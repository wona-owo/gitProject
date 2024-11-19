<%@ page import="com.menupick.member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    Member member = (Member) request.getAttribute("member");
%>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f0f0f0;
	margin: 0;
	padding: 0;
}
.wrap {
	width: 100%;
	max-width: 700px;
	margin: 0 auto;
	background-color: #fff;
	padding: 20px;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
	border-radius: 10px;
}

.input-wrap {
	display: flex;
	flex-direction: column;
	margin-bottom: 15px;
}

.input-title {
	margin-bottom: 5px;
}

.input-item {
	width: 100%;
}

.delete-button{
	display: flex;
	justify-content: center;
	align-items: center;
	padding-top : 30px;
}
.cancel-button{
	display: flex;
	justify-content: center;
	align-items: center;
	padding-top : 30px;
}
.input-item{
	width:700px;
}
.input-wrap{
	display: flex;
	justify-content: center;
	align-items: center;
}
button {
	padding: 10px 20px;
	border: none;
	border-radius: 5px;
	background-color: #007bff;
	color: #fff;
	font-size: 16px;
	cursor: pointer;
	transition: background-color 0.3s;
}

button:hover {
	background-color: #0056b3;
}

.join-button-box {
	display: flex;
	justify-content: center;
	align-items: center;
	padding-top: 30px;
}

.btn-primary {
	background-color: #007bff;
	border: none;
	border-radius: 30px;
	padding: 10px 20px;
	color: #fff;
	font-size: 16px;
	cursor: pointer;
	transition: background-color 0.3s;
}

.btn-primary:hover {
	background-color: #0056b3;
}
.btn-default {
	background-color: #f40;
	border: none;
	border-radius: 30px;
	padding: 10px 20px;
	color: #fff;
	font-size: 16px;
	cursor: pointer;
	transition: background-color 0.3s;
	margin-bottom: 20px;
}

.btn-default:hover {
	background-color: #0056b3;
}
input[type="password"]{
	width: calc(100% - 20px);
	padding: 10px;
	margin: 5px 0;
	border: 1px solid #b0b0b0;
	border-radius: 8px;
	transition: border-color 0.3s, box-shadow 0.3s;
}

input[type="password"]:focus
	{
	border-color: #808080;
	box-shadow: 0 0 5px rgba(128, 128, 128, 0.5);
	outline: none;
}
.delete-wrap{
	max-width: 800px;
	margin: 0 auto;
	padding: 20px;
	border: 1px solid #ddd;
	border-radius: 10px;
	background-color: #ffffff;
	box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
}
</style>

</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp" />

<section class="section delete-wrap">
	<div class="page-title">회원 탈퇴</div>
     <form action="/member/delete" method="post" id="deleteForm">
    	<input type="hidden" id="memberNo" name="memberNo" value="${loginMember.memberNo}">
    	
	    	<div class="input-wrap">
			    <p>회원탈퇴를 하시려면 비밀번호를 입력해주세요.</p>
		    	<div>
			        <div class="input-item">
			         <input type="password" id="memberPw" name="memberPw" placeholder="비밀번호" />
			        </div>
			        <p id="pwMessage" class="input-msg"></p>
		        </div>
	        </div>
	        	
	    <div class="input-wrap">   
         <div class="input-item">
            <input type="password" id="pwChk" name="pwChk" placeholder="비밀번호 확인" />
          </div>
            <p id="pwMessage" class="input-msg"></p>
        </div>
        <div class="delete-button">
        	<!--  <button type="submit" id="delete" name="delete" class="btn btn-primary">회원탈퇴</button>-->
        	<input type="submit" id="delete" name="delete" value="회원 탈퇴" class="btn btn-primary"> 
        </div>
        <div class="cancel-button">
    		<a href="/" id="cancelButton" class="btn btn-default">취소</a>
		</div>
    	
	</form>
   </section>
   	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
    <script>
    
	   	$("#cancelButton").on("click", function(event) {
	        event.preventDefault();
	        window.location.href = "/";
	  	 });
	   	
		$(document).ready(function(){
			
			const checkObj = {
					"memberPw" : false,		
				}
				
			
			const memberPw = $('#memberPw');
			const pwMessage = $('#pwMessage');
			
			memberPw.on('input',function(){
				checkObj.memberPwChanged = true;
		        pwMessage.removeClass('valid');
		        pwMessage.removeClass('invalid');
		        
		        const regExp = /(?=.*[0-9])(?=.*[!@#$%^&*()-_=+])(?=.*[a-zA-Z])[a-zA-Z0-9!@#$%^&*()-_=+]{8,20}$/;

		        
		        if(regExp.test($(this).val())){
		            checkObj.memberPw = true;
		            pwMessage.html("");
		            pwMessage.addClass("valid");
		        }else{
		            pwMessage.html("올바른 비밀번호가 아닙니다");
		            pwMessage.addClass("invalid");
		            checkObj.memberPw = false;
		        }
		    });
		
			$("#delete").on("click", function(){
				 event.preventDefault();
				if($("#memberPw").val()==""){
					alert("비밀번호를 입력해주세요");
					$("#memberPw").focus();
					return false;
				}
				
				if($("#pwChk").val()==""){
					alert("비밀번호 확인을 입력해주세요");
					$("#pwChk").focus();
					return false;
				}
				
				if ($("#memberPw").val() != $("#pwChk").val()) {
					alert("비밀번호가 일치하지 않습니다.");
					$("#memberPw").focus();
					 
					return false;
					}
				$.ajax({
		            url: "/member/deleteCheck",
		            type: "POST",
		            dataType: "json",
		            data: $("#deleteForm").serializeArray(),
		            success: function(data) {
		                // 비밀번호가 맞으면
		                if (data === 1) {
		                    // 탈퇴 확인 창 띄우기
		                    if (confirm("탈퇴하시겠습니까?")) {
		                        $("#deleteForm").submit(); // 폼 제출
		                    }
		                } else {
		                    // 비밀번호가 틀리면
		                    alert("비밀번호를 확인해주세요.");
		                }
		            },
		            error: function() {
		                alert("서버 오류가 발생했습니다. 다시 시도해주세요.");
		            }
		        });
			});
		});
	</script>