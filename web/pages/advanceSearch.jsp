<%@page import="com.ruanko.house.beans.Admin"%>
<%@page import="com.ruanko.house.beans.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
<title>房产管理系统--高级查询</title>
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
			<span class="chat"> </span>
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

			<div id="listitem" class="box">

				<div id="search-room" class="box">
					<div class="callout">
						<h2>高级查询：</h2>
					</div>
					<br>
				</div>
				<!-- 高级条件查询表单 -->
				<form id="form1"
					action="houseServlet?method=advanceSearch&shouPage=1" method="post">
					<div id="item" style="margin: 10px">
						<p style="font-size: 25px">
							<span style="font-size: 18px;">区域:</span> <input
								style="margin-left: 50px" class="location" type="text"
								name="address" required />
						</p>
					</div>
					<div id="item" style="margin: 10px">
						<p>
							<span style="font-size: 18px;">价格区间:</span> <input id="price1"
								style="margin-left: 18px" class="location" type="text"
								name="price1" required />-- <input id="price2" class="location"
								type="text" name="price2" required />
						</p>
					</div>
					<div id="item" style="margin: 10px">
						<p>
							<span style="font-size: 18px;">房型:</span> <input
								style="margin-left: 55px" class="location" type="text"
								name="type" required />

						</p>
					</div>
					<input class="search_btn" type="submit" value="搜索"
						onclick="return checkPrice();" />
				</form>
				<br>

				<div id="list-box" class="box f-left" style="marin-top: 18px;">
					<c:forEach var="house" items="${list1 }">
						<table class="list-room">
							<tr>
								<td width="20%"><a
									href="houseServlet?method=houseDetail&id=${house.id }"> <img
										class="unit-pic" src="${house.path }" width="50px"
										height="50px" />
								</a></td>
								<td><a
									href="houseServlet?method=houseDetail&id=${house.id }">[</a> <br /> <br /> <span>${house.type } </span>&nbsp; <span
									style="color: #aaaaaa;">地址：${house.address }</span> <br /></td>
								<td valign="top" class="unit-price" width="25%"><c:choose>
                                    <c:when test="${house.saleRent ==1}">￥${com.shy.house.price }元</c:when>
                                    <c:otherwise>￥${com.shy.house.price }元/月</c:otherwise>
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

				</div>

			</div>

			<div id="footer">
				<ul>
					<li><a target="_blank" href="http://wh.ganji.com/">赶集</a></li>
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
	</div>
</body>
<script type="text/javascript">
	//验证输入数据类型
	function checkPrice() {
		var price1 = document.getElementById("price1");
		var price2 = document.getElementById("price2");

		// 非空
		//alert("是数值吗");
		if (isNaN(price1.value)) {
			alert("价格请输入数字！");
			price1.focus();
			return false;
		}
		if (isNaN(price2.value)) {
			alert("价格请输入数字！");
			price2.focus();
			return false;
		}
		if (parseInt(price1.value) > parseInt(price2.value)) {
			alert("第一个价格数字应小于第二个价格数字！");
			price1.focus();
			return false;
		}
	}
</script>
</html>