<%@ page import="com.menupick.member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    Member member = (Member) request.getAttribute("member");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원 정보 수정</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f0f0f0;
	margin: 0;
	padding: 0;
}
.submit-button{
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
.sub{
	width:150px;
	height:50px;
	color: white;
	background-color : #f40;
	border-style:none;
	font-size:15px;
	border-radius:30px;
}
.delete-button{
	display: flex;
	justify-content: center;
	align-items: center;
	padding-top : 30px;
	color: white;
}
.delete{
	width:150px;
	height:50px;
	color: white;
	background-color : #f40;
	border-style:none;
	font-size:15px;
	border-radius:30px;
}
.delete>p{
	padding-left: 45px;
	padding-top: 15px;
	font-size: 15px;
}
input[type="text"], input[type="password"], input[type="email"] {
	width: calc(100% - 20px);
	padding: 10px;
	margin: 5px 0;
	border: 1px solid #b0b0b0;
	border-radius: 8px;
	transition: border-color 0.3s, box-shadow 0.3s;
}

input[type="text"]:focus, input[type="password"]:focus, input[type="email"]:focus
	{
	border-color: #808080;
	box-shadow: 0 0 5px rgba(128, 128, 128, 0.5);
	outline: none;
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
.delete-button {
	padding: 10px 20px;
	border: none;
	border-radius: 5px;
	color: #fff;
	font-size: 16px;
	cursor: pointer;
	transition: background-color 0.3s;
}
.delete:hover {
	background-color: #0056b3;
	transition: background-color 0.3s;
}
</style>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp" />

<section class="section update-wrap">
    <div class="page-title">회원정보 수정</div>
    
    <form method="post" action="/member/update" onsubmit="return joinValidate()">
        <!-- hidden inputs for memberNo-->
        <input type="hidden" name="memberNo" value="${loginMember.memberNo}">
        
            <div class="input-wrap">
            	<div class="input-title">
                	<label for="memberId">아이디</label>
                </div>
                <div class="input-item">
                	<input type="text" id="memberId" name="memberId" value="${loginMember.memberId}">
                	<button type="button" id="idDuplChkBtn" class="btn-primary">중복체크</button>
            	</div>
            	<p id="idMessage" class="input-msg"></p>
            </div>
            <div class="input-wrap">
            	<div class="input-title">
                	<label for="memberPw">비밀번호</label>
                </div>
                <div class="input-item">
                	<input type="password" id="memberPw" name="memberPw" value="${loginMember.memberPw}">
            	</div>
            </div>
            <div class="input-wrap">
				<div class="input-title">
					<label for="memberPwConfirm">비밀번호 확인</label>
				</div>
				<div class="input-item">
					<input type="password" id="memberPwConfirm" name="memberPwConfirm" maxlength="20"/>
				</div>
					<p id="pwMessage" class="input-msg"></p>
				</div>
            <div class="input-wrap">
            	<div class="input-title">
                	<label for="memberName">이름</label>
                </div>
                <div class="input-item">
                	<input type="text" id="memberName" name="memberName" value="${loginMember.memberName}">
            	</div>
            	<p id="nameMessage" class="input-msg"></p>
            </div>
            <div class="input-wrap">
            	<div class="input-title">
                	<label for="memberNick">닉네임</label>
                </div>
                <div class="input-item">
                	<input type="text" id="memberNick" name="memberNick" value="${loginMember.memberNick}">
                	<button type="button" id="nickDuplChkBtn" class="btn-primary">중복체크</button>
            	</div>
            	<p id="nickMessage" class="input-msg"></p>
            </div>
            <div class="input-wrap">
            	<div class="input-title">
                	<label for="memberPhone">전화번호</label>
                </div>
                <div class="input-item">
                	<input type="text" id="memberPhone" name="memberPhone" value="${loginMember.memberPhone}">
            	</div>
            	<p id="phoneMessage" class="input-msg"></p>
            </div>
				           
				 <div class="input-wrap">
				    <div class="input-title">
				        <label for="zipp_code_id">우편번호</label>
				    </div>
                <div class="input-wrap">
            	<div class="input-title">
                	<label for="memberAddr">주소</label>
                </div>
                <div class="input-item">
               			    <input type="text" id="zipp_code_id" name="zipp_code"
								maxlength="10" placeholder="우편번호" value="${loginMember.memberAddr}"
								style="width: 50%; display: inline;">
								
							<button type="button" id="zipp_btn" class="btn btn-primary"
								onclick="execDaumPostcode()">우편번호 찾기</button>
						
							<input type="text" name="user_add1" id="UserAdd1" maxlength="40"
								placeholder="기본 주소를 입력하세요" required>
							 <input type="text"	name="user_add2" id="UserAdd2" maxlength="40"
								placeholder="상세 주소를 입력하세요"> 
            	</div>
            </div>
            </div>
            <div class="input-wrap">
            	<div class="input-title">
                	<label for="memberEmail">이메일</label>
                </div>
                <div class="input-item">
                	<input type="text" id="memberEmail" name="memberEmail" value="${loginMember.memberEmail}">
            	</div>
            </div>
            	<div class="submit-button">
                    <button type="submit" class="sub">회원 정보 수정</button>      
            	</div>
              	<div class="delete-button">
                    <a href="/member/delete" class="delete"><p>회원 탈퇴</p></a>         
            	</div>
            
    	</form>
    </section>
    <jsp:include page="/WEB-INF/views/common/footer.jsp" />
 	<script>
	const checkObj = {
		"memberId" : false,
		"idDuplChk" : false,
		"nickDuplChk" : false,
		"memberPw" : false,
		"memberPwConfirm" : false,
		"memberName" : false,
		"memberNick" : false,
		"memberPhone" : false,	
		"memberIdChanged": false,
	    "memberPwChanged": false,
	    "memberNameChanged": false,
	    "memberNickChanged": false,
	    "memberPhoneChanged": false
	}
	
	const memberId = $('#memberId'); 
	const idMessage = $('#idMessage');
	

	memberId.on('input',function(){
		checkObj.memberIdChanged = true;
        checkObj.idDuplChk = false;
        idMessage.removeClass('valid');
        idMessage.removeClass('invalid');
        
        const regExp = /(?=.*[0-9])(?=.*[a-zA-z])[a-zA-Z0-9]{6,12}$/;
        
        if(regExp.test($(this).val())){ 
            idMessage.html("");
            idMessage.addClass("valid");
            checkObj.memberId = true;
        }else{
            idMessage.html("영어 + 숫자 6~12글자 사이로 입력하세요");
            idMessage.addClass("invalid");
            checkObj.memberId = false;
        }
    });
	
	
	$('#idDuplChkBtn').on('click', function(){
        if(!checkObj.memberId){
            msg("알림", "유효한 아이디를 입력한 후 중복체크를 진행하세요", "error");
            return false;
        }
        $.ajax({
            url : "/member/idDuplChk",
            data : {"memberId" : memberId.val()},
            type : "get", 
            success : function(res){
                if(res == 0){
                    msg("알림", "사용 가능한 아이디입니다", "success");
                    checkObj.idDuplChk = true;
                }else {
                    msg("알림", "중복된 아이디가 존재합니다", "warning");
                    checkObj.idDuplChk = false;
                }
            },
            error : function(){
                console.log('ajax 오류 발생');
            }
        });
    });
	
	const memberNick = $('#memberNick'); 
	const nickMessage = $('#nickMessage');
	
	memberNick.on('input',function(){
		checkObj.memberNickChanged = true;
		checkObj.nickDuplChk = false;
		
		nickMessage.removeClass('valid');
		nickMessage.removeClass('invalid');
		
		const regExp = /^[가-힣a-zA-z0-9!@#$%^&*()-_=+]{2,10}$/;
		
		if(regExp.test($(this).val())){ 
			nickMessage.html("");
			nickMessage.addClass("valid");
			checkObj.memberNick = true;
		}else{
			nickMessage.html("한글,영어,숫자,특수문자 2~10글자 사이로 입력하세요")
			nickMessage.addClass("invalid");
			checkObj.memberNick = false;
		}
	});
	
	$('#nickDuplChkBtn').on('click', function(){
		
		if(!checkObj.memberNick){
			msg("알림", "유효한 닉네임을 입력한 후 중복체크를 진행하세요", "error");
			return false; 
		}
		
		$.ajax({
			url : "/member/nickDuplChk",
			data : {"memberNick" : memberNick.val()},
			type : "get", 
			success : function(res){
				if(res == 0){
					msg("알림", "사용 가능한 닉네임입니다", "success");
					checkObj.nickDuplChk = true;
				}else {
					msg("알림", "중복된 닉네임이 존재합니다", "warning");
					checkObj.nickDuplChk = false;
				}
			},
			error : function(){
				console.log('ajax 오류 발생');
			}
		})
		
	});
	
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
            pwMessage.html("비밀번호 형식이 유효하지 않습니다");
            pwMessage.addClass("invalid");
            checkObj.memberPw = false;
        }
    });
	
	const memberPwConfirm = $('#memberPwConfirm');
    memberPwConfirm.on('input', function(){
        if(memberPwConfirm.val() === memberPw.val()){
            pwMessage.addClass('valid');
            pwMessage.html('');
            checkObj.memberPwConfirm = true;
        }else{
            pwMessage.addClass('invalid');
            pwMessage.html('비밀번호가 일치하지 않습니다');
            checkObj.memberPwConfirm = false;
        }
    });
	
	const memberPhone = $('#memberPhone');
	const phoneMessage = $('#phoneMessage');
	
	memberPhone.on('input', function(){
		checkObj.memberPhoneChanged = true;
		phoneMessage.removeClass('valid');
		phoneMessage.removeClass('invalid');
		
		const regExp = /^010-\d{3,4}-\d{4}$/;
		
		if(regExp.test($(this).val())){
			phoneMessage.addClass('valid');
			phoneMessage.html("");
			checkObj.memberPhone = true;
		}else{
			phoneMessage.addClass('invalid');
			phoneMessage.html("전화번호 형식이 유효하지 않습니다");
			checkObj.memberPhone = false;
		}
	});
	
	const memberName = $('#memberName');
	const nameMessage = $('#nameMessage');
	
	memberName.on('input', function(){
		checkObj.memberNameChanged = true;
		nameMessage.removeClass('valid');
		nameMessage.removeClass('invalid');
		
		const regExp = /^[가-힣]{2,10}$/;
		
		if(regExp.test($(this).val())){
			nameMessage.addClass('valid');
			nameMessage.html("");
			checkObj.memberName = true;
		}else{
			nameMessage.addClass('invalid');
			nameMessage.html("이름 형식이 유효하지 않습니다");
			checkObj.memberName= false;
		}
	});
	
	function joinValidate(){
	    let str = "";

	    // 중복 체크가 필요한 항목 확인
	    if(!checkObj.idDuplChk && checkObj.memberIdChanged){
	        str = "아이디 중복체크를 진행하세요";
	        msg("정보수정 실패", str, "error");
	        return false;
	    }

	    if(!checkObj.nickDuplChk && checkObj.memberNickChanged){
	        str = "닉네임 중복체크를 진행하세요";
	        msg("정보수정 실패", str, "error");
	        return false;
	    }

	    // 형식 검증이 필요한 항목만 확인
	    for(let key in checkObj){
	        // 중복 체크와 관련 없는 항목이며, 변경된 항목만 확인
	        if(checkObj[key + "Changed"] && !checkObj[key]){  
	            switch(key){
	                case "memberId": str = "아이디 형식이 유효하지 않습니다"; break;
	                case "memberPw": str = "비밀번호 형식이 유효하지 않습니다"; break;
	                case "memberPwConfirm": str = "비밀번호 확인 형식이 유효하지 않습니다"; break;
	                case "memberName": str = "이름 형식이 유효하지 않습니다"; break;
	                case "memberPhone": str = "전화번호 형식이 유효하지 않습니다"; break;
	            }
	            str += "";
	            msg("정보수정 실패", str, "error");
	            return false;
	        }
	    }
	    return true;
	
	}
	
    function msg(title, text, icon){
        swal({
            title : title,
            text : text,
            icon : icon
        });     
    }
    
    
	</script>
	 <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <!-- CDN 방식 사용 -->
    <script>
    function execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업을 통한 검색 결과 항목 클릭 시 실행
                var addr = ''; // 주소_결과값이 없을 경우 공백 
                var extraAddr = ''; // 참고항목

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 도로명 주소를 선택
                    addr = data.roadAddress;
                } else { // 지번 주소를 선택
                    addr = data.jibunAddress;
                }

                if(data.userSelectedType === 'R'){
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                } else {
                    document.getElementById("UserAdd1").value = '';
                }

                // 선택된 우편번호와 주소 정보를 input 박스에 넣는다.
                document.getElementById('zipp_code_id').value = data.zonecode;
                document.getElementById("UserAdd1").value = addr;
                document.getElementById("UserAdd1").value += extraAddr;
                document.getElementById("UserAdd2").focus(); // 우편번호 + 주소 입력이 완료되었음으로 상세주소로 포커스 이동
            }
        }).open();
    }
	</script>
</body>
</html>