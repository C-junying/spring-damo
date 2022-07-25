var data = [
    {
        "cityID":"123456",
        "airportID":"ASD",
        "airportName":"前思后想"
    },
    {
        "cityID":"123456",
        "airportID":"ASAZXD",
        "airportName":"朝思暮想"
    },
    {
        "cityID":"123456",
        "airportID":"fsD",
        "airportName":"思前想后"
    },
    {
        "cityID":"123456",
        "airportID":"asdfg",
        "airportName":"博览群书"
    },
    {
        "cityID":"123456",
        "airportID":"AfdsSD",
        "airportName":"知书识字"
    },
    {
        "cityID":"123456",
        "airportID":"xsdx",
        "airportName":"书思践行"
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
            "cityID":$("#kw-city-key").text(),
            "airportID":$("#kw-airport-key").text(),
            "airportName":$("#kw").val()
        }
        console.log(passenger);
    });

    //机场信息，编辑信息,弹出
    $(".city-sp-bg").click(function(){
        $(".modal").show();
        let list = $(this).siblings();
        $("#MemberId").val(list[0].innerHTML);
        $("#MemberAirId").val(list[1].innerHTML);
        $("#MemberName").val(list[2].innerHTML);
    });
    // 机场信息，提交
    $(".form-submit").click(function(){
        if(!checkInforEmpty()){
            console.log("正在提交");
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
        if(confirm("是否确认删除"+airport.cityID+"    "+airport.airportID+"   "+airport.airportName)){
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