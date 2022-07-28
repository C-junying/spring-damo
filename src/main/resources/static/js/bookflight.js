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
    var flight_infor = {};
    var user_passenger = [];
    var passenger_flag = [];
    $(".flight-book").click(function(){
        $(".book-flight").hide(400);
        $(".book-passenger").show(400);
        let list = $(this).parent().children();
        flight_infor = {
            "onFlightID":list[0].innerHTML,
            "typeName":list[1].innerHTML,
            "startStationName":list[2].innerHTML,
            "estimatedTakeoffTime":list[3].innerHTML,
            "endStationName":list[4].innerHTML,
            "estimatedArrivalTime":list[5].innerHTML,
            "ticketPricing":parseInt(list[7].innerHTML),
            "discount":parseInt(list[8].innerHTML),
            "insuranceCosts":parseInt(list[9].innerHTML),
            "ticketCost":parseInt(list[7].innerHTML)-parseInt(list[8].innerHTML)+parseInt(list[9].innerHTML),
            "ticketTypeID":list[10].innerHTML,
            "remainingTickets":parseInt(list[6].innerHTML)
        };
        $.ajax({
            url: "/user/query-passenger",
            type: "post",
            data: '',
            dataType: "json",
            // contentType : 'application/json',
            success:function (json){
                if(json.state == 200){
                    user_passenger = json.data;
                    console.log(user_passenger);
                    let html = "";
                    for(let i of user_passenger){
                        html = html +`<li class="append-li"><span>${i.passengerName}</span><span>${i.passengerID}</span><span></span><span>${i.phone}</span></li>`;
                    }
                    console.log(html);
                    $(".pa_btn .append-ul").html(html);
                }else{
                    alert(json.message);
                }
            },
            error:function (xhr){
                alert("返回时产生未知的异常",xhr.message);
            }
        });
    });
    // 绑定删除图标的点击效果
    $("table").delegate(".td_delete","click",function(){
        if(confirm("确定要删除吗?")) {
            $(this).parent().remove();
            let list = $(this);
            for(let i = 0;i<user_passenger.length;i++){
                if(user_passenger[i].passengerID == $.trim(list[1].innerHTML)){
                    passenger_flag[i] = false;
                }
            }
        }
    });
    $(".pa_btn button").click(function(e){
        $(".append").show(400);
        e.stopPropagation();
    });
    $("body").click(function(){
        $(".append").hide(400);
    });
    $(".book-passenger").delegate(".append-li","click",function (){
        $(".append").hide(400);
        let list = $(this).children();
        let html = `<tr class="table-tr"><td>${list[0].innerHTML}</td><td>${list[1].innerHTML}</td><td>${list[3].innerHTML}</td><td class="pa_td td_delete"><span class="iconfont icon-shanchu_o"></span></td></tr>`;
        for(let i = 0;i<user_passenger.length;i++){
            if(user_passenger[i].passengerID == $.trim(list[1].innerHTML)){
                if(passenger_flag[i]!= null && passenger_flag[i]){
                    alert("该乘机人已添加："+$.trim(list[1].innerHTML));
                    return false;
                }else{
                    passenger_flag[i] = true;
                }
            }
        }
        $(".book-passenger .pa_ta table").append(html);
    });
    var passIDList = [];
    // 乘机人上一步
    $(".flight-prev").click(function(){
        $(".book-passenger").hide(400);
        $(".book-flight").show(400);
        $(".book-passenger .pa_ta table .table-tr").remove();
    });
    // 乘机人下一步提示
    $(".flight-next").click(function(){
        let tr = $(".book-passenger table tr");
        if(tr.length<=1){
            alert("请添加乘机人");
            return false;
        }
        for(let i = 1;i<tr.length;i++){
            let value = $(tr[i]).children();
            let html = "";
            html = html +`<ul><li><span>乘机人</span><span>${$.trim(value[0].innerHTML)}</span><span></span><span>${$.trim(value[1].innerHTML)}</span></li><li><span>联系人手机</span><span>${$.trim(value[2].innerHTML)}</span></li></ul>`
            passIDList.push($.trim(value[1].innerHTML));
            $(".order-ul").append(html);
        }
        $("#ticketTypeID").val(flight_infor.onFlightID);
        $("#startStation").val(flight_infor.startStationName);
        $("#endStation").val(flight_infor.endStationName);
        $("#takeoffTime").val(flight_infor.estimatedTakeoffTime);
        $("#arrivalTime").val(flight_infor.estimatedArrivalTime);
        $("#ticketPricing").val(flight_infor.ticketPricing);
        $("#discount").val(flight_infor.discount);
        $("#insuranceCosts").val(flight_infor.insuranceCosts);
        $("#ticketCost").val(flight_infor.ticketCost);
        $(".book-passenger").hide(400);
        $(".book-order").show(400);
    });
    // 订单上一步
    $(".order-prev").click(function(){
        $(".book-order").hide(400);
        $(".book-passenger").show(400);
        $(".order-ul ul").remove();
    });
    $(".book-order .form-submit").click(function (){
        $.ajax({
            url: "/user/order-create",
            type: "post",
            data: {
                "flight":JSON.stringify(flight_infor),
                "passList":JSON.stringify(passIDList)
            },
            dataType: "json",
            // contentType : 'application/json',
            success:function (json){
                if(json.state == 200){
                    console.log(json);
                    location.href = "/user/order-success";
                }else{
                    alert(json.message);
                }
            },
            error:function (xhr){
                alert("返回时产生未知的异常",xhr.message);
            }
        });
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