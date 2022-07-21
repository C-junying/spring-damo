$(function(){
	// 点击登录转换到登录界面
	$("#signIn").click(()=>{
		$(".container").removeClass("right-panel-active");

	});
	// 点击注册，转换到注册界面
	$("#signUp").click(()=>{
		$(".container").addClass("right-panel-active");
	});
	//是否要提示
	let flag = false;
	// 点击表单按钮提交，没填的项，显示提示
	$("button[type='button']").click(function(){
		let input = $(this).parents("fieldset").find("input");
		let aside = $(this).parents("fieldset").find("aside");
		for(let i = 0;i<input.length;i++){
			if(input[i].value === ""){
				aside[i].style.display = "block";
				flag = true;
			}else{
				aside[i].style.display = "none";
			}
		}
	});
	//注册提交
	$("#form1 .btn").click(function (e){
		if(flag){
			flag = false;
			return false;
		}
		let registerData = $("#form1").serialize();
		console.log(registerData);
		$.ajax({
			url:'/user/register',
			type:'POST',
			data:registerData,
			dataType: 'json',
			success:function (json){
				if(json.state == 200){
					alert("注册成功");
				}else{
					alert(json.message);
				}
			},
			error:function (xhr){
				alert("注册时产生未知错误！"+xhr.message);
			}
		});
	});
	//登录提交
	$("#form2 .btn").click(function (){
		if(flag){
			flag = false;
			return false;
		}
		let resultData = $("#form2").serialize();
		console.log(resultData);
		$.ajax({
			url: "/user/login",
			type: "post",
			data: resultData,
			dataType: "json",
			// contentType : 'application/json',
			success:function (json){
				if(json.state == 200){
					alert("登录成功");
					location.href="/user/home";
				}else{
					alert(json.message);
				}
			},
			error:function (xhr){
				alert("登录时产生未知的异常",xhr.message);
			}
		});
	});
});
// 登录按钮，清除显示的提示，与对应的值
function cleaner(){
	let input = document.querySelectorAll("form input");
	let aside = document.querySelectorAll("aside");
	for(let i of input){
		if(i.className === "btn"){
			continue;
		}
		i.value = "";
	}
	for(let i of aside){
		i.style.display="none";
	}
}