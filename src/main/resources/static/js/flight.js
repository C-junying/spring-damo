var data = [];
var mouse = new MouseMove(".item",".addbg","addbg");
$(function(){
    $("input").attr("autocomplete","off");
    //显示搜索框内容
    $(".city-search form button").click(function(){
        if($("#kw").val() == ""){
            alert("输入框不能为空");
        }
        let onFlight = {
            "onFlightID":$("#kw").val(),
            "ticketTypeID":$("#kw").val(),
            "estimatedTakeoffTime":$("#kw").val()
        }
        console.log(onFlight);
        location.href = `/admin/flight-search?onFlightID=${onFlight.onFlightID}&ticketTypeID=${onFlight.ticketTypeID}&page=1`;
    });

    //航班信息，编辑信息,弹出
    $(".city-sp-bg").click(function(){
        $(".modal").show();
        
    });
    $("table").delegate(".city-sp-bg","click",function (){
        let list = $(this).siblings();
        $.ajax({
            url: "/admin/flight-ticket",
            type: "post",
            data: {
                "ticketTypeID":list[2].innerHTML
            },
            dataType: "json",
            // contentType : 'application/json',
            success:function (json){
                if(json.state == 200){
                    $("#MemberId").val(json.data.ticketTypeID);
                    $("#MemberStartAirId").val(json.data.startAirportID);
                    $("#MemberStartStationID").val(json.data.startStationID);
                    $("#MemberEndAirportID").val(json.data.endAirportID);
                    $("#MemberEndStationID").val(json.data.endStationID);
                    $("#MemberTicketPricing").val(json.data.ticketPricing);
                    $("#MemberDiscount").val(json.data.discount);
                    $("#MemberInsuranceCosts").val(json.data.insuranceCosts);
                    $("#MemberTicketCount").val(json.data.ticketCount);
                    $("#MemberRemainingTickets").val(json.data.remainingTickets);
                    $(".modal").show(400);
                }else{
                    alert(json.message);
                }
            },
            error:function (xhr){
                alert("返回数据时产生未知的异常",xhr.message);
            }
        });
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
        if ($(".ticketTypeID").text() == "")
            location.href = "/admin/flight?page=" + value;
        else {
            let onFlight = {
                "onFlightID":$(".onFlightID").text(),
                "ticketTypeID":$(".ticketTypeID").text()
            }
            location.href = `/admin/flight-search?onFlightID=${onFlight.onFlightID}&ticketTypeID=${onFlight.ticketTypeID}&page=${value}`;
        }
    });
    $("body").click(function(){
        $(".flight-passenger").hide(400);
    });
    $(".flight-passenger").click(function(e){
        e.stopPropagation();
    });
    // 航班信息，显示乘机人
    $(".form-submit").click(function(e){
        let value = $(this).parents("fieldset").find("#MemberId").val();
        console.log(value);
        $.ajax({
            url: "/admin/flight-passengers",
            type: "post",
            data: {
                "ticketTypeID":value
            },
            dataType: "json",
            // contentType : 'application/json',
            success:function (json){
                if(json.state == 200){
                    let html = "<li><span>姓名</span><span>身份证</span><span>手机号</span></li>";
                    for(let i of json.data){
                        html = html + `<li><span>${i.passengerName}</span><span>${i.passengerID}</span><span>${i.phone}</span></li>`;
                    }
                    $(".flight-passenger ul").html(html);
                    $(".flight-passenger").show(400);
                }else{
                    alert(json.message);
                }
            },
            error:function (xhr){
                alert("返回数据时产生未知的异常",xhr.message);
            }
        });
        e.stopPropagation();
    });
    // 航班信息，删除显示当行信息
    $(".pa-cl").click(function(){
        let list = $(this).siblings();
        let onFlight = {
            "onFlightID":list[0].innerHTML,
            "ticketTypeID":list[1].innerHTML,
            "estimatedTakeoffTime":list[4].innerHTML
        };
        console.log(onFlight);
        if(confirm("是否确认删除"+onFlight.onFlightID+"    "+onFlight.ticketTypeID)){
            let page = $(".current-page").text();
            location.href = `/admin/flight-delete?onFlightID=${onFlight.onFlightID}&estimatedTakeoffTime=${onFlight.estimatedTakeoffTime}&page=${page}`;
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