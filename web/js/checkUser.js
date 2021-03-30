var nameVaildate = false;
var passwordVaildate = false;
var mobileVaildate = false;
var emailVaildate = false;
function mysubmit() {
//	alert("submithhh");
	var name = $("#name").val();
	var password = $("#password").val();
	var mobile = $("#mobile").val();
	var email = $("#email").val();
	var realName = $("#realName").val();
	var imgPath = $("path").val();
	
	if (nameVaildate == true && passwordVaildate == true
			&& mobileVaildate == true && emailVaildate == true) {
		return true;
//		$("#registerform").submit();
//		$.ajax({
//			type: "post",
//			url: "userServlet?method=register",
//			data: {"name": name, "password": password,"realName":realName,"mobile":mobile,"email":email,"imgPath":imgPath},
//			dataType: "json",
//			success: function(data){
//				if (data=="success"){
//					//return submit();
//					alert("注册成功!");
//					
//				} else {
//					alert("注册失败!");					
//				}
//			}
//		});
	}else {
		alert("未输入完整和正确格式的数据");
		return false;
	}
}

function check(str) {
	if (str = "name") {
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
//								alert("注册成功!");
								$("#nameErr").hide();
								$("#nameRig").show();

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
	if (str = "password") {
		var password = $("#password").val();
		var re = /^(\w){6,20}$/;
		if (re.test(password)) {
			$("#passwordErr").hide();
			$("#passwordRig").show();
			passwordVaildate = true;
		} else {
			$("#passwordErr").show();
			passwordVaildate = false;
		}
	}
	if (str = "mobile") {
		var mobile = $("#mobile").val();
		var re = /^1\d{10}$/;
		if (re.test(mobile)) {
			$("#mobileErr").hide();
			$("#mobileRig").show();
			mobileVaildate = true;
		} else {
			$("#mobileErr").show();
			mobileVaildate = false;
		}
	}
	if (str = "email") {
		var email = $("#email").val();
		var re = /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/;
		if (re.test(email)) {
			$("#emailErr").hide();
			$("#emailRig").show();
			emailVaildate = true;
		} else {
			$("#emailErr").show();
			emailVaildate = false;
		}
	}
}
