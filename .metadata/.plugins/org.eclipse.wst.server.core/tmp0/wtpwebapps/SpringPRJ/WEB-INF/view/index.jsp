<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
	function innerHTMLTest(){
			alert("test")
			var randValNode = document.getElementById("rand_val");
			randValNode.innerHTML = " <b> <font color= 'red' >"+ Math.random() + " </font> </b> ";
	}
	
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
table, th, td {
	border: 1px solid black;
}
</style>
</head>
<body>
	<!-- top 시작 -->
	<div>
	<%@ include file="/WEB-INF/view/user/top.jsp" %>
	</div>
	<!-- top 끝 -->
	와! 스프링 아시는구나! 참고로 겁.나.어.렵.습.니.다
			<div id="rand_val"> Let's generata random Value</div>
			<button onclick="innerHTMLTest()">Generata</button>
			<br><br>
			<a href="/mail/sendMail.do">메일보내기</a>
</body>
</html>