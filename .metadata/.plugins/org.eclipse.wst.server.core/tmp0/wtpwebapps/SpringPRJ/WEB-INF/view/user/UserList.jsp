<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="poly.util.CmmUtil"%>
<%@page import="poly.dto.UserDto"%>
<%
	List<UserDto> rList = (List<UserDto>) request.getAttribute("rList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="UserList">
		<table border='1' id="UserList">
			<tr>
				<td>User ID</td>
				<td>User NAME</td>

			</tr>

			<%
				for (UserDto e : rList) {
			%>
			<tr>
				<td><%=e.getUser_id()%></td>
				<td><%=e.getName()%></td>
			</tr>
			<%
				}
			%>
		</table>
		</div>
</body>
<script>
	function UserList() {
		//alert("test");
		var user_id = $('#user_id').val();
		//alert("제목 : " + post_title);
		if ($('#user_id').val() == "") {
			$('#user_id').focus();
			return false;
		}

		console.log("user_id : " + user_id);

		$.ajax({
			url : '/redirect',
			type : 'post',
			data : {
				"user_id" : user_id
			},
			success : function(data) {
				console.log("test");
				console.log(data)

				var resHTML = "";
				resHTML += '<tr>';
				resHTML += '<td>User_ID</td>';
				resHTML += '<td>User NAME</td>';
				resHTML += '</tr>';

				if (data.length == 0) {

					resHTML += '<tr>';
					resHTML += '<td>-</td>';
					resHTML += '<td>-</td>';
					resHTML += '</tr>';

				} else {

					for (var i = 0; i < data.length; i++) {
						resHTML += '<tr>';
						resHTML += '<td>' + data[i].user_id + '</td>';
						resHTML += '<td>' + data[i].name + '</td>';
						resHTML += '</tr>';
					}

				}
				$("#UserList").html(resHTML);

			}
		})
	}
</script>
</html>