
function publish() {
	var content = $("#content").val();
	//var id = $("#product_id").val();
	alert(content);
	if (content != "") {
		$.ajax({
			type: "post",
			url: "MessageServlet.do?method=saveMessage",
			data: {"content": content, "id": id},
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






