<%@ page language="java" pageEncoding="UTF-8"%>

<!-- 引入JSTL核心标签库 -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>" />
<title>留言页面</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/baseIndex.css" type="text/css" rel="stylesheet" />
<link href="css/productDetail.css" type="text/css" rel="stylesheet" />
<link rel="stylesheet" href="css/default.css" />
<script type="text/javascript" src="js/jquery-1.6.2.js"></script>
<!-- <script type="text/javascript" src="js/comment.js"></script> -->
</head>

<body>
	<%
		Object user = session.getAttribute("user");
		if (user == null) {
			out.write("<script>alert('请先登录！');window.location.href='userServlet?method=toLogin';</script>");
		} 
	%>
	<div id="wrapper">
		<!-- header start -->
		<div id="header">
		</div>
		<!-- header end -->



		<!-- product-deltail start -->
		<div class="product-deltail">
			<div class="prdduct-relation">
				<div style="background-color: #cc0000;">留言信息</div>
			</div>

			<div class="attribute">
				<div class="comments">
					<div class="comment">
						<div class="re-text">&nbsp;&nbsp;&nbsp;我要评论</div>
						<div id="reply">
							<form action="messageServlet?method=saveMessage" method="post">
								<textarea id="content" name="content"
									style="width: 100%; height: 500px"></textarea>
								<input id="cinput" type="submit" value="评论" />
							</form>
						</div>

						<div id="reply-list">
							<c:forEach items="${userAndMessages }" var="message">
								<div class="name">
									<div class="re-text">&nbsp;&nbsp;&nbsp;网友：${message.userName}</div>
								</div>
								<div class="content2">
									<div>
										<pre>${message.content}</pre>
									</div>
								</div>
								<div class="time">
									<span>评论时间：${message.createDate}&nbsp;</span>
								</div>
							</c:forEach>

						</div>
					</div>
				</div>
			</div>

		</div>
	</div>
	<!-- product-deltail end -->

	<!-- footer end -->
	<div id="footer">
		<ul>
			<li><a target="_blank" href="http://www.ruanko.com/">软酷网</a></li>
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
	<!-- footer end -->

	</div>
	<!-- main end-->

	<script type="text/javascript">
	function publish() {
		var content = $("#content").val();
		//var id = $("#product_id").val();
		if (content != "") {
			$.ajax({
				type: "post",
				url: "<%=basePath%>MessageServlet.do",
				data: {"content": content},
				dataType: "json",
				success: function(data){
					if (data!=null){
						var html="";
						for (var i = 0; i < data.length; i++) {
							 html+= "<div class=\"name\"><div class=\"re-text\">&nbsp;&nbsp;&nbsp;网友：" + data[i].userName+ "</div></div>" +
							"<div class=\"content2\"><div><pre>" +  data[i].content + "</pre></div>" +
							"</div><div class=\"time\"><span>评论时间：" +  data[i].createDate + "&nbsp;</span></div>";
							
						}
						
						$("#reply-list").prepend(html);
					} else {
						alert("请确定是否登录，是否购买已付款！否则不能发表评论!");					
					}
				}
			});
		}
	}
		
  </script>

</body>

</html>