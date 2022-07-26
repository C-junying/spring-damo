var data = [];
var mouse = new MouseMove(".item",".addbg","addbg");
$(function(){
    //显示搜索框内容
    $(".city-search form button").click(function(){
        if($("#kw").val() == ""){
            alert("输入框不能为空");
        }
        let user = {
            "userName":$("#kw").val(),
            "userID":$("#kw").val()
        }
        console.log(user);
        location.href = `/admin/user-search?userID=${user.userID}&userName=${user.userName}&page=1`;
    });

    // 用户信息，删除显示当行信息
    $(".pa-cl").click(function(){
        let list = $(this).siblings();
        let user = {
            "userID":list[0].innerHTML,
            "userName":list[1].innerHTML
        };
        console.log(user);
        if(confirm("是否确认删除"+user.userID+"    "+user.userName)){
            let page = $(".current-page").text();
            location.href = `/admin/user-delete?userID=${user.userID}&userName=${user.userName}&page=${page}`;
        }else{
            alert("取消删除");
        }
    });
    //跳转
    $(".first-page,.prev-page,.next-page,.jump-page").click(function() {
        let value = parseInt($(this).val());
        if (isNaN(value)) {
            value = parseInt($(".input-page").val());
            if (isNaN(value)) {
                alert("请输入跳转页数")
                return;
            }
            let max = parseInt($(".size-page").text());
            if (value > max) {
                alert(`请输入正确的跳转页数\n输入跳转页数：${value}，最大跳转页数：${max}`);
                return;
            }
        }
        if ($(".userName").text() == "")
            location.href = "/admin/admin_user?page=" + value;
        else {
            let user = {
                "userID": $(".userID").text(),
                "userName": $(".userName").text()
            }
            location.href = `/admin/user-search?userID=${user.userID}&userName=${user.userName}&page=${value}`;
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