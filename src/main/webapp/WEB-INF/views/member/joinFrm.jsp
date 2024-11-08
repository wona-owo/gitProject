<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>회원가입</h1>
	<hr>
	
	<form action="/member/join" method="post">
	<table border='1'>
		<tr>
			<th><label for='memberId'>아이디</label></th>
				<td>
				<input type="text" id="memberId" name="memberId">
				</td>
		</tr>
		<tr>
			<th><label for='memberPw'>비밀번호</label></th>
				<td>
				<input type="password" id="memberPw" name="memberPw">
				</td>
		</tr>
		<tr>
			<th><label for='memberName'>이름</label></th>
				<td>
				<input type="text" id="memberName" name="memberName">
				</td>
		</tr>
			<tr>
			<th><label for='memberNick'>닉네임</label></th>
				<td>
				<input type="text" id="memberNick" name="memberNick">
				</td>
		</tr>
		<tr>
		<tr>
			<th><label for='memberPhone'>전화번호</label></th>
				<td>
				<input type="text" id="memberPhone" name="memberPhone">
				</td>
		</tr>
		<tr>
			<th><label for='memberAddr'>주소</label></th>
				<td>
				<input type="text" id="memberAddr" name="memberAddr">
				</td>
		</tr>
			<tr>
			<th><label for='memberGender'>성별</label></th>
				<td>
				<input type="text" id="memberGender" name="memberGender">
				</td>
		</tr>
			<tr>
			<th><label for='memberEmail'>이메일</label></th>
				<td>
				<input type="text" id="memberEmail" name="memberEmail">
				</td>
		</tr>
		<tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="회원가입">
		</tr>
	</table>
</form>
</body>
</html>