$(function(){
	// 点击登录转换到登录界面
	$("#signIn").click(()=>{
		$(".container").removeClass("right-panel-active");
		
	});
	// 点击注册，转换到注册界面
	$("#signUp").click(()=>{
		$(".container").addClass("right-panel-active");
	});
	// 点击表单按钮提交，没填的项，显示提示
	$("button[type='button']").click(function(){
		let input = $(this).parents("fieldset").find("input");
		let aside = $(this).parents("fieldset").find("aside");
		for(let i = 0;i<input.length;i++){
			if(input[i].value === ""){
				aside[i].style.display = "block";
			}else{
				aside[i].style.display = "none";
			}
		}
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