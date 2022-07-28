
var slideIndex = 0;     //图片指定位置
//图片
var imgs = ["/img/air01.jpg","/img/air02.jpg","/img/air04.jpg","/img/air05.jpg","/img/air06.jpg"];
//获取点，并插入点
var dotlist = document.getElementsByClassName("dots");
for(let i = 0;i<imgs.length;i++){
    let dot = document.createElement("span");
    dot.className = "dot";
    dot.addEventListener("click",dotOnclick.bind(this,i));
    dotlist[0].appendChild(dot);
}
//获取img，dot
var slides = document.getElementsByClassName("mySlides");
var dots = document.getElementsByClassName("dot");
//递归定时
function showSlides() {
    slideIndex++;
    if (slideIndex > imgs.length) {slideIndex = 1}    
    removeStyle();
    setTimeout(showSlides, 5000); // 切换时间为 2 秒
}
//点点击更换图片
function dotOnclick(index){
    slideIndex=index+1;
    removeStyle();
}
//更换点，图片
function removeStyle(){
    for (let i = 0; i < dots.length; i++) {
        dots[i].className = dots[i].className.replace("active", "");
    }
	slides[0].src=imgs[slideIndex-1];
    dots[slideIndex-1].className += " active";
}
showSlides();
var data = [];
$(function(){
    $.ajax({
        url: "/admin/query-city",
        type: "post",
        data: '',
        dataType: "json",
        // contentType : 'application/json',
        success:function (json){
            if(json.state == 200){
                console.log(json);
                data = json.data;
                console.log(data);
            }else{
                alert(json.message);
            }
        },
        error:function (xhr){
            alert("返回时产生未知的异常",xhr.message);
        }
    });
    $("input").attr("autocomplete","off");
    // 交换起始地址
    $(".swap").click(function(){
        let temp = $("#startCity").val();
        $("#startCity").val($("#endCity").val());
        $("#endCity").val(temp);
    });
    $("body").click(function(){
        $(".start-app").hide(400);
        $(".end-app").hide(400);
    });
    $("#startCity").click(function(e){
        $(".end-app").hide(400);
        if(data.length != 0){
            $(".start-app").show(400);
        }
        e.stopPropagation();
    });
    $("#endCity").click(function(e){
        $(".start-app").hide(400);
        if(data.length != 0){
            $(".end-app").show(400);
        }
        e.stopPropagation();
    });
    $(".submit-btn .btn").click(function (){
        let startCity = $("#startCity").val();
        let endCity = $("#endCity").val();
        let time = $("#takeoffDate").text();
        if(startCity == ''){
            alert("请填写出发城市");
            return false;
        }
        if(endCity == ''){
            alert("请填写到达城市");
            return false;
        }
        if(time == '选择日期'){
            alert("请填写日期");
            return false;
        }
        let str = time.match(/(\S*)-(\S*)-(\S*)/);
        let year = str[1];
        let month = parseInt(str[2])<10?"0"+parseInt(str[2]):str[2];
        let day = parseInt(str[3])<10?"0"+parseInt(str[3]):str[3];
        let date = `${year}-${month}-${day}`;
        location.href = `/user/user_flight?startCity=${startCity}&endCity=${endCity}&date=${date}`;
    });
});
// 获取数据，添加标签
function getContent(obj){
    var kw = jQuery.trim($(obj).val());
    let patt = new RegExp(kw,"i");
    var html = "";
    for(let i of data){
        if(i.cityID.search(patt)>=0||i.cityName.search(patt)>=0){
            html = html + "<li class='item' onClick='getCon(this)'><span>"+i.cityID+"</span>"+"<span>"+i.cityName+"</span>"+"</li>"
        }
    }
    if(html != ""){
        $(".start-app").show(400);
        $(".start-ul").html(html);
    }else{
        $(".start-app").hide(400);
        $(".start-ul").html("");
    }

}
// 获取数据，添加标签
function getContent2(obj){
    var kw = jQuery.trim($(obj).val());
    let patt = new RegExp(kw,"i");
    var html = "";
    for(let i of data){
        if(i.cityID.search(patt)>=0||i.cityName.search(patt)>=0){
            html = html + "<li class='item' onClick='getCon2(this)'><span>"+i.cityID+"</span>"+"<span>"+i.cityName+"</span>"+"</li>"
        }
    }
    if(html != ""){
        $(".end-app").show(400);
        $(".end-ul").html(html);
    }else{
        $(".end-app").hide(400);
        $(".end-ul").html("");
    }

}
// 点击效果
function getCon(obj){
    var value = $(obj).children();
    $("#startCity").val(value[1].innerHTML);
    $(".start-app").hide(400);
    $(".start-ul").html("");
}
// 点击效果
function getCon2(obj){
    var value = $(obj).children();
    $("#endCity").val(value[1].innerHTML);
    $(".end-app").hide(400);
    $(".end-ul").html("");
}