<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 화면</title>
<script type="text/javascript">
	function newUser(f){
	if(f.user_id.value==""){
		alert("아이디를 입력해주세요 ");
		f.user_id.focus();
		return false;
	}if(f.password.value==""){
		alert("비밀번호를 입력해주세요 ");
		f.password.focus();
	}
	}
</script>
</head>
<body>
<form action="/user/newUser.do" method="post" name="f" onsubmit="return newUser(this)">
<table>
<tr>
<td>아이디</td> 
<td><input type="text" name="user_id"></td> 
</tr>	
<!--  <a href="/user/idcheck"> <input type="button" value="중복확인"> </a>-->
<tr>
<td>이름</td> 
<td><input type="text" name="name"></td>
</tr>
<tr>
<td>비밀번호</td>
<td> <input type="password" name="password" maxlength="10">
</td>
</tr>
<tr>
<td>Email</td>
<td><input type="email" name="email"></td>
</tr>
<tr >
<td colspan='2'><input type="submit" value="회원가입"></td>
</tr>
</table>
</form>
</body>
</html>