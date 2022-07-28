Calendar.create({
    classN: 'calendar-item',
    callBack: function(bindElem, dateObj) {
        bindElem.innerHTML = dateObj.year + '-' + dateObj.month + '-' + dateObj.date;
    }
});
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
    $(".or_que .flight-query").click(function (){
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
    // 交换起始地址
    $(".swap").click(function(){
        let temp = $("#startCity").val();
        $("#startCity").val($("#endCity").val());
        $("#endCity").val(temp);
    });
    $(".pa_td").click(function(){
        $(".book-flight").hide(400);
        $(".book-passenger").show(400);
    });
    // 绑定删除图片的点击效果
    $("table").delegate(".td_delete","click",function(){
        if(confirm("确定要删除吗?")) {
            $(this).parent().remove();
        }
    });
    $(".pa_btn button").click(function(e){
        $(".append").show(400);
        e.stopPropagation();
    });
    $("body").click(function(){
        $(".append").hide(400);
    });
    $(".append li").click(function(){
        $(".append").hide(400);
        let list = $(this).children();
        let html = `<tr><td>${list[0].innerHTML}</td><td>${list[1].innerHTML}</td><td>${list[2].innerHTML}</td><td class="pa_td td_delete"><span class="iconfont icon-shanchu_o"></span></td></tr>`;
        $(".pa_ta table").append(html);
    });
    // 乘机人上一步
    $(".flight-prev").click(function(){
        $(".book-passenger").hide(400);
        $(".book-flight").show(400);
    });
    // 乘机人下一步提示
    $(".flight-next").click(function(){
        let tr = $(".book-passenger table tr");
        console.log(tr);
        if(tr.length<=1){
            alert("请添加乘机人");
            return false;
        }
        $(".book-passenger").hide(400);
        $(".book-order").show(400);
    });
    // 订单上一步
    $(".order-prev").click(function(){
        $(".book-order").hide(400);
        $(".book-passenger").show(400);
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
    // 监听事件
    var ul = document.querySelectorAll('.item');
    for(let i of ul){
        // 鼠标进入
        i.addEventListener("mouseenter",function(){
            $('.item').removeClass("addbg");
            $(this).addClass("addbg");
        });
        // 鼠标离开
        i.addEventListener("mouseleave",function(){
            $(this).removeClass("addbg");
        });
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