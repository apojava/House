<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="../js/jquery-1.6.2.js"></script>
</head>
<script type="text/javascript">

  function hehe(id){
	  top.location.href = "houseServlet?method=houseDetail&id="+id;
  }
  function aa(){
	  
  }
  
</script>
<body>
	<form method="post" action="HouseServlet.do?method=deleteHouses"
		onsubmit="return aa()">
		<table cellspacing="1" border="0">
			<tr>
				<td width="50px">编号</td>
				<td width="150px">房产名称</td>
				<td width="80px">价格(元/月)</td>
				<td width="60px">面积</td>
				<td width="80px">房产类型</td>
				<td width="65px">是否出租</td>
				<td width="80px">审核状态</td>
				<td width="80px">发布时间</td>
				<td width="40px">操作</td>
			</tr>

			<c:forEach var="house" items="${houses}">
				<tr>
					<td>${house.id }</td>
					<td><a href="" onclick="hehe(${house.id});">${house.name}</a></td>
					<td>${house.price}</td>
					<td>${house.size}</td>
					<td>${house.type}</td>
					<td><c:choose>
							<c:when test="${house.saleRent ==1}">出售</c:when>
							<c:otherwise>出租</c:otherwise>
						</c:choose></td>
					<td>
					<c:choose>
							<c:when test="${house.isCheck ==0}">待审核</c:when>
							<c:when test="${house.isCheck ==1}">审核通过</c:when>
							<c:otherwise>审核拒绝</c:otherwise>
						</c:choose>
					</td>
					<td>${house.createDate}</td>
					<td><a href="houseServlet?method=delHouse&id=${house.id }"
						onclick="if(confirm( '确定删除！ ')==false)return   false; ">删除</a></td>
				</tr>
			</c:forEach>
			<tr>
				<td>${msg}</td>
			</tr>
		</table>
	</form>
</body>
</html>