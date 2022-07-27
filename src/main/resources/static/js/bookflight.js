Calendar.create({
    classN: 'calendar-item',
    callBack: function(bindElem, dateObj) {
        bindElem.innerHTML = dateObj.year + '-' + dateObj.month + '-' + dateObj.date;
    }
});
$(function(){
    // 交换起始地址
    $(".swap").click(function(){
        let temp = $("#startCity").val();
        $("#startCity").val($("#endCity").val());
        $("#endCity").val(temp);
    });
    $(".pa_td").click(function(){
        $(".book-flight").hide(400);
        $(".book-passenger").show(400);
    });
    // 绑定删除图片的点击效果
    $("table").delegate(".td_delete","click",function(){
        if(confirm("确定要删除吗?")) {
            $(this).parent().remove();
        }
    });
    $(".pa_btn button").click(function(e){
        $(".append").show(400);
        e.stopPropagation();
    });
    $("body").click(function(){
        $(".append").hide(400);
    });
    $(".append li").click(function(){
        $(".append").hide(400);
        let list = $(this).children();
        let html = `<tr><td>${list[0].innerHTML}</td><td>${list[1].innerHTML}</td><td>${list[2].innerHTML}</td><td class="pa_td td_delete"><span class="iconfont icon-shanchu_o"></span></td></tr>`;
        $(".pa_ta table").append(html);
    });
    // 乘机人上一步
    $(".flight-prev").click(function(){
        $(".book-passenger").hide(400);
        $(".book-flight").show(400);
    });
    // 乘机人下一步提示
    $(".flight-next").click(function(){
        let tr = $(".book-passenger table tr");
        console.log(tr);
        if(tr.length<=1){
            alert("请添加乘机人");
            return false;
        }
        $(".book-passenger").hide(400);
        $(".book-order").show(400);
    });
    // 订单上一步
    $(".order-prev").click(function(){
        $(".book-order").hide(400);
        $(".book-passenger").show(400);
    });
});