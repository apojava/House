<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 引入JSTL核心标签库 -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<title>注册</title>
<link href="css/style.css" type="text/css" rel="stylesheet" />
<link href="css/reg.css" type="text/css" rel="stylesheet" />
<link href="css/validate.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="js/jquery-1.6.2.js"></script>
<script type="text/javascript" src="js/jquery.validate.js"></script>
<script type="text/javascript" src="js/validator-register.js"></script>
<script type="text/javascript" src="js/checkUser.js"></script>
</head>

<body>
	<div id="wrapper">
		<div id="head">
			<h1 class="f-left">
				<a href="index" style="font-size: 26px;">房产管理</a>
			</h1>
			<span class="chat"></span>
			<ul>
				<li><a href="userServlet?method=toLogin">登录</a></li>
			</ul>
		</div>

		<div id="cols">
			<div class="reg-left">
				<dl>
					<dt>
						注册账户 <span id="msg"><c:if test="${err!='' }">${err}</c:if></span>
					</dt>
				</dl>
				<form id="" method="post" name="registerform" id="registerform"
					enctype="multipart/form-data" action="userServlet?method=register">
					<table>
						<tr>
							<td>用户名:</td>
							<td><input id="name" class="input-reg required" type="text"
								name="name" onblur="checkSameUser()" required /></td>
						</tr>
						<tr>
							<td colspan="2"><span id="nameErr" style=""><font
									color="red">*字母、数字、下划线组成，字母开头，4-16位*</font></span> <span id="nameRig"
								style="display: none;"><font color="blue">*填写正确*</font></span><span
								id="chongfu" style="color: red"></span></td>
						</tr>
						<tr>
							<td>密码:</td>
							<td><input id="password" class="input-reg required"
								type="password" name="password" onblur="check('password')"
								required /></td>
						</tr>
						<tr>
							<td colspan="2"><span id="passwordErr" style=""><font
									color="red">*输入6-20个字母、数字、下划线*</font></span> <span id="passwordRig"
								style="display: none;"><font color="blue">*填写正确*</font></span></td>
						</tr>
						<tr>
							<td>真实姓名:</td>
							<td><input id="realName" class="input-reg required"
								type="text" name="realName" required /></td>
						</tr>
						<tr>
							<td>手机号:</td>
							<td><input id="mobile" class="input-reg required"
								type="text" name="mobile" onblur="check('mobile')" required /></td>
						</tr>
						<tr>
							<td colspan="2"><span id="mobileErr" style="display: none;"><font
									color="red">*手机号错误，请重新填写*</font></span> <span id="mobileRig"
								style="display: none;"><font color="blue">*填写正确*</font></span></td>
						</tr>
						<tr>
							<td>邮箱:</td>
							<td><input id="email" class="input-reg required" type="text"
								name="email" onblur="check('email')" required /></td>
						</tr>
						<tr>
							<td colspan="2"><span id="emailErr" style="display: none;"><font
									color="red">*邮箱错误，请重新填写*</font></span> <span id="emailRig"
								style="display: none;"><font color="blue">*填写正确*</font></span></td>
						</tr>
						<tr>
							<td>上传图像:</td>
							<td><input id="path" class="input-reg required" type="file"
								name="imgPath" required /></td>
						</tr>
						<tr>
							<td colspan="2">
<!-- 								<a href="javascript:void(0)" onclick="mysubmit()">立刻注册</a> -->
								<input type="submit" value="立刻注册!" />
							</td>
						</tr>
					</table>

				</form>


				<p id="register">

					<!-- 	<a class="" href="#ongo">立刻注册!</a> -->
				</p>
			</div>
			<div class="reg-right">
				<dl>
					<dt class="font18">已有账号，请直接登录</dt>
					<dd>
						<a class="reg-log" href="userServlet?method=toLogin">登录</a>
					</dd>
				</dl>
			</div>
		</div>
		<div id="footer">
			<ul>
				<li><a target="_blank" href="http://www.ruanko.com/">软酷网</a></li>
				<li>｜</li>

				<li><a onclick="alert('功能待定敬请期待')" href="#">帮助</a></li>
				<li>｜</li>

				<li><a target="_blank"
					href="<%=basePath%>MessageServlet.do?method=getMessageList">留言</a></li>
			</ul>

			<div class="license">
				联系客服 400-733-3377&nbsp;客服邮件：service@ruanko.com <br /> <br />
				网站备案/许可证号：鄂ICP备11045189号 <br /> 网络技术（武汉）有限公司
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function checkSameUser(){
			
			var name = $("#name").val();
			var re = /^[a-zA-z]\w{3,15}$/;
			if (re.test(name)) {
				$.ajax({
						type : "post",
						url : "userServlet?method=registerValidater",
						data : {
							"name" : name
						},

						success : function(data) {
							if (data == "success") {

								$("#nameErr").hide();
								$("#nameRig").show();
								//alert("注册成功!");

							} else {
								$("#chongfu").text("用户名已存在");
								$("#nameErr").hide();
								$("#nameRig").hide();
								nameVaildate = false;
							}
						}
					});

				nameVaildate = true;
			} else {
				nameVaildate = false;
				$("#nameErr").show();
			}
			
		}
		
		function registerCheck(){
			var name = $("#name").val();
			var password = $("#password").val();
			if(name==""||password==""){
				alert("有未填的数据，请填写！");
				return false;
			}
			
			var realName = $("#realName").val();
			var mobile = $("#mobile").val();
			if(realName==""||mobile==""){
				alert("有未填的数据，请填写！");
				return false;
			}
			
		}
	
	</script>
</body>
</html>