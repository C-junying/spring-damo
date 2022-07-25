var data = [
    {
        "onFlightID":"CA1234",
        "ticketTypeID":"XMN2022072518",
        "estimatedTakeoffTime":"2022-07-25 18:00:00"
    },
    {
        "onFlightID":"CA4567",
        "ticketTypeID":"XMN2022072518",
        "estimatedTakeoffTime":"2022-07-25 18:00:00"
    },
    {
        "onFlightID":"CA7895",
        "ticketTypeID":"XMN2022072518",
        "estimatedTakeoffTime":"2022-07-25 18:00:00"
    },
    {
        "onFlightID":"CA3695",
        "ticketTypeID":"XMN2022072518",
        "estimatedTakeoffTime":"2022-07-25 18:00:00"
    },
    {
        "onFlightID":"CA1545",
        "ticketTypeID":"XMN2022072518",
        "estimatedTakeoffTime":"2022-07-25 18:00:00"
    },
    {
        "onFlightID":"CA5689",
        "ticketTypeID":"XMN2022072518",
        "estimatedTakeoffTime":"2022-07-25 18:00:00"
    },
    
];
var mouse = new MouseMove(".item",".addbg","addbg");
$(function(){
    //显示搜索框内容
    $(".city-search form button").click(function(){
        if($("#kw").val() == ""){
            alert("输入框不能为空");
        }
        let onFlight = {
            "onFlightID":$("#kw-city-key").text(),
            "ticketTypeID":$("#kw-airport-key").text(),
            "estimatedTakeoffTime":$("#kw").val()
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
        let airport = {
            "onFlightID":list[0].innerHTML,
            "ticketTypeID":list[1].innerHTML,
            "estimatedTakeoffTime":list[2].innerHTML
        };
        console.log(airport);
        if(confirm("是否确认删除"+airport.onFlightID+"    "+airport.ticketTypeID+"   "+airport.estimatedTakeoffTime)){
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
        if(i.onFlightID.search(patt)>=0||i.ticketTypeID.search(patt)>=0||i.estimatedTakeoffTime.search(patt)>=0){
            html = html + "<li class='item' onClick='getCon()'><span>"+i.onFlightID+"</span>"+"<span>"+i.ticketTypeID+"</span>"+"<span>"+i.estimatedTakeoffTime+"</span>"+"</li>"
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