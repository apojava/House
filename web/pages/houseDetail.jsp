<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>房产详情</title>
<link href="css/style.css" type="text/css" rel="stylesheet" />
<link href="css/detail.css" type="text/css" rel="stylesheet" />
</head>
<body>
	<div id="wrapper">
		<div id="head">
			<h1 class="f-left">
				<a href="index" style="font-size: 26px;">房产管理</a>
			</h1>
			<span class="chat"></span>
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

			<!-- 房屋标题 -->
			<div id="room-tit">
				<p>
					<a href="index">首页</a> » 房屋详情
				</p>
				<h1>${house.name }</h1>
				<h4>地址：${house.address }</h4>
			</div>

			<!-- 左侧信息 -->
			<div id="left-detail">

				<!-- 房屋图片-->
				<div class="room-fla">
					<img id="pic" class="big-img" src="${house.path }" width="80px"
						height="400px" />
					<!-- 图片列表 -->
					<!-- <ul>
						<li><a href="#"><img class="icon"
								src="images/default.jpg" /> </a></li>
						<li><a href="#"><img class="icon"
								src="images/default-house.jpg" /> </a></li>
					</ul>  -->
				</div>

				<div class="room-fla">
					<table class="detail">
						<tr>
							<th>房屋描述</th>
						</tr>
						<tr>
							<td>${house.description }</td>
						</tr>

					</table>
					<table class="info-table">
						<tr>
							<td>房屋类型：</td>
							<td>${house.type }</td>
						</tr>
						<tr class="odd">
							<td>租售状态：</td>
							<td><c:choose>
									<c:when test="${house.saleRent ==1}">出售</c:when>
									<c:otherwise>出租</c:otherwise>
								</c:choose></td>
						</tr>
						<tr>
							<td>面积：</td>
							<td>${house.size }平方米</td>
						</tr>

					</table>
					<!-- 房屋信息表格展示 end -->
				</div>
			</div>

			<!-- 右侧信息 -->
			<div id="right-detail">


				<div id="person" class="room-fla">
					<!-- 房东信息 -->
					<img src="${houseUser.imgPath }" />
					<h2>${houseUser.name}<br>${houseUser.mobile }</h2>
					<a href="#"></a>
				</div>

				<div id="same-room" class="room-fla">
					<h2>房东的其他房屋</h2>
					<table>
						<c:forEach var="house" items="${houses }">
							<tr>
								<td width="40%"><a
									href="houseServlet?method=houseDetail&id=${house.id }"><img
										src="${house.path}" /> </a></td>
								<td valign="top"><span><c:choose>
											<c:when test="${house.saleRent ==0}">￥${house.price }元</c:when>
											<c:otherwise>￥${house.price }元/月</c:otherwise>
										</c:choose></span> <br /> ${house.address}</td>
							</tr>
						</c:forEach>


					</table>
				</div>
			</div>

		</div>

		<div id="footer">
			<ul>

				<li><a target="_blank" href="http://www.ruanko.com">友情链接</a></li>
				<li>｜</li>
				<li><a onclick="alert('功能待定敬请期待')" href="#">全部城市</a></li>
				<li>｜</li>
				<li><a onclick="alert('功能待定敬请期待')" href="#">精彩专题</a></li>
				<li>｜</li>

				<li><a onclick="alert('功能待定敬请期待')" href="#">《隐私条款》</a></li>
				<li>｜</li>

				<li><a onclick="alert('功能待定敬请期待')" href="#">帮助</a></li>
				<li>｜</li>

				<li><a target="" href="messageServlet?method=getMessageList">留言</a></li>
			</ul>

			<div class="license">
				联系客服 400-733-3377&nbsp;客服邮件：service@ruanko.com <br /> <br />
				网站备案/许可证号：鄂ICP备11045189号 <br /> 网络技术（武汉）有限公司
			</div>
		</div>
	</div>
</body>
</html>