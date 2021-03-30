<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>基本信息</title>
</head>
<body>
	<table>
		<tr>
			<td>图像：</td>
			<td><img alt="" src="${sessionScope.user.imgPath }"
				width="100px" height="100px"></td>
		</tr>
		<tr>
			<td>姓名：</td>
			<td>${sessionScope.user.name }</td>
		</tr>
		<tr>
			<td>联系方式：</td>
			<td>${sessionScope.user.mobile }</td>
		</tr>
		<tr>
			<td>邮箱：</td>
			<td>${sessionScope.user.email }</td>
		</tr>
	</table>
</body>
</html>