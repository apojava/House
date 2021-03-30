<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
<link href="css/style.css" type="text/css" rel="stylesheet" />
<link href="css/reg.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="js/jquery-1.6.2.js"></script>
</head>
<script type="text/javascript">
	$(document).ready(function() {
		$('#name').focus(function() {
			$("#label_username").css("display", "none");
		});
		$('#name').blur(function() {
			$("#label_username").css("display", "block");
		});
	});
	function myLogin() {
		var name = $("#name").val();
		var password = $("#password").val();
		$.ajax({
			type : "post",
			url : "userServlet?method=login",
			data : {
				"name" : name,
				"password" : password
			},

			success : function(data) {
				if (data == "fail") {
					//$("#msg").text("用户名或密码错误");
					alert("用户名或密码错误");
				} else {
					$("#loginform").submit();
				}
			}
		});
	}
	
	function loginCheck(){
		var name = $("#name").val();
		var password = $("#password").val();
		if(name==""||password==""){
			alert("用户名和密码不能为空！");
			return false;
		}
	}
</script>
<body>
	<div id="wrapper">
		<div id="head">
			<h1 class="f-left">
					<a style="font-size: 26px;" href="index">房产管理</a>
			</h1>
			<span class="chat"></span>
			<ul>
				<li><a href="userServlet?method=toRegister">注册</a></li>
				<li>｜</li>
				<li><a href="userServlet?method=toLogin">登录</a></li>
			</ul>
		</div>

		<div id="cols">
			<div class="reg-left">
				<form id="loginform" name="loginform"
					action="userServlet?method=login" method="post">

					<dl>
						<dt>
							登录到您的账户 <span id="msg"><c:if test="${err!='' }">${err}</c:if></span>
						</dt>
						<dd class="">
							<input id="name" class="input-reg" type="text" name="name" />
						</dd>
						<dd class="">
							<input id="password" class="input-reg" type="password"
								maxlength="12" value="" autocomplete="off" name="password" />
						</dd>
					</dl>

					<p>
						<a href="javascript:void(0)">忘记密码？</a>
					</p>
					<input id="next" type="hidden" name="next" value="" />
					<p class="my-login">
						<input id="login_btn" class="" type="submit" value="登录" onclick="return loginCheck();" />

					</p>
					<p class="reg-gj">
						还没有账户吗？ <a href="userServlet?method=toRegister">立即注册</a>
					</p>
				</form>
			</div>
		</div>
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
	</div>
</body>
</html>