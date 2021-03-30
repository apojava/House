$().ready(function(){
	$("#registerform").validate({
		errorElement : "span",
		errorPlacement : function(error, element) {
			var errorplace = element.parent("dd").next().first("span");
			errorplace.text("");
			error.appendTo( errorplace );
		},
		submitHandler:function(form){
			alert("submitted");  
			form.submit();
        },
		rules: {
			username: {
				required : true,
				rangelength : [4,20],
				remote : {
					url:"http://loclhost:8080/newAnt15/users.do?action=checkExist",   
					type:"post",
					dataType:"json",
					data:{
						name : function (){
							return $("#username").val();
						}
					}
				}
			},
			mobile: {
				digits: true
			},
			email: {
				email: true
			},
			password: {
				required: true,
				rangelength:[6,12]
			},
			password2: {
				required: true,
				rangelength:[6,12],
				equalTo: "#password"
			}
		},
        messages: {
			username: {
				required : "<span class='validatorMsg validatorError'>请填写用户名</span>",
				rangelength : jQuery.format("<span class='validatorMsg validatorError'>{0}-{1}个字符（汉字、字母、数字、下划线）</span>"),
				remote : "<span class='validatorMsg validatorError'>该用户名已存在</span>"
			},
			mobile: {
				digits: "<span class='validatorMsg validatorError'>请输入手机号码</span>"
			},
			email: {
				email: "<span class='validatorMsg validatorError'>请输入正确的email地址</span>"
			},
			password: {
				required: "<span class='validatorMsg validatorError'>请输入密码</span>",
				rangelength: jQuery.format("<span class='validatorMsg validatorError'>{0}-{1}个字符</span>")
			},
			password2: {
				required: "<span class='validatorMsg validatorError'>请输入确认密码</span>",
				rangelength: "<span class='validatorMsg validatorError'>{0}-{1}个字符</span>",
				equalTo: "<span class='validatorMsg validatorError'>两次输入密码不一致</span>"
			}
		}
    });

	init("#username", "#label_username")
	init("#password", "#label_password")
	init("#password2", "#label_password2")
	init("#mobile", "#label_mobile")
	init("#email", "#label_email")

	function init(id, label) {
		$(id).focus(function(){  
			$(label).css("display","none");
		})
		$(id).blur(function(){
			if ($(id).val() == "")
			{
				$(label).css("display","block");
			}
		})
	}

	
});