<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="poly.util.CmmUtil"%>
<!DOCTYPE html>

<%
//Contlloer에서 받은 값을 저장하는 변수 
String res = CmmUtil.nvl((String)request.getAttribute("res"),"0");
%>
<html>
<head>
<meta charset="UTF-8">
<title>학교 식단 정보</title>
</head>
<body>
학교애서 받아오는 식단의 개수는 <%=res %>개입니다.
</body>
</html>