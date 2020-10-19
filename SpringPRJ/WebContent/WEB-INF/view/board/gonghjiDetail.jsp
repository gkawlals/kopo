<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="poly.dto.GongDto"%>
<%
	GongDto rDTO = (GongDto) request.getAttribute("rDTO");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><%=rDTO.getGong_tit()%></title>
</head>
<body>
	<div style="width: 800px;">
		<h4><%=rDTO.getGong_tit()%></h4>

		<hr>

		<div style="text-align: right;">
			작성자 :
			<%=rDTO.getGong_id()%></div>
		<div>내용 : 
			<p><%=rDTO.getGong_txt()%></p>
		</div>
		<hr>
		<div style="float: left;">
			<button onclick="location.href='/board/gongji.do'">뒤로</button>
		</div>
	</div>
</body>
</html>