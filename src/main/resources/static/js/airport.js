var data = [];
var mouse = new MouseMove(".item",".addbg","addbg");
$(function(){
    //显示搜索框内容
    $(".city-search form button").click(function(){
        if($("#kw").val() == ""){
            alert("输入框不能为空");
        }
        let airport = {
            "cityID":$("#kw").val(),
            "airportID":$("#kw").val(),
            "airportName":$("#kw").val()
        }
        console.log(airport);
        location.href = `/admin/airport-search?cityID=${airport.cityID}&airportID=${airport.airportID}&airportName=${airport.airportName}&page=1`;
    });

    //机场信息，编辑信息,弹出
    $(".city-sp-bg").click(function(){
        $(".tb-edit").show();
        let list = $(this).siblings();
        $("#MemberId").val(list[0].innerHTML);
        $("#MemberAirId").val(list[1].innerHTML);
        $("#MemberName").val(list[2].innerHTML);
    });
    //机场信息，航站楼,弹出
    $(".airport-td").click(function(){
        $(".tb-terminal").show();
        let list = $(this).siblings();
        console.log(list);
    });
    // 机场信息，提交
    $(".form-submit").click(function(){
        if(!checkInforEmpty()){
            console.log("正在提交");
            let airport = {
                "cityID":$("#MemberId").val(),
                "airportID":$("#MemberAirId").val(),
                "airportName":$("#MemberName").val()
            };
            let page = $(".current-page").text();
            location.href = `/admin/airport-edit?cityID=${airport.cityID}&airportID=${airport.airportID}&airportName=${airport.airportName}&page=${page}`;
            $(".modal").find("aside").hide();
            $(".modal").hide(400);
        }
    });

    // 机场信息，删除显示当行信息
    $(".pa-cl").click(function(){
        let list = $(this).siblings();
        let airport = {
            "cityID":list[0].innerHTML,
            "airportID":list[1].innerHTML,
            "airportName":list[2].innerHTML
        };
        console.log(airport);
        if(confirm("是否确认删除:"+airport.airportName)){
            let page = $(".current-page").text();
            location.href = `/admin/airport-delete?cityID=${airport.cityID}&airportID=${airport.airportID}&airportName=${airport.airportName}&page=${page}`;
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
        if ($(".airportName").text() == "")
            location.href = "/admin/airport?page=" + value;
        else {
            let airport = {
                "cityID":$(".cityID").text(),
                "airportID":$(".airportID").text(),
                "airportName":$(".airportName").text()
            };
            location.href = `/admin/airport-search?cityID=${airport.cityID}&airportID=${airport.airportID}&airportName=${airport.airportName}&page=${value}`;
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
        if(i.cityID.search(patt)>=0||i.airportID.search(patt)>=0||i.airportName.search(patt)>=0){
            html = html + "<li class='item' onClick='getCon()'><span>"+i.cityID+"</span>"+"<span>"+i.airportID+"</span>"+"<span>"+i.airportName+"</span>"+"</li>"
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
    $("#kw-city-key").text(value[0].innerHTML);
    $("#kw-airport-key").text(value[1].innerHTML);
    $("#kw").val(value[2].innerHTML);
    $(".append").hide(400);
    $(".append-ul").html("");
}