<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<table border="1">
<%for(int i = 0; i < 3; i++){ %>
<tr>
<%for(int j = 0; j < 4; j++){ %>
<td><%=i %>�� <%=j %>��</td>
<% } %>
</tr>
<% } %>
</table>
</body>
</html>