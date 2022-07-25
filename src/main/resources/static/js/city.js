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
var mouse = new MouseMove(".item",".addbg","addbg");
$(function(){
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
        let list = $(this).siblings();
        $("#MemberId").val(list[0].innerHTML);
        $("#MemberName").val(list[1].innerHTML);
    });
    // 城市信息，提交
    $(".form-submit").click(function(){
        if(!checkInforEmpty()){
            console.log("正在提交");
        }
    });

    // 城市信息，删除显示当行信息
    $(".pa-cl").click(function(){
        let list = $(this).siblings();
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