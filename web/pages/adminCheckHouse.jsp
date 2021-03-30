<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">

  function hehe(id){
	  top.location.href = "houseServlet?method=houseDetail&id="+id;
  }
</script>
<body>
	<table cellspacing="1" align="center">
		<tr>
			<td width="60px">编号</td>
			<td width="200px">房产名称</td>
			<td width="60px">发布人</td>
			<td width="120px">联系方式</td>
			<td width="100px">发布时间</td>
			<td width="80px">审核状态</td>
			<td>操作</td>
		</tr>
		<c:forEach items="${userAndHouses }" var="userAndHouse">
			<tr>
				<td><a href="" onclick="hehe(${userAndHouse.houseId});">${userAndHouse.houseId}</a></td>
				<td>${userAndHouse.houseName}</td>
				<td>${userAndHouse.userName}</td>
				<td>${userAndHouse.mobile}</td>

				<td>${userAndHouse.createDate}</td>
				<td><c:choose>
						<c:when test="${userAndHouse.isCheck==0 }">
							<font color="blue">待审核</font>
						</c:when>
						<c:when test="${userAndHouse.isCheck==1 }">
							<font color="red">审核通过</font>
						</c:when>
						<c:when test="${userAndHouse.isCheck==2 }">
							<font color="grey">审核拒绝 </font>
						</c:when>
					</c:choose></td>
				<td><c:choose>
						<c:when test="${userAndHouse.isCheck==0 }">
							<a
								href="houseServlet?method=check&id=${userAndHouse.houseId }&chkState=1"
								onclick="alert('审核通过！')">通过</a> | <a
								href="houseServlet?method=check&id=${userAndHouse.houseId }&chkState=2"
								onclick="alert('审核拒绝！')">拒绝</a>
						</c:when>
					</c:choose></td>
			</tr>
		</c:forEach>
	</table>
	<c:if test="${empty userAndHouses}">
		<div class="tbox"
			style="border: 1px solid brown; text-align: center; color: brown; font-size: 18px;">
			<!-- 此处显示提示信息 -->
			您还没有此项数据
		</div>
	</c:if>

</body>
</html>