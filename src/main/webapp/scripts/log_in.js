//console.log("hello!你好！");

$(function() {
	// console.log("hello!你好2！");
	// var obj = $('#login');
	// console.log(obj);
	$('#login').click(loginAction);
	$('#count').on('blur', checkName).on('focus', function() {
		$('#count_msg').empty();
	}).focus();
	$('#password').on('blur', checkPassword).on('focus', function() {
		$('#password_msg').empty();
	});
});
function checkName() {
	// console.log("checkName");
	var name = $('#count').val();
	if (name == '') {
		$('#count_msg').empty().append("不能为空");
		return false;
	}
	var reg = /^\w{3,10}$/;
	if (reg.test(name)) {
		$('#count_msg').empty();
		return true;
	}

	$('#count_msg').empty().append("3~10字符");
	return false;

}
function checkPassword() {
	 
	var password = $('#password').val();
	console.log(password);
	if (password == '') {

		$('#password_msg').empty().append("不能为空");
		return false;
	}

	var reg = /^\w{3,10}$/;
	if (reg.test(password)) {
		$('#password_msg').empty();
		return true;
	}

	$('#password_msg').empty().append("3~10字符");
	return false;
}
function loginAction() {
	// console.log("就想试一下");
	var name = $('#count').val();
	var password = $('#password').val();
	// 表单检查，如果没有用户名、密码 ，就不再提交了！
	var namePass = checkName();
	var pwdPass = checkPassword();
	if (!namePass || !pwdPass) {
		return false;
	}

	$.ajax({
		url : 'account/login.do?' + Math.random,
		type : 'POST',
		data : {
			'name' : name,
			'password' : password
		},
		datatype : 'JSON',
		success : function(result) {
			// result 就是服务器发送回的JSONResult对象
			// state和data 是属性
			if (result.state == SUCCESS) {
				console.log(result.data);
				console.log("登录成功！");
				// 保存userId到cookie
				setCookie(USER_ID, result.data.id);
				window.location.href = 'edit.html';
				return;
			}
			var filed = result.state;
			//console.log(filed);
			if (filed == 1) {
				// 显示用户名错误
				// console.log(result.message);
				$('#count_msg').empty().append(result.message);
			}
			if (filed == 2) {
				// 显示密码错误
				// console.log(result.message);
				$('#password_msg').empty().append(result.message);
			}
			// alert(result.message);

		}

	});
}