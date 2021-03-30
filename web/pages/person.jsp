<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人中心</title>
<link href="css/style.css" type="text/css" rel="stylesheet" />
<link href="css/frame.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="js/jquery-1.6.2.js"></script>
<base href="">
</head>
<body>
	<%
		Object userName = session.getAttribute("user");
		if (userName == null) {
			response.sendRedirect("userServlet?method=toLogin");
		}
	%>
	<div id="wrapper">
		<div id="head">
			<h1 class="f-left">
				<a style="font-size: 26px;" href="index">房产管理</a>
			</h1>
			<span class="chat"></span>
			<ul>
				<li><a href="houseServlet?method=toCreateHouse">发布房源</A></li>
				<li>|</li>
				<li><a href="userServlet?method=logout">退出</A></li>
			</ul>
		</div>

		<div id="cols">

			<div id="aside">
				<dl>
					<dt>基本信息</dt>
					<dd>
						<a href="userServlet?method=personInfo" target="person"
							id="personInfo">个人资料</a>
					</dd>
				</dl>
				<dl>
					<dt>房产资源管理</dt>
					<dd>
						<a href="houseServlet?method=personHouse" target="person">我的发布</a>
					</dd>
				</dl>
			</div>

			<div id="content">
				<iframe id="iframeInfo" name="person"
					src="userServlet?method=personInfo" width="100%" height="600px"></iframe>

			</div>

		</div>
		<div id="footer">
			<ul>

				<li><a onclick="alert('功能待定敬请期待')" href="#">《隐私条款》</a></li>
				<li>｜</li>

				<li><a onclick="alert('功能待定敬请期待')" href="#">帮助</a></li>
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