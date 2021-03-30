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
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>发布房源</title>
<link href="css/style.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="js/jquery.min.js"></script>
</head>
<script type="text/javascript">
   function upload(){
	   $.ajax({
			  url:"<%=path%>/UserServlet.do?method=upload",
			       data:{  
			             //  path : 0,  
			       },  
			      type:'post',  
			      cache:false,  
			      dataType:'json',  
			     success:function(data) {  
			          if(data!=null ){  
			             alert("删除成功！");  
			              window.location.reload();  
			          }else{  
			        	  alert("删除失败！");  
			          }  
			      },  
			      error : function() {  
			           // view("异常！");  
			            alert("异常！");  
			       }  
		  });
   }
  
</script>
<body>
	<div id="wrapper">
		<div id="head">
			<span class="chat"></span>
			<ul>
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


			<form action="houseServlet?method=createHouse"
				method="post" enctype="multipart/form-data">
				<table align="center">
					<caption style="font-size: 30px;">发布房源</caption>

					<tr>
						<td>房产名称：</td>
						<td><input id="name" type="text" name="name" msg="房产名称" required /></td>


					</tr>
					<tr>
						<td>出售类型：</td>
						<td><input type="radio" name="saleRent" value="R"
							checked="checked" /> 出租 <input type="radio" name="saleRent"
							value="S" /> 售卖</td>


					</tr>
					<tr>
						<td>户型：</td>
						<td><input id="type" type="text" name="type" msg="户型" required>如：两室一厅</td>

					</tr>
					<tr>
						<td>面积：</td>
						<td><input id="size" type="text" name="size" msg="面积" required /></td>


					</tr>
					<tr>
						<td>价格：</td>
						<td><input id="price" type="text" name="price" msg="价格" required /></td>

					</tr>
					<tr>
						<td>地址：</td>
						<td><input id="address" type="text" name="address" msg="地址" required /></td>

					</tr>
					<tr>
						<td>房产描述：</td>
						<td><textarea name="description" required></textarea></td>
					</tr>

					<tr>
						<td>上传图片：</td>
						<td><input id="file" type="file" name="path" required /></td>

					</tr>


					<tr>
						<td align="center" colspan="2"><input type="submit"
							value="发布房源" onclick="return checkNull()" style="font-family: 微软雅黑" /></td>
					</tr>

				</table>
			</form>
		</div>

		<div id="footer">
			<ul>

				<li><a onclick="alert('功能待定敬请期待')" href="#">《隐私条款》</a></li>
				<li>｜</li>

				<li><a onclick="alert('功能待定敬请期待')" href="#">帮助</a></li>
			</ul>

			<div class="license">
				联系客服 400-733-3377&nbsp;客服邮件：service@ruanko.com <br /> <br />
				网站备案/许可证号：鄂ICP备11045189号 <br /> 网络技术（北京）有限公司
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
function checkNull()
{
     var num=0;
     var str="";
     $("input[type$='text']").each(function(n){
          if($(this).val()=="")
          {
               num++;
               str+="?"+$(this).attr("msg")+"不能为空！\r\n";
          }
     });
     
     
     
     if(num>0)
     {
          alert(str);
          return false;
     }
     else
     {
	     var file=$("#file");
	     if($.trim(file.val())==''){
	            alert("请选择文件");
	            return false;
	     }
          return true;
     }
}
</script>
</html>