var data = [
    {
        "userID":"520221200112252001",
        "userName":"张三",
    },
    {
        "userID":"520221200112252001",
        "userName":"张三",
    },
    {
        "userID":"520221200112252001",
        "userName":"张三",
    },
    {
        "userID":"520221200112252001",
        "userName":"张三",
    },
    {
        "userID":"520221200112252001",
        "userName":"张三",
    }
    
    
];
var mouse = new MouseMove(".item",".addbg","addbg");
$(function(){
    //显示搜索框内容
    $(".city-search form button").click(function(){
        if($("#kw").val() == ""){
            alert("输入框不能为空");
        }
        let onFlight = {
            "userName":$("#kw-airport-key").text(),
            "userID":$("#kw").val()
        }
        console.log(onFlight);
    });

    //航班信息，编辑信息,弹出
    $(".city-sp-bg").click(function(){
        $(".modal").show();
        
    });
    // 航班信息，提交
    $(".form-submit").click(function(){
        if(!checkInforEmpty()){
            console.log("正在提交");
        }
    });
    // 航班信息，删除显示当行信息
    $(".pa-cl").click(function(){
        let list = $(this).siblings();
        let user = {
            "userID":list[0].innerHTML,
            "userName":list[1].innerHTML
        };
        console.log(user);
        if(confirm("是否确认删除"+user.userID+"    "+user.userName)){
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
    $("#kw-city-key").text("");
    $("#kw-airport-key").text("");
    let patt = new RegExp(kw,"i");
    var html = "";
    for(let i of data){
        if(i.userID.search(patt)>=0||i.userName.search(patt)>=0){
            html = html + "<li class='item' onClick='getCon()'><span>"+i.userID+"</span>"+"<span>"+i.userName+"</span></li>"
        }
    }
    if(html != ""){
        $(".append").show(400);
        $(".append-ul").html(html);
    }else{
        $(".append").hide(400);
        $(".append-ul").html("");
    }
    mouse.mouseListen();
}
// 点击效果
function getCon(){
    var value = mouse.listChildren();
    $("#kw-airport-key").text(value[1].innerHTML);
    $("#kw").val(value[0].innerHTML);
    $(".append").hide(400);
    $(".append-ul").html("");
}