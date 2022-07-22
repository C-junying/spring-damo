var data = [
    {
        "cityID":"123456",
        "cityName":"前思后想"
    },
    {
        "cityID":"456789",
        "cityName":"朝思暮想"
    },
    {
        "cityID":"741258",
        "cityName":"思前想后"
    },
    {
        "cityID":"123987",
        "cityName":"左思右想"
    },
    {
        "cityID":"369852",
        "cityName":"博览群书"
    },
    {
        "cityID":"369852",
        "cityName":"书声琅琅"
    },
    {
        "cityID":"369852",
        "cityName":"知书识字"
    },
    {
        "cityID":"369852",
        "cityName":"书思践行"
    }
];
$(function(){
    // 按键按下事件
    $(document).keydown(function(e){
        e = e || window.event;
        var keycode = e.which ? e.which : e.keyCode;
        if(keycode == 38){
            // 向上移动
            if(jQuery.trim($(".append-ul").html())==""){
                return;
            }
            movePrev();
        }else if(keycode == 40){
            // 向下移动
            if(jQuery.trim($(".append-ul").html())==""){
                return;
            }
            // 退出聚焦
            $("#kw").blur();
            if($(".item").hasClass("addbg")){
                moveNext();
            }else{
                // 移除背景，选取第一个，并添加背景
                $(".item").removeClass('addbg').eq(0).addClass('addbg');
            }
        }else if(keycode == 13){
            // 回车效果
            dojob();
        }
    });
    var movePrev = function(){
        // 退出聚焦
        $("#kw").blur();
        // 获取
        var index = $(".addbg").prevAll().length;
        if(index == 0){
            // 当前在第一行向上移动，移除背景，选择最后一个添加背景
            $(".item").removeClass('addbg').eq($(".item").length-1).addClass('addbg');
        }else{
            // 移到上一个位置，添加背景
            $(".item").removeClass('addbg').eq(index-1).addClass('addbg');
        }
    }
    var moveNext = function(){
        var index = $(".addbg").prevAll().length;
        if(index == $(".item").length-1){
            // 当前在最后一行向下移动，选取第一个，并添加背景
            $(".item").removeClass('addbg').eq(0).addClass('addbg');
        }else{
            // 移到下一个位置，添加背景
            $(".item").removeClass('addbg').eq(index+1).addClass('addbg');
        }     
    }
    // 回车效果
    var dojob = function(){
        $("#kw").blur();
        var value = $(".addbg").children();
        $("#kw").val(value[1].innerHTML);
        $("#kw-key").text(value[0].innerHTML);
        $(".append").hide(400);
        $(".append-ul").html("");
    }
    //显示搜索框内容
    $(".city-search form button").click(function(){
        if($("#kw").val() == ""){
            alert("输入框不能为空");
        }
        let passenger = {
            "cityID":$("#kw-key").text(),
            "cityName":$("#kw").val()
        }
        console.log(passenger);
    });

    //城市信息，编辑信息,弹出
    $(".city-sp-bg").click(function(){
        $(".modal").show();
        let list = $(this).parent().siblings();
        $("#MemberId").val(list[0].innerHTML);
        $("#MemberName").val(list[1].innerHTML);
    });
    // 城市信息，重置
    $(".pa-reset").click(function(){
        $("#MemberName").val('');
    });
    // 城市信息，编辑信息，关闭
    $(".close").click(function(){
        $(".modal").hide(400);
    });
    // 城市信息，删除显示当行信息
    $(".pa-cl").click(function(){
        let list = $(this).parent().siblings();
        let passenger = {
            "cityID":list[0].innerHTML,
            "cityName":list[1].innerHTML
        };
        console.log(passenger);
        if(confirm("是否确认删除"+passenger.cityID+"    "+passenger.cityName)){
            alert("删除成功");
        }else{
            alert("取消删除");
        }
    });
});
// 获取数据，添加标签
function getContent(obj){
    var kw = jQuery.trim($(obj).val());
    if(kw == ""){
        $(".append").hide();
        $(".append-ul").html("");
        return false;
    }
    var html = "";
    for(let i of data){
        if(i.cityID.indexOf(kw)>=0||i.cityName.indexOf(kw)>=0){
            html = html + "<li class='item' onClick='getCon(this)'><span>"+i.cityID+"</span>"+"<span>"+i.cityName+"</span>"+"</li>"
        }
    }
    if(html != ""){
        $(".append").show(400);
        $(".append-ul").html(html);
    }else{
        $(".append").hide(400);
        $(".append-ul").html("");
    }
    var ul = document.querySelectorAll('.item');
    for(let i of ul){
        // 鼠标进入
        i.addEventListener("mouseenter",function(){
            $(".item").removeClass("addbg");
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
    $("#kw").val(value[1].innerHTML);
    $("#kw-key").text(value[0].innerHTML);
    $(".append").hide(400);
    $(".append-ul").html("");
}