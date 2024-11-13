<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>회원 탈퇴</h3>
    <form action="/member/delete" method="post" id="deleteForm">
    <input type="hidden" id="memberNo" name="memberNo" value="${login.memberNo}">
    
            <div>
                <p>회원탈퇴를 하려면 비밀번호를 입력해주세요.</p>
                <div class="form-group">
                    <input type="password" id ="memberPw" name="memberPw" placeholder="비밀번호" />
                </div>
                <div class="form-group">
                    <input type="password" id="pwChk" name="pwChk" placeholder="비밀번호 확인" />
                    <p id="pwMessage" class="input-msg"></p>
                </div>
                <button type="button" id="delete" name="delete" class="btn btn-primary">회원탈퇴</button>
                <a href="/member/main" class="btn btn-default">취소</a>
            </div>
    
    </form>
    <script>
		$(document).ready(function(){
			
			const checkObj = {
					"memberPw" : false,		
				}
				
			
			const memberPw = $('#memberPw');
			const pwMessage = $('#pwMessage');
			
			memberPw.on('input',function(){
				pwMessage.removeClass('valid');
				pwMessage.removeClass('invalid');
				
				const regExp = /^[a-zA-z0-9!@#$%^&*()-_=+]{8,20}$/;
				
				if(regExp.test($(this).val())){
					checkObj.memberPw = true;
					if($(memberPwConfirm).val().length<1){
						pwMessage.html("");
						pwMessage.addClass("valid");
					}else{
						checkPw();
					}
					pwMessage.html("");
					pwMessage.addClass("valid");
				}else{
					pwMessage.html("비밀번호 형식이 유효하지 않습니다")
					pwMessage.addClass("invalid");
					checkObj.memberPw = false;
				}
			});
		
			$("#delete").on("click", function(){
				
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
					url : "/member/deleteCheck",
					type : "POST",
					dataType : "json",
					data : $("#deleteForm").serializeArray(),
					success: function(data){
						
						if(data==0){
							alert("비밀번호를 확인해주세요.");
							return;
						}else{
							if(confirm("탈퇴하시겠습니까?")){
								$("#deleteForm").submit();
							}
							
						}
					}
				})
			});
		})
	</script>