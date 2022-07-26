var data = [
];
// let ur = location.href;
// console.log(ur.replace(/\?[x00-xff]*/g,""));
var mouse = new MouseMove(".item",".addbg","addbg");
$(function(){
    //显示搜索框内容
    $(".city-search form button").click(function(){
        if($("#kw").val() == ""){
            alert("输入框不能为空");
            $(".city-flag").text("true");
        }
        let city = {
            "cityID":$("#kw").val(),
            "cityName":$("#kw").val()
        }
        console.log(city);
        location.href = `/admin/city-search?cityID=${city.cityID}&cityName=${city.cityName}&page=1`;
    });

    //城市信息，编辑信息,弹出
    $("table").delegate(".city-sp-bg","click",function (){
        $(".modal").show();
        let list = $(this).siblings();
        $("#MemberId").val(list[0].innerHTML);
        $("#MemberName").val(list[1].innerHTML);
    });
    // 城市信息，提交
    $(".form-submit").click(function(){
        if(!checkInforEmpty()){
            console.log("正在提交");
            let city = {
                "cityID":$("#MemberId").val(),
                "cityName":$("#MemberName").val()
            };
            let page = $(".current-page").text();
            location.href = `/admin/city-edit?cityID=${city.cityID}&cityName=${city.cityName}&page=${page}`;
            $(".modal").find("aside").hide();
            $(".modal").hide(400);
        }
    });

    // 城市信息，删除显示当行信息
    $("table").delegate(".pa-cl","click",function (){
        let list = $(this).siblings();
        let city = {
            "cityID":list[0].innerHTML,
            "cityName":list[1].innerHTML
        };
        console.log(city);
        if(confirm("是否确认删除:"+city.cityID)){
            let page = $(".current-page").text();
            location.href = `/admin/city-delete?cityID=${city.cityID}&cityName=${city.cityName}&page=${page}`;
        }else{
            alert("取消删除");
        }
    });
    //跳转
    $(".first-page,.prev-page,.next-page,.jump-page").click(function(){
        let value = parseInt($(this).val());
        if(isNaN(value)){
            value = parseInt($(".input-page").val());
            if(isNaN(value)){
                alert("请输入跳转页数")
                return;
            }
            let max = parseInt($(".size-page").text());
            if(value>max){
                alert(`请输入正确的跳转页数\n输入跳转页数：${value}，最大跳转页数：${max}`);
                return;
            }
        }
        if($(".cityID").text() == ""||$(".cityName").text()=="")
            location.href = "/admin/city?page="+value;
        else{
            let city = {
                "cityID":$(".cityID").text(),
                "cityName":$(".cityName").text()
            }
            location.href = `/admin/city-search?cityID=${city.cityID}&cityName=${city.cityName}&page=${value}`;
        }
        // let resultData = {
        //     "page":value
        // };
        // $.ajax({
        //     url: "/admin/city-jump",
        //     type: "post",
        //     data: resultData,
        //     dataType: "json",
        //     // contentType : 'application/json',
        //     success:function (json){
        //         if(json.state == 200){
        //             $(".pa_ta table").html("");
        //             let html = "<tr><th>城市ID</th><th>城市</th><th>编辑</th><th>删除</th></tr>";
        //             json.data.list.forEach(function (item){
        //                 html = html + `<tr><td>${item.city_id}</td><td>${item.city_name}</td><td class="pa_td city-sp-bg"><span class="iconfont icon-edit"></span></td><td class="pa_td pa-cl"><span class="iconfont icon-shanchu_o"></span></td></tr>`;
        //             });
        //             $(".pa_ta table").html(html);
        //             $(".prev-page,.next-page,.current-page").val(value+1);
        //             $(".prev-page").val(json.data.prePage);
        //             $(".next-page").val(json.data.nextPage);
        //         }else{
        //             alert("跳转失败");
        //         }
        //     },
        //     error:function (xhr){
        //         alert("跳转时产生未知的异常",xhr.message);
        //     }
        // });
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
    $("#kw-key").text("");
    let patt = new RegExp(kw,"i");
    var html = "";
    for(let i of data){
        if(i.cityID.search(patt)>=0||i.cityName.search(patt)>=0){
            html = html + "<li class='item' onClick='getCon()'><span>"+i.cityID+"</span>"+"<span>"+i.cityName+"</span>"+"</li>"
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
    $("#kw").val(value[1].innerHTML);
    $("#kw-key").text(value[0].innerHTML);
    $(".append").hide(400);
    $(".append-ul").html("");
}