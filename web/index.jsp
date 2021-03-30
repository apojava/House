<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>房产管理系统</title>
<link href="css/style.css" type="text/css" rel="stylesheet" />
<link href="css/list.css" type="text/css" rel="stylesheet" />
</head>
<script type="text/javascript">
	
</script>
<body>
	<div id="wrapper">
		<div id="head">
			<h1 class="f-left">
				<a href="index" style="font-size: 26px;">房产管理</a>
			</h1>
			<span class="chat"> <!-- 放图片 -->
			</span>
			<ul>
				<c:if test="${empty user}">
					<li><a href="userServlet?method=toRegister">注册</a></li>
					<li>｜</li>
					<a href="userServlet?method=toLogin">登录</a>
				</c:if>
				<c:if test="${!empty user}">

					<li><c:choose>
							<c:when test="${user.type==0 }">
							[你好！<a href="userServlet?method=toUserCenter">${user.name }</a>]
						</c:when>

							<c:when test="${user.type==1 }">
							[你好！<a href="userServlet?method=toAdminCenter">${user.name }</a>]
						</c:when>
						</c:choose></li>
					<li>|</li>
					<li><a href="userServlet?method=logout">退出</a></li>
				</c:if>
			</ul>
		</div>

		<div id="cols">
			<div id="search-room" class="box">
				<div class="callout">
					<h2>输入名称或地址查询：</h2>
				</div>
				<div class="search">
					<form action="houseServlet?method=searchHouse&shouPage=1"
						method="post">
						<input class="location" type="text" name="name" value="${name }" />
						<input class="search_btn" type="submit" value="搜索" />
					</form>
				</div>
				<a href="houseServlet?method=toAdvanceSearch"
					style="font-size: 15px; padding-top: 10px;">高级查询</a>
			</div>

			<div id="list-box" class="box f-left">
				<c:forEach var="house" items="${list1 }">
					<table class="list-room">
						<tr>
							<td width="20%"><a
								href="houseServlet?method=houseDetail&id=${house.id }"> <img
									class="unit-pic" src="${house.path }" width="50px"
									height="50px" />
							</a></td>
							<td><a
								href="houseServlet?method=houseDetail&id=${house.id }">[<c:choose>
										<c:when test="${house.saleRent ==1}">出售</c:when>
										<c:otherwise>出租</c:otherwise>
									</c:choose>]${house.name }
							</a> <br /> <br /> <span>${house.type } </span>&nbsp; <span
								style="color: #aaaaaa;">地址：${house.address }</span> <br /></td>
							<td valign="top" class="unit-price" width="25%"><c:choose>
									<c:when test="${house.saleRent ==0}">￥${house.price }元/月</c:when>
									<c:otherwise>￥${house.price }元</c:otherwise>
								</c:choose></td>
						</tr>
					</table>

				</c:forEach>
				<div class="pagination">
					<%
						if (request.getAttribute("pageCount") != null) {
							Integer size = (Integer) request.getAttribute("pageCount");
							int s = size;
							for (int i = 0; i < s; i++) {
					%>
					<a class="active_a" target="_self"
						href="<%=basePath%>HouseServlet.do?method=serachByName&shouPage=<%=i+1%>&name=${name}"><%=i + 1%></a>
					<%
						}
						}
					%>
				</div>
				<!--  <div class="pagination">
					<span class="active_tnt_link">1</span> <a class="active_a" href="#"
						target="_self">2</a> <a class="active_a" href="#" target="_self">3</a>
					<a class="active_a" href="#" target="_self">4</a> 
					<a class="active_a" href="#" target="_self">5</a> ... 
					<a class="active_a" href="#" target="_self">98</a> 
				</div>-->
			</div>

			<!-- 			<div id="ads" class="box f-right"> -->
			<!-- 				预留广告位 -->
			<!-- 				暂无广告 -->
			<!-- 			</div> -->



		</div>

		<div id="footer">
			<ul>
				<li><a target="_blank" href="http://www.ruanko.com/">软酷网</a></li>
				<li>｜</li>

				<li><a href="#" onclick="alert('功能待定敬请期待')">帮助</a></li>
				<li>｜</li>

				<li><a target="_blank"
					href="messageServlet?method=getMessageList">留言</a></li>
			</ul>

			<div class="license">
				联系客服 400-733-3377&nbsp;客服邮件：service@ruanko.com <br /> <br />
				网站备案/许可证号：鄂ICP备11045189号 <br /> 网络技术（武汉）有限公司
			</div>
		</div>
	</div>
</body>
</html>