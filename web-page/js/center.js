$(function(){
    // 导航菜单栏，背景颜色改变
    $("nav.center-infor li").click(function () { 
        $("nav.center-infor li").removeClass("li-action");
        $(this).addClass("li-action");
    });
    let flag = true;
    // 个人信息，消除只读与加入只读
    $("#infor .infor-sub").click(function(){
        // 当前按钮所有同胞都显示
        $(this).siblings().show();
        // 隐藏当前按钮
        $(this).hide();
        if(flag){
            $("#infor").find("input").removeAttr("readonly");
            $("#infor").find("input:first").attr("readonly","readonly");
            $("#infor").find("input:last").attr("readonly","readonly");
        }else{
            $("#infor").find("input").attr("readonly","readonly");
        }
        flag = !flag;
    })
    // 我的乘机人，弹出提示框
    $(".pa_btn button").click(function(){
        $(".modal").show();
    });
    // 我的乘机人，关闭提示框
    $(".close").click(function(){
        $(".modal").hide(400);
        $(".modal").find("input").val("");
    });
    // 我的乘机人，删除显示当行信息
    $(".pa_td").click(function(){
        let list = $(this).parent().siblings();
        let passenger = {
            "passengerID":list[1].innerHTML,
            "passengerName":list[0].innerHTML,
            "phone":list[2].innerHTML
        };
        console.log(passenger);
    });
    // 我的订单,点击显示内容
    $(".inner .order-head").click(function(){
        let hidden = $(this).next();
        if(hidden.css("display") == "none"){
            hidden.show(400);
        }else{
            hidden.hide(400);
        }
    });
    // 我的订单，退票
    $(".order-refund button").click(function(){
        if(confirm("尊敬的用户，您是否确认退票?")){
            alert("退票成功");
        }else{
            alert("您已取消退票");
        }
    });
    // 充值中心
    $(".pay-btn").click(function(){
        let value = $("#balance").val();
        if(value == ""){
            alert("请输入金额");
        }else{
            if(isNaN(value)){
                alert("请输入数字");
            }else if(confirm("请确认充值金额："+value)){
                alert("充值成功");
            }else{
                alert("充值失败");
            }
        }
    });
});