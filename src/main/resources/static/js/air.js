$(function(){
    // 按键按下事件
    $(document).keydown(function(e){
        e = e || window.event;
        var keycode = e.which ? e.which : e.keyCode;
        if(keycode == 38){
            // 向上移动
            if(jQuery.trim($(".append-ul").html())==""){
                return;
            }
            // 退出聚焦
            $("#kw,.add-box #MemberTerminalAirID,.add-box #MemberAirCityID,.add-box #MemberTypeID").blur();
            mouse.movePrev();
        }else if(keycode == 40){
            // 向下移动
            if(jQuery.trim($(".append-ul").html())==""){
                return;
            }
            // 退出聚焦
            $("#kw,.add-box #MemberTerminalAirID,.add-box #MemberAirCityID,.add-box #MemberTypeID").blur();
            mouse.moveNext();
        }else if(keycode == 13){
            // 回车效果
            $("#kw,.add-box #MemberTerminalAirID,.add-box #MemberAirCityID,.add-box #MemberTypeID").blur();
            getCon();
        }
    });
    // 信息，重置
    $(".pa-reset").click(function(){
        let read = $(".modal").find("input");
        for(let i = 0;i<read.length; i++){
            if(!read[i].readOnly){
                read[i].value = "";
            }
        }
    });
    //编辑信息，关闭
    $(".home_box").delegate(".close","click",function (){
        $(".modal").find("aside").hide();
        $(".modal").find("input").val('');
        $(".modal").hide(400);
        $(".append").hide(400);
        $(".append-ul").html("");
    });
    // $(".close").click(function(){
    //     $(".modal").find("aside").hide();
    //     $(".modal").find("input").val('');
    //     $(".modal").hide(400);
    // });

    //nav导航栏跳转
    //个人信息
    $(".ref-infor").click(function (){
        location.href = "/user/user_center";
    });
    //我的乘机人
    $(".ref-passenger").click(function (){
        location.href = "/user/user_passenger";
    });
    //我的订单
    $(".ref-order").click(function (){

    });
    //充值中心
    $(".ref-pay").click(function (){
        location.href = "/user/user_balance";
    });
    //城市信息
    $(".ref-city").click(function (){
        location.href = "/admin/city";
    });
    //机场信息
    $(".ref-airport").click(function (){
        location.href = "/admin/airport";
    });
    //航班信息
    $(".ref-flight").click(function (){

    });
    //用户信息
    $(".ref-user").click(function (){
        location.href = "/admin/admin_user";
    });
    //添加信息
    $(".ref-add").click(function (){
        location.href = "/admin/admin_add"
    });
});
// 检查信息是否为空，并提示
function checkInforEmpty(){
    let sectionList = $(".modal fieldset section");
    let flag = false;
    for(let i = 0;i<sectionList.length;i++){
        // console.log(sectionList[i].nextElementSibling);
        // 如果aside存在，并且当前input如果为空，就显示aside
        if(sectionList[i].nextElementSibling!=null){
            if(sectionList[i].lastElementChild.value == ""&&sectionList[i].nextElementSibling.localName == "aside"){
                $(sectionList[i].nextElementSibling).show();
                flag = true;
            }else if(sectionList[i].lastElementChild.value != ""&&sectionList[i].nextElementSibling.localName == "aside"){
                $(sectionList[i].nextElementSibling).hide();
            }
        }
    }
    return flag;
}