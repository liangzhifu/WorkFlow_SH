$(function(){
	loginObj.init();
	if(error == "" || error == " "){
		
	}else {
		var errorStr = document.getElementById("errorMsg");
		var errorBox = document.getElementById('J_Message');
		errorStr.innerHTML = error;
		errorBox.style.display = "block";
	}
});

var loginObj = (function(){
	var currentObj;
	//URL
	var loginUrl = contextPath + "/loginAuthenticate.do";
	var mainframeUrl =contextPath + "/index.jsp";
	var imagesPath = contextPath + "/jsp/login/images";

	return{
		
		init:function(){
			currentObj = this;
			$("#loginBtn").bind("click", function(){
				currentObj.login();
			});
		},
		
		//登录
		login:function(){		
			var userName = $("#userName").val();
			var password = $("#password").val();
			var loginBtn = document.getElementById("loginBtn");
			var errorStr = document.getElementById("errorMsg");
			var errorBox = document.getElementById('J_Message');
			var userNameInput = document.getElementById('userName');
			var passwordInput = document.getElementById('password');
			var userConfImg = document.getElementById('usernameConfirmImage');
			
			if (userName=='' && password=='') {
				errorStr.innerHTML = "请输入用户名和密码";
				errorBox.style.display = "block";
				userNameInput.focus();
				return;
			}else if (password=='') {
				errorStr.innerHTML = "请输入密码";
				errorBox.style.display = "block";
				passwordInput.focus();
				return;
			}

			loginBtn.src = imagesPath+"/button_login.gif";
			loginBtn.disabled=true;
			errorBox.style.display = "none";

			//验证用户名和密码
			$.post(loginUrl, {userName:userName, password:password}, function(data){
				var jresult = angular.fromJson(data);
				if(jresult.loginState == 'success'){
					window.location.href = mainframeUrl;
				}else {
					loginBtn.disabled=false;
					loginBtn.src = imagesPath+"/button_login_n.png";
					var errorMsg = "";
					switch(jresult.loginState)
					{
						case "UnknowUser":
							errorMsg="用户名不存在，请重新输入";
                            userNameInput.value = "";
							passwordInput.value="";
							userNameInput.focus();
							break;
						case "WrongPassword":
							errorMsg="密码错误";
							passwordInput.value="";
							passwordInput.focus();
							break;
						default:
							errorMsg="用户名或密码错误";
					}
					errorStr.innerHTML = errorMsg;
					errorBox.style.display = "block";
				}
			});
		}
		
	}
})();